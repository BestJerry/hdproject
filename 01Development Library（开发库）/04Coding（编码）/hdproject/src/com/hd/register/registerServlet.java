package com.hd.register;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.hd.beans.Account;
import com.hd.beans.Business;
import com.hd.beans.Contacts;
import com.hd.mapper.AccountMapper;
import com.hd.mapper.BusinessMapper;
import com.hd.mapper.ContactsMapper;
import com.hd.tools.DBTools;


@WebServlet(urlPatterns={"/register"})
public class registerServlet extends HttpServlet {
	
	
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	    	doPost(request, response);
	}
     
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
          	request.setCharacterEncoding("utf-8");
          	String bus_type=request.getParameter("bus_type");
          	String bus_name=request.getParameter("bus_name");
          	String bus_add=request.getParameter("bus_add");
          	String bus_phone=request.getParameter("bus_phone");
          	String bus_postcode=request.getParameter("bus_postcode");
          	int bus_star=Integer.parseInt(request.getParameter("bus_star"));
          	double con_intergral=Double.parseDouble(request.getParameter("con_intergral"));
          	double discount=Double.parseDouble(request.getParameter("discount"));
          	String con_title=request.getParameter("con_title");
          	String con_name=request.getParameter("con_name");
          	String con_position=request.getParameter("con_position");
          	String con_tel=request.getParameter("con_tel");
          	String con_mobile=request.getParameter("con_mobile");
          	String con_fax=request.getParameter("con_fax");
          	String con_email=request.getParameter("con_email");
          	String verificationCode=request.getParameter("verificationCode");
          	
          	int con_id=insertContacts(con_title,con_name,con_position,con_tel,con_mobile,con_fax,con_email);
          	insertBusiness(con_id, bus_type, bus_name, bus_add, bus_phone, bus_postcode, bus_star, con_intergral, discount);
            
            //��������ʼ�˻�
            String acc_name=getAccName();
          	while(isSame(acc_name)==true){
          		acc_name=getAccName();
          	}
          	
          	//��ʼ����̶�Ϊ123456
          	String acc_psd="123456";
          	
          	request.getSession().setAttribute(acc_name, acc_name);
          	request.getSession().setAttribute(acc_psd, acc_psd);
          	
          	//request.getRequestDispatcher("../index.jsp").forward(request, response);
    
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
		List<Account> accounts = null;
		try{
			accounts=mapper.selectAllAccounts();
			System.out.println(accounts.toString());
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			// TODO: handle exception
		}
		boolean isSame=false;
		for(Account account:accounts){
			if(acc_name.equals(account.getAcc_name())){
				isSame=true;
				break;
			}	
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
			System.out.println(contacts.toString());
			session.commit();
		}catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("contacts fail");
		    session.rollback();
		}
		return con_id;
	}
	
    
    /**
	 * �����̻�
	 */
	public static void insertBusiness(int con_id,String bus_type,String bus_name,String bus_add,String bus_phone,String bus_postcode,int bus_star,double con_intergral,double discount){
		SqlSession session=DBTools.getSession();
		BusinessMapper mapper=session.getMapper(BusinessMapper.class);
		Business business=new Business(con_id, bus_type, bus_name, bus_add, bus_phone, bus_postcode, bus_star, con_intergral, discount);
		try{
			mapper.insertBusiness(business);
			System.out.println(business.toString());
			session.commit();
		}catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("business fail");
		    session.rollback();
		}
	}
}
