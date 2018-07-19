package serviceImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import bean.Users;
import dao.UsersDAO;
import service.UsersService;

public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;

	public UsersDAO getUsersDAO() {
		return usersDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	@Override
	public boolean add(Users users) {
		boolean flag = true;
		if (users.getUId().equals("") || users.getPsd().equals("") || users.getPhone().equals("")
				|| users.getUName().equals("") || users.getUId().contains(" ") || users.getPsd().contains(" ")
				|| users.getPhone().contains(" ") || users.getUName().contains(" ")) {
			flag = false;
		}
		List<Users> ul = usersDAO.findAll();
		for (Users u : ul) {
			if (u.getUId().equals(users.getUId())) {
				flag = false;
				break;
			}
		}
		if (flag) {
			if (usersDAO.save(users) == true) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public boolean checkUser(Users users) {
		// TODO Auto-generated method stub
		List<Users> ul = usersDAO.findByIdAndPsd(users);
		if (ul.size() > 0)
			return true;
		else
			return false;
	}

}
