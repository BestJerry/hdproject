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
 * 控制器样例
 * 
 */

@Controller
@RequestMapping("/user")
public class DemoController {
	
	@Autowired	//声明由Spring自动注入SqlSession生成的Mapper对象
	UserMapper mapper;
	
	@RequestMapping("/get")
	@ResponseBody		//声明将返回的对象转换成json格式
	@Transactional(rollbackFor=Exception.class) //声明事务，发生异常时回滚
	public User get(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		mapper.add(user);
		System.out.println("kk");
		return user;
	}
	
}
