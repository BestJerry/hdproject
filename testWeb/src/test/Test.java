package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import action.UsersAction;
import bean.Users;
import dao.BaseDAO;
import serviceImpl.UsersServiceImpl;
import util.HibernateSessionFactory;

public class Test {

	public static void main(String[] args) {
		Users users = new Users();
		users.setUId("7");
		users.setNumber("222");
		users.setPsd("666");
		users.setPhone("HELLO");
		users.setUName("XX");
		users.setUAbouts("33");

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// ac.getBean("dataSource");
		// SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		// BaseDAO baseDAO=(BaseDAO) ac.getBean("baseDAO");
		// UsersAction usersAction = (UsersAction) ac.getBean("usersAction");
		
		UsersServiceImpl userS = (UsersServiceImpl) ac.getBean("usersService");
		Session session = userS.getUsersDAO().getSession();
		
		Transaction transaction = session.beginTransaction();
		System.out.println(session.save(users));
		transaction.commit();
		// session.close();
		// ac.getBean("transactionManager");
		// ac.getBean("baseDAO");
		// Session session = sf.openSession();
		// Transaction transaction = session.beginTransaction();
		// UsersDAO usersDAO = (UsersDAO) ac.getBean("usersDAO");
		// usersDAO.save(users);

		// transaction.commit();
		// session.close();
	}

}
