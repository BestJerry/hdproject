package com.hd.operation.modifyPassword;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import com.hd.accountBusinessContactsmapper.AccountMapper;
import com.hd.beans.Account;
import com.hd.tools.DBTools;
import com.hd.tools.Response;

@WebServlet(urlPatterns={"/updatePassword"})
public class updatePassword extends HttpServlet {
	Response res = new Response(null,"0","");//��ʼ����Ӧ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String oldpsd=request.getParameter("oldpsd");
		String newpsd=request.getParameter("newpsd");
		String confirm=request.getParameter("confirm");
		
    	
      	PrintWriter out = response.getWriter();//��ʼ����Ӧ���������
      	if(oldpsd== null||newpsd==null||confirm==null){
      			res.setStatus("1");res.setMessage("�������");
      			JSONObject resJson = new JSONObject(res);//������ת����json��ʽ
      			out.print(resJson);//���json�ַ���
      			return;//�˳�doPost����
      	}
      	
      	HttpSession httpSession = request.getSession();
      	Account account=(Account)httpSession.getAttribute("Account");
	    String correct_password=account.getAcc_psd();//��ȡ��ȷ����
       
		
		if(correct_password!=oldpsd){//ԭ��������ȷ���벻һ��
			res.setStatus("1");res.setMessage("ԭ�����������");
  			JSONObject resJson = new JSONObject(res);//������ת����json��ʽ
  			out.print(resJson);
  			return;
		}else {
			if(newpsd!=confirm){//�����������벻һ��
				res.setStatus("1");res.setMessage("�������������벻һ�£�");
      			JSONObject resJson = new JSONObject(res);//������ת����json��ʽ
      			out.print(resJson);//���json�ַ���
      			return;//�˳�doPost����
			}
			else{//�޸��������
				account.setAcc_psd(newpsd);
				//���ݿ��޸�
				int acc_id=account.getAcc_id();
				SqlSession session=DBTools.getSession();
				AccountMapper mapper=session.getMapper(AccountMapper.class);
				try{
				     mapper.updateAccount(account,acc_id);
					System.out.println(account.toString());
					session.commit();
			    	}catch (Exception e) {
					e.printStackTrace();
					session.rollback();
					res.setStatus("3");res.setMessage("���ݿ��������");
	      			JSONObject resJson = new JSONObject(res);
	      			out.print(resJson);
		  			return;
				} finally {
					session.close();
				}
			}
		}
		    //res.setData(account);
		    res.setStatus("0");
		    res.setMessage("�޸�����ɹ�!");
			JSONObject resJson = new JSONObject(res);//������ת����json��ʽ
			out.print(resJson);//���json�ַ���
			return;//�˳�doPost����

	}

}
