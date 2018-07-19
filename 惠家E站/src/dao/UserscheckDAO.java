package dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.Users;
import bean.Userscheck;

/**
 * A data access object (DAO) providing persistence and search support for
 * Userscheck entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see bean.Userscheck
 * @author MyEclipse Persistence Tools
 */
public class UserscheckDAO extends BaseDAO {
	private static final Logger log = LoggerFactory.getLogger(UserscheckDAO.class);
	// property constants
	public static final String UC_NUMBER = "ucNumber";
	public static final String UC_FLAG = "ucFlag";
	private Session session;
	private Transaction transaction;

	public boolean save(Userscheck transientInstance) {
		log.debug("saving Userscheck instance");
		try {
			session = getSession();
			transaction = session.beginTransaction();
			session.save(transientInstance);
			transaction.commit();
			// System.out.println(a);
			// System.out.println(session.close());
			log.debug("save successful");
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Userscheck persistentInstance) {
		log.debug("deleting Userscheck instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Userscheck findById(java.lang.String id) {
		log.debug("getting Userscheck instance with id: " + id);
		try {
			Userscheck instance = (Userscheck) getSession().get("bean.Userscheck", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Userscheck instance) {
		log.debug("finding Userscheck instance by example");
		try {
			List results = getSession().createCriteria("bean.Userscheck").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Userscheck instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Userscheck as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUcNumber(Object ucNumber) {
		return findByProperty(UC_NUMBER, ucNumber);
	}

	public List findByUcFlag(Object ucFlag) {
		return findByProperty(UC_FLAG, ucFlag);
	}

	public List findAll() {
		log.debug("finding all Userscheck instances");
		try {
			String queryString = "from Userscheck";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Userscheck merge(Userscheck detachedInstance) {
		log.debug("merging Userscheck instance");
		try {
			Userscheck result = (Userscheck) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Userscheck instance) {
		log.debug("attaching dirty Userscheck instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userscheck instance) {
		log.debug("attaching clean Userscheck instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void addCheck(String userID, Userscheck userscheck) {

		List<Userscheck> userschecks = findByUcNumber(userscheck.getUcNumber());
		Userscheck uc = userschecks.get(0);
		System.out.println(uc.getUcNumber() + " " + uc.getUcId() + " " + userID);

		session = getSession();
		transaction = session.beginTransaction();
		Users instance = (Users) session.get("bean.Users", userID);
		instance.setUserscheck(uc);
		session.update(instance);
		transaction.commit();
		session.close();
	}
}