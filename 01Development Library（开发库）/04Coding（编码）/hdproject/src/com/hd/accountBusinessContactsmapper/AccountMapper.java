package com.hd.accountBusinessContactsmapper;

import java.util.List;

import com.hd.beans.Account;

public interface AccountMapper {
	/**
	  * 新增账户
	  * @param account
	  * @return
	  * throws Exception
	  */
     public int insertAccount(Account account) throws Exception;
     
     /**
      * 修改账户
      * @param account
      * @param acc_id
      * @return
      * @throws Exception
      * 
      */
     public int updateAccount(Account account,int acc_id) throws Exception;
     
     /**
      * 删除账户
      * @param Account
      * @param acc_id
      * @return 
      * @throws Exception
      */
     public int deleteAccount(int acc_id)throws Exception;
     
     /**
      * 根据acc_id查询账户信息
      * @param acc_id
      * @return
      * @throws Exception
      */
     public Account selectAccountById(int acc_id)throws Exception;
     
     /**
      * 查询所有的账户信息
      * 
      */
     public List<Account> selectAllAccounts() throws Exception;
}
