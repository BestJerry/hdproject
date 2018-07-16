package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserBasicDAO;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String password;
	private UserBasicDAO dao;
	

	public String execute() throws Exception {
		if (dao.login(userId, password)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserBasicDAO getDao() {
		return dao;
	}

	public void setDao(UserBasicDAO dao) {
		this.dao = dao;
	}

}
