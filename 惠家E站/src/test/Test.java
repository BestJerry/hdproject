package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import action.UsersAction;
import bean.News;
import bean.Users;
import dao.BaseDAO;
import dao.NewsDAO;
import serviceImpl.UsersServiceImpl;
import util.HibernateSessionFactory;

public class Test {

	public static void main(String[] args) {
		// Date date = new Date();
		// SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(ft.format(date));
		//
		// News news = new News();
		//
		// news.setAuthor("ace");
		// news.setNBody("睡觉");
		// news.setNDate(date);
		// news.setNFlag("新闻类");
		// news.setNPassflag(false);
		// news.setTitle("今天");
		//
		// Users users = new Users();
		// users.setUId("999");
		// news.setUsers(users);

		// users.setNumber("222");
		// users.setPsd("666");
		// users.setPhone("HELLO");
		// users.setUName("XX");
		// users.setUAbouts("33");
		//
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		// // ac.getBean("dataSource");
//		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
//		Session session = sf.openSession();
//		// // BaseDAO baseDAO=(BaseDAO) ac.getBean("baseDAO");
//		// // UsersAction usersAction = (UsersAction) ac.getBean("usersAction");
//		//
//		// UsersServiceImpl userS = (UsersServiceImpl)
//		NewsDAO dao = (NewsDAO) ac.getBean("newsDAO");
//		// Session session = userS.getUsersDAO().getSession();
//		//
//		Transaction transaction = session.beginTransaction();
//
//		List<News> news = dao.findAll();
//		System.out.println(news.size());
//		transaction.commit();
//		session.close();
		// ac.getBean("transactionManager");
		// ac.getBean("baseDAO");
		// Session session = sf.openSession();
		// Transaction transaction = session.beginTransaction();
		// UsersDAO usersDAO = (UsersDAO) ac.getBean("usersDAO");
		// usersDAO.save(users);

		// transaction.commit();
		// session.close();
		String string="123";
		System.out.println(string.contains(""));

	}

}
