package com.hd.operation.modifyPassword;

import java.io.PrintWriter;
import java.nio.MappedByteBuffer;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.accountBusinessContactsmapper.AccountMapper;
import com.hd.beans.Account;
import com.hd.tools.DBTools;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String oldpsd="123456";
		String newpsd="12345678";
		String confirm="12345678";
		
		SqlSession session=DBTools.getSession();
		Account account = null;
		AccountMapper accountMapper=session.getMapper(AccountMapper.class);
		//UserMapper mapper=session.getMapper(UserMapper.class);
		try{
			account=accountMapper.selectAccountById(1);
		//	UserBean user=mapper.selectUserById(2);
			System.out.println(account.toString());
		
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			// TODO: handle exception
		}
      
      	if(oldpsd== null||newpsd==null||confirm==null){
      			System.out.println("�������");
      			return;
      	}
      	
      	String correct_password = "123456";//��ȷԭ����
    
		if(correct_password!=oldpsd){//ԭ��������ȷ���벻һ��
			System.out.println("ԭ�����������");
  		
  			return;//�˳�doPost����
		}else {//�����������벻һ��
			if(newpsd!=confirm){
				System.out.println("�������������벻һ�£�");
      			return;//�˳�doPost����
			}
			else{//�޸��������
				account.setAcc_psd(newpsd);
				SqlSession session1=DBTools.getSession();
				AccountMapper mapper=session1.getMapper(AccountMapper.class);
				try{
					mapper.updateAccount(account,1);
					System.out.println(account.toString());
					session1.commit();
			    	}catch (Exception e) {
					e.printStackTrace();
					session1.rollback();
					System.out.println("������");
					//res.setStatus("3");res.setMessage("���ݿ��������");
	      		//	JSONObject resJson = new JSONObject(res);
	      			
		  			return;
				} finally {
					session1.close();
				}
	

	}
		}

}
}
