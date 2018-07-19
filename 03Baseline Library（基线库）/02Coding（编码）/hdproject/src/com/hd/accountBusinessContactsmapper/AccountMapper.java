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
     public int insertAccount(Account account);
     
     /**
      * �޸��˻�
      * @param account
      * @param acc_id
      * @return
      * @throws Exception
      * 
      */
     public int updateAccount(Account account,int acc_id);
     
     /**
      * ɾ���˻�
      * @param Account
      * @param acc_id
      * @return 
      * @throws Exception
      */
     public int deleteAccount(int acc_id);
     
     /**
      * ����acc_id��ѯ�˻���Ϣ
      * @param acc_id
      * @return
      * @throws Exception
      */
     public Account selectAccountById(int acc_id);
     
     public Account selectAccountByName(String acc_name);
     
     /**
      * ��ѯ���е��˻���Ϣ
      * 
      */
     public List<Account> selectAllAccounts();
     
     public List<Account> searchAccounts(int start, int countEachPage, String account_name,int bus_id);
}
