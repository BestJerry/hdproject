package com.hd.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hd.general.Response;

/**
 *  @author geek1116
 *  用于处理全局异常
 *
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		
		try {
			PrintWriter out = response.getWriter();
			if(exception instanceof NumberFormatException ) {
				out.print(new Response(null,"1","数据格式错误！"));
			} else if(exception instanceof SQLException) {
				out.print(new Response(null,"2","数据库操作出错！"));
			} else {
				out.print(new Response(null,"3","未知错误！"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
