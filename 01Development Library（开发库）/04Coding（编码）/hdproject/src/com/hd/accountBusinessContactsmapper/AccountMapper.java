package com.hd.accountBusinessContactsmapper;

import java.util.List;

import com.hd.beans.Account;

public interface AccountMapper {
	/**
	  * �����˻�
	  * @param account
	  * @return
	  * throws Exception
	  */
     public int insertAccount(Account account) throws Exception;
     
     /**
      * �޸��˻�
      * @param account
      * @param acc_id
      * @return
      * @throws Exception
      * 
      */
     public int updateAccount(Account account,int acc_id) throws Exception;
     
     /**
      * ɾ���˻�
      * @param Account
      * @param acc_id
      * @return 
      * @throws Exception
      */
     public int deleteAccount(int acc_id)throws Exception;
     
     /**
      * ����acc_id��ѯ�˻���Ϣ
      * @param acc_id
      * @return
      * @throws Exception
      */
     public Account selectAccountById(int acc_id)throws Exception;
     
     /**
      * ��ѯ���е��˻���Ϣ
      * 
      */
     public List<Account> selectAllAccounts() throws Exception;
}
