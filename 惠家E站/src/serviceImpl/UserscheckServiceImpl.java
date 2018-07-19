package serviceImpl;

import bean.Users;
import bean.Userscheck;
import dao.UserscheckDAO;
import service.UserscheckService;

public class UserscheckServiceImpl implements UserscheckService {

	private UserscheckDAO userscheckDAO;

	public UserscheckDAO getUserscheckDAO() {
		return userscheckDAO;
	}

	public void setUserscheckDAO(UserscheckDAO userscheckDAO) {
		this.userscheckDAO = userscheckDAO;
	}

	public void add(Userscheck userscheck) {
		userscheckDAO.save(userscheck);
	}

	@Override
	public void addCheck(String userID , Userscheck userscheck) {
		userscheckDAO.addCheck(userID, userscheck);

	}
}
