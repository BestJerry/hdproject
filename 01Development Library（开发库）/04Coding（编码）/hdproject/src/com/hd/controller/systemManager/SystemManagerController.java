package com.hd.controller.systemManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hd.general.Email;
import com.hd.general.Response;
import com.hd.mapper.businessInfoMapper.BusinessInfoMapper;
import com.hd.mapper.systemManagerMapper.SystemManagerMapper;
import com.hd.pojo.Business;

@Controller
@RequestMapping("/system")
public class SystemManagerController {
	
	static final String LOGIN_ACCOUNT = "root"; //管理员账号
	
	static final String LOGIN_PASSWORD = "iop123asdf"; //管理员密码
	
	@Autowired
	SystemManagerMapper mapper;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(String account,String password) {
		if(account != null && password != null && account.equals(LOGIN_ACCOUNT) && password.equals(LOGIN_PASSWORD)) {
			return JSON.toJSONString(new Response("0","0",""));
		}
		return JSON.toJSONString(new Response("1","0",""));
	}
	
	@RequestMapping("/getToExamine")
	@ResponseBody
	public List<Business> getToExamine(Integer count,Integer page) {
		int tcount = 10, tpage = 1;
		if(count != null) tcount = count;
		if(page != null) tpage = page;
		return mapper.getAllToExamine(tcount, (tpage - 1)*tcount);
	}
	
	@RequestMapping("/operateBusinessStatus")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String operateBusinessStatus(int id,int status) {
		String tStatus = status==0?"禁用":"启用";
		mapper.updateBusinessStatus(id,tStatus);
		return JSON.toJSONString(new Response(null,"0",""));
	}
	
	@RequestMapping("/operateExamine")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String operateExamine(int id,int status){
		if(status == 1){
			String toEmail = mapper.getEmail(id);
			Email.sendMail(toEmail, "845119166@qq.com", "七彩云商家注册审核结果", "已通过");
		}else {
			mapper.deleteBusiness(id);
		}
		return JSON.toJSONString(new Response(null,"0",""));
	}
}
