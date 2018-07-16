package action;

import com.opensymphony.xwork2.ActionSupport;

import bean.Admin;
import bean.Users;
import service.AdminService;
import service.UsersService;

public class AdminAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Admin admin;
	private AdminService adminService;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}
