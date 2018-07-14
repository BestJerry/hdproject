/**
 * 
 */
package com.hd.login.controller;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.beans.Account;
import com.hd.login.mapper.AccountMapper;
import com.hd.tools.DBTools;
import com.hd.tools.Response;

/**
 * @author Jerry
 *
 * @date 2018��7��14��
 */
public class LoginController implements Controller {

	/*
	 * (non-Javadoc)
	 * 
	 * @see login.controller.Controller#handleRequest(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public JSONObject handleRequest(HttpServletRequest request, HttpServletResponse response) {

		// TODO Auto-generated method stub
		

		JSONObject jsonObject;
		Response result = new Response();
		
		if (!request.getParameter("verificationCode").equals(request.getSession().getAttribute("verificationCode"))) {
			result.setData(new JSONObject());
			result.setMessage("��֤�����");
			result.setStatus("1");
			jsonObject = new JSONObject(result);
			return jsonObject;
		}
		
		SqlSession session = DBTools.getSession();

		AccountMapper accountMapper = session.getMapper(AccountMapper.class);
		try {
			Account account = accountMapper.selectAccountByNameAndPsw(request.getParameter("acc_name"),
					request.getParameter("acc_psd"));

			System.out.println(account.toString());

			result.setData(new JSONObject());
			result.setMessage("");
			result.setStatus("0");
			jsonObject = new JSONObject(result);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("account", account);

			session.commit();
		} catch (Exception e) {
			// TODO: handle exception

			result.setData(new JSONObject());
			result.setMessage("�˺Ų����ڻ��������");
			result.setStatus("1");
			jsonObject = new JSONObject(result);
			session.rollback();
		} finally {
			session.close();
		}

		return jsonObject;
	}

}
