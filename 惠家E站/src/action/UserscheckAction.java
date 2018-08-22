package action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import bean.Users;
import bean.Userscheck;
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

	public String add() {
		// System.out.println(userscheck.getUcId());

		Date ucCommitdate = new Date();
		userscheck.setUcCommitdate(ucCommitdate);
		userscheck.setUcFlag(false);

		userscheckService.add(userscheck);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		userscheckService.addCheck(userID, userscheck);
		return SUCCESS;

	}
}
