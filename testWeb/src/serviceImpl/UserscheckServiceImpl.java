package serviceImpl;

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
}
