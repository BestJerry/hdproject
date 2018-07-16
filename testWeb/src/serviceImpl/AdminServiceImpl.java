package serviceImpl;

import dao.AdminDAO;
import service.AdminService;

public class AdminServiceImpl implements AdminService {

	private AdminDAO adminDAO;

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
}

