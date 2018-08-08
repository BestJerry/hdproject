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
				throw new Exception("������������벻һ��");
			
			Account account = (Account)request.getSession().getAttribute("account");
			
			if(!account.getPassword().equals(oldPassword))
				throw new Exception("ԭ�������벻��ȷ");
			
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
			//�������˺����벻�ɸ�
			Account bus_account = accountMapper.selectUseableAccount(account);
			if(bus_account == null)
				return 1;
			String to = accountMapper.selectMail(bus_account);
			String from = "845119166@qq.com",title = "����������֤",messageText;
			long current = System.currentTimeMillis();
			
			//30���Ӻ�����ʧЧ
			Date overdate = new Date(current + 30 * 60 * 1000);
			String token = Str2MD5.MD5(bus_account.getAccount() + current);
			
			Reset_password record = resetPasswordMapper.selectReset_password(account);
			
			//������������
			if(record == null)
				resetPasswordMapper.insert(new Reset_password(account, token,  overdate));
			else {
				record.setToken(token);
				record.setOverdate(overdate);
				resetPasswordMapper.update(record);
			}
				
			messageText = " ����·����ӽ��뵽��������ҳ�棺\n " +
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
				throw new Exception("�˺Ų����ڻ������ѹ���");
			else if(record.getOverdate().before(new Date(System.currentTimeMillis()))){
				//����
				resetPasswordMapper.delete(account);
				return 1;
			}else if(!token.equals(record.getToken())){
				//���Ʋ���
				return 1;
			}else {	
				//�˺Ŵ�������תҳ��
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
		    //��Ȩ�� 
			String authorizationCode = "lmwuqyzrogahbfgd";
		      
		      // ָ�������ʼ�������Ϊ smtp.qq.com
		      String host = "smtp.qq.com";  //QQ �ʼ�������
		 
		      // ��ȡϵͳ����
		      Properties properties = System.getProperties();
		 
		      // �����ʼ�������
		      properties.setProperty("mail.smtp.host", host);
		 
		      properties.put("mail.smtp.auth", "true");
		      // ��ȡĬ��session����
		      Session session = Session.getDefaultInstance(properties,new Authenticator(){
		        public PasswordAuthentication getPasswordAuthentication()
		        {
		         return new PasswordAuthentication(from, authorizationCode); //�������ʼ��û�������Ȩ��
		        }
		       });
		 
		      try{
		         // ����Ĭ�ϵ� MimeMessage ����
		         MimeMessage message = new MimeMessage(session);
		 
		         // Set From: ͷ��ͷ�ֶ�
		         message.setFrom(new InternetAddress(from));
		 
		         // Set To: ͷ��ͷ�ֶ�
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
		 
		         // Set Subject: ͷ��ͷ�ֶ�
		         message.setSubject(title);
		 
		         // ������Ϣ��
		         message.setText(messageText);
		 
		         // ������Ϣ
		         Transport.send(message);
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		}

}
