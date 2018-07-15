package com.hd.subaccount.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.hd.accountBusinessContactsmapper.AccountMapper;
import com.hd.tools.DBTools;
import com.hd.tools.Response;

@WebServlet("/addSubaccount")
public class AddSubaccountServlet extends HttpServlet {

	public AddSubaccountServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response res = new Response(null, "0" ,"");
		int account_type = Integer.parseInt(request.getParameter("account_type"));
		int isStart = Integer.parseInt(request.getParameter("isStart"));
		if(account_type<=1 || account_type > 4 || isStart != 0 || isStart != 1) throw new NumberFormatException();
		
		SqlSession sqlSession = DBTools.getSession();
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		/*
		 *随机分配账号和密码 并返回
		 * 早上修改增加新子帐号的接口文档（漏返回账号密码） 
		 */
		
	}


	public void init() throws ServletException {
	}

}
