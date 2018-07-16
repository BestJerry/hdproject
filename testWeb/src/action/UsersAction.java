package action;

import com.opensymphony.xwork2.ActionSupport;

import bean.Users;
import service.UsersService;

public class UsersAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Users users;
	private UsersService usersService;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public String add() {
		usersService.add(users);
		return SUCCESS;
	}
}
