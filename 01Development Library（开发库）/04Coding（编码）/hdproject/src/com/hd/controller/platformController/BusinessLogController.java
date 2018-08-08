package com.hd.controller.platformController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hd.general.Response;
import com.hd.general.Str2MD5;
import com.hd.mapper.platform.businesslog.AccountMapper;
import com.hd.mapper.platform.businesslog.ResetPasswordMapper;
import com.hd.pojo.Account;
import com.hd.pojo.Reset_password;

@Controller
@RequestMapping("/businessLog")
public class BusinessLogController {
		
		@Autowired
		AccountMapper accountMapper;
		
		@Autowired
		ResetPasswordMapper resetPasswordMapper;
		
		@RequestMapping("/updatePassword")
		@Transactional(rollbackFor=Exception.class)
		@ResponseBody
		public String updatePassword(String oldPassword,String newPassword,String confirmPassword,HttpServletRequest request)throws Exception{
			oldPassword = Str2MD5.MD5(oldPassword);
			newPassword = Str2MD5.MD5(newPassword);
			confirmPassword = Str2MD5.MD5(confirmPassword);
			if(!newPassword.equals(confirmPassword))
				throw new Exception("两次输入的密码不一致");
			
			Account account = (Account)request.getSession().getAttribute("account");
			
			if(!account.getPassword().equals(oldPassword))
				throw new Exception("原密码输入不正确");
			
			account.setPassword(newPassword);
			accountMapper.updateByPrimaryKey(account);
			return JSON.toJSONString(new Response(null,"0",""));
		}
		
		@RequestMapping("/forgetPassword")
		@ResponseBody
		@Transactional(rollbackFor=Exception.class)
		public int forgetPassword(String account,String verificationCode,HttpServletRequest request) throws UnknownHostException{
			String verify = (String)request.getSession().getAttribute("verificationCode");
			if(!verifyCode(verify, verificationCode)){
				return 1;
			}
			//不可用账号密码不可改
			Account bus_account = accountMapper.selectUseableAccount(account);
			if(bus_account == null)
				return 1;
			String to = accountMapper.selectMail(bus_account);
			String from = "845119166@qq.com",title = "重置密码验证",messageText;
			long current = System.currentTimeMillis();
			
			//30分钟后令牌失效
			Date overdate = new Date(current + 30 * 60 * 1000);
			String token = Str2MD5.MD5(bus_account.getAccount() + current);
			
			Reset_password record = resetPasswordMapper.selectReset_password(account);
			
			//更新已有令牌
			if(record == null)
				resetPasswordMapper.insert(new Reset_password(account, token,  overdate));
			else {
				record.setToken(token);
				record.setOverdate(overdate);
				resetPasswordMapper.update(record);
			}
				
			messageText = " 点击下方链接进入到重置密码页面：\n " +
					"http://127.0.0.1:8080/hdproject/businessLog/confirmToken?account=" + account + "&token=" + token;
			//messageText = "http://127.0.0.1:8080/hdproject/businessLog/login?account=123&password=123&verificationCode=123";
			sendMail(to, from, title, messageText);
			return 0;
		}
		
		@RequestMapping("/login")
		@ResponseBody
		public int login(String account,String password,String verificationCode,HttpServletRequest request){
			String verify = (String)request.getSession().getAttribute("verificationCode");
			if(!verifyCode(verify, verificationCode))
				return 2;
			
			Account bus_account = accountMapper.selectUseableAccount(account);
			
			if(bus_account == null) 
				return 1;
			
			String bus_password = bus_account.getPassword();
			
			if(bus_password.equals(Str2MD5.MD5(password))){
				request.getSession().setAttribute("account",bus_account);
				return 0;
			}else return 1;
		}
		
		@RequestMapping("/logout")
		@ResponseBody
		public String logout(HttpServletRequest request){
			request.getSession().removeAttribute("account");
			return JSON.toJSONString(new Response(null,"0",""));
		}
		
		@RequestMapping("/confirmToken")
		@Transactional(rollbackFor=Exception.class)
		@ResponseBody
		public int confirmToken(String account,String token,String password,String confirmPassword)throws Exception{
			Reset_password record = resetPasswordMapper.selectReset_password(account);
			if(!password.equals(confirmPassword))
				return 1;
			if(record == null)
				throw new Exception("账号不存在或链接已过期");
			else if(record.getOverdate().before(new Date(System.currentTimeMillis()))){
				//过期
				resetPasswordMapper.delete(account);
				return 1;
			}else if(!token.equals(record.getToken())){
				//令牌不对
				return 1;
			}else {	
				//账号存在且跳转页面
				Account bus_account = accountMapper.selectUseableAccount(account);
				bus_account.setPassword(Str2MD5.MD5(password));
				accountMapper.updateByPrimaryKey(bus_account);
				resetPasswordMapper.delete(account);
			}
			
			return 0;
		}
		
		
		
		private boolean verifyCode(String verify1,String verify2){
			verify1 = verify1.toLowerCase();
			verify2 = verify2.toLowerCase();
			if(!verify1.equals(verify2))
				return false;
			else return true;
		}
		
		private void sendMail(String to,String from,String title,String messageText){
		    //授权码 
			String authorizationCode = "lmwuqyzrogahbfgd";
		      
		      // 指定发送邮件的主机为 smtp.qq.com
		      String host = "smtp.qq.com";  //QQ 邮件服务器
		 
		      // 获取系统属性
		      Properties properties = System.getProperties();
		 
		      // 设置邮件服务器
		      properties.setProperty("mail.smtp.host", host);
		 
		      properties.put("mail.smtp.auth", "true");
		      // 获取默认session对象
		      Session session = Session.getDefaultInstance(properties,new Authenticator(){
		        public PasswordAuthentication getPasswordAuthentication()
		        {
		         return new PasswordAuthentication(from, authorizationCode); //发件人邮件用户名、授权码
		        }
		       });
		 
		      try{
		         // 创建默认的 MimeMessage 对象
		         MimeMessage message = new MimeMessage(session);
		 
		         // Set From: 头部头字段
		         message.setFrom(new InternetAddress(from));
		 
		         // Set To: 头部头字段
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
		 
		         // Set Subject: 头部头字段
		         message.setSubject(title);
		 
		         // 设置消息体
		         message.setText(messageText);
		 
		         // 发送消息
		         Transport.send(message);
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		}

}
