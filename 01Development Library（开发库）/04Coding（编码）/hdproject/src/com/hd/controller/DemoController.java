package com.hd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.mapper.UserMapper;
import com.hd.pojo.User;

/**
 * ����������
 * 
 */

@Controller
@RequestMapping("/user")
public class DemoController {
	
	@Autowired	//������Spring�Զ�ע��SqlSession���ɵ�Mapper����
	UserMapper mapper;
	
	@RequestMapping("/get")
	@ResponseBody		//���������صĶ���ת����json��ʽ
	@Transactional(rollbackFor=Exception.class) //�������񣬷����쳣ʱ�ع�
	public User get(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		mapper.add(user);
		System.out.println("kk");
		return user;
	}
	
}
