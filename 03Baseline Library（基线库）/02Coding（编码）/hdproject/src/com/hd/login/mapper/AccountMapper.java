/**
 * 
 */
package com.hd.login.mapper;

import java.util.List;

import com.hd.beans.Account;

/**
 * @author Jerry
 *
 * @date 2018��7��14��
 */
public interface AccountMapper {

	public Account selectAccountByNameAndPsw(String acc_name,String acc_password) throws Exception;
	
	public List<Account> selectAllAccounts() throws Exception;
}
