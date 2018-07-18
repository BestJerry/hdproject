package com.hd.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.accountBusinessContactsmapper.AccountMapper;
import com.hd.accountBusinessContactsmapper.BusinessMapper;
import com.hd.accountBusinessContactsmapper.ContactsMapper;
import com.hd.beans.Account;
import com.hd.beans.Business;
import com.hd.beans.Contacts;

import com.hd.tools.DBTools;
import com.hd.tools.Response;


@WebServlet(urlPatterns={"/register"})
public class registerServlet extends HttpServlet {
	
	
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	    	doPost(request, response);
	}
     
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
          	String bus_type=request.getParameter("bus_type");
          	String bus_name=request.getParameter("bus_name");
          	String bus_add=request.getParameter("bus_add");
          	String bus_phone=request.getParameter("bus_phone");
          	String bus_postcode=request.getParameter("bus_postcode");if(bus_postcode.trim().equals(""))bus_postcode=null;
          	int bus_star=Integer.parseInt(request.getParameter("bus_star"));
          	double con_intergral=Double.parseDouble(request.getParameter("con_intergral"));
          	double discount=Double.parseDouble(request.getParameter("discount"));
          	String con_title=request.getParameter("con_title");
          	
          	String con_name=request.getParameter("con_name");
          	String con_position=request.getParameter("con_position");
          	String con_tel=request.getParameter("con_tel");if(con_tel.trim().equals(""))con_tel=null;
          	String con_mobile=request.getParameter("con_mobile");if(con_mobile.trim().equals(""))con_mobile=null;
          	String con_fax=request.getParameter("con_fax");if(con_fax.trim().equals(""))con_fax=null;
          	String con_email=request.getParameter("con_email");
          	String verificationCode=request.getParameter("verificationCode");
          	
          	Response res = new Response(null,"0","");//��ʼ����Ӧ����
          	PrintWriter out = response.getWriter();//��ʼ����Ӧ���������
          	if(verificationCode == null || verificationCode.equals("") || 
          			!verificationCode.equals(request.getSession().getAttribute("verificationCode"))) {
          			//������֤���Ƿ���ȷ
          			res.setStatus("1");res.setMessage("��֤�����");
          			//System.out.println
          			JSONObject resJson = new JSONObject(res);//������ת����json��ʽ
          			out.print(resJson);//���json�ַ���
          			return;//�˳�doPost����
          	}
          	
          	int con_id=insertContacts(con_title,con_name,con_position,con_tel,con_mobile,con_fax,con_email);
          	int bus_id = insertBusiness(con_id, bus_type, bus_name, bus_add, bus_phone, bus_postcode, bus_star, con_intergral, discount);
            
            //��������ʼ�˻�
            String acc_name=getAccName();
          	while(isSame(acc_name)==true){
          		acc_name=getAccName();
          	}
          	//��ʼ����̶�Ϊ123456
          	String acc_psd="123456";
          	
          	//�������˺�
          	Account account = new Account(0,1,bus_id,acc_name,acc_psd,true,null);
          	SqlSession session=DBTools.getSession();
    		AccountMapper mapper=session.getMapper(AccountMapper.class);
    		try {
				mapper.insertAccount(account);
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
				res.setStatus("3");res.setMessage("���ݿ��������");
      			JSONObject resJson = new JSONObject(res);
      			out.print(resJson);
      			return;
			} finally {
				session.close();//sessionʹ�����ر�
			}
    		
    		res.setData(account);
  			JSONObject resJson = new JSONObject(res);//������ת����json��ʽ
  			out.print(resJson);//���json�ַ���
  			return;//�˳�doPost����
    }
    
    /**
     * �������8λ�˺ţ�����ǰ��λΪ�����ĸ������λΪ�������
     */
    private String getAccName(){
    	String acc_name="";
    	Random random=new Random();
      	String charsString="abcdefghijklmnopqrstuvwxyz";
      	for(int i=0;i<4;i++){
      		acc_name+=charsString.charAt((int)(Math.random()*26));
      	}
      	String numsString="0123456789";
      	for(int i=0;i<4;i++){
      		acc_name+=numsString.charAt((int)(Math.random()*10));
      	}
      
      	return acc_name;
    }
    
    
    /**
     * ������ݿ��������ظ����˺�
     * @return
     */
    private boolean isSame(String acc_name){
    	SqlSession session=DBTools.getSession();
		AccountMapper mapper=session.getMapper(AccountMapper.class);
		boolean isSame=false;
		try{
			Account account = mapper.selectAccountByName(acc_name);
			if(account != null) isSame = true;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isSame;
    }
    
    /**
     * ������ϵ��
     */
    public static int insertContacts(String con_title,String con_name,String con_position,String con_tel,String con_mobile,String con_fax,String con_email){
    	int con_id = 0;//Ҫ��ȡ����������
		SqlSession session=DBTools.getSession();
		ContactsMapper mapper=session.getMapper(ContactsMapper.class);
		Contacts contacts=new Contacts(con_title, con_name, con_position, con_tel, con_mobile, con_fax, con_email);
		try{
			mapper.insertContacts(contacts);
			con_id=contacts.getCon_id();
			session.commit();
		}catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("contacts fail");
		    session.rollback();
		} finally {
			session.close();
		}
		return con_id;
	}
	
    
    /**
	 * �����̻�
	 */
	public static int insertBusiness(int con_id,String bus_type,String bus_name,String bus_add,String bus_phone,String bus_postcode,int bus_star,double con_intergral,double discount){
		SqlSession session=DBTools.getSession();
		BusinessMapper mapper=session.getMapper(BusinessMapper.class);
		Business business=new Business(con_id, bus_type, bus_name, bus_add, bus_phone, bus_postcode, bus_star, con_intergral, discount);
		int bus_id = 0;//Ҫ��ȡ����������
		try{
			mapper.insertBusiness(business);
			bus_id= business.getBus_id();
			session.commit();
		}catch (Exception e) {
		    e.printStackTrace();
		    session.rollback();
		} finally {
			session.close();
		}
		return bus_id;
	}
}
