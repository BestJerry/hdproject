package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import bean.Users;
import service.UsersService;

public class UsersAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Users users;
	private UsersService usersService;

	private String space = " ";

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

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

		if (usersService.add(users) == true)
			return SUCCESS;
		else
			return ERROR;
	}

	public String login() {
		if (usersService.checkUser(users)) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("userID", users.getUId());
			session.setAttribute("userName", users.getUName());
			return SUCCESS;
		}

		else
			return ERROR;
	}
}
