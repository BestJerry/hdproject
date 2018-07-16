package dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class UserBasicDAOImp extends BaseDAO implements UserBasicDAO {

	@Override
	public boolean login(Integer userId, String password) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from User where userId = ? and password = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, userId);
		query.setString(1, password);
		if (query.list().size() > 0) {
			return true;
		}
		session.close();
		return false;
	}

}
