package action;

import com.opensymphony.xwork2.ActionSupport;

import bean.Users;
import bean.Userscheck;
import service.UsersService;
import service.UserscheckService;

public class UserscheckAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Userscheck userscheck;
	private UserscheckService userscheckService;

	public Userscheck getUserscheck() {
		return userscheck;
	}

	public void setUserscheck(Userscheck userscheck) {
		this.userscheck = userscheck;
	}

	public UserscheckService getUserscheckService() {
		return userscheckService;
	}

	public void setUserscheckService(UserscheckService userscheckService) {
		this.userscheckService = userscheckService;
	}

}
