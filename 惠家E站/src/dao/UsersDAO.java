package dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.Users;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.Users
 * @author MyEclipse Persistence Tools
 */
public class UsersDAO extends BaseDAO {
	private static final Logger log = LoggerFactory.getLogger(UsersDAO.class);
	// property constants
	public static final String _UNAME = "UName";
	public static final String PSD = "psd";
	public static final String NUMBER = "number";
	public static final String _UABOUTS = "UAbouts";
	public static final String PHONE = "phone";
	private Session session;
	private Transaction transaction;

	public boolean save(Users transientInstance) {
		log.debug("saving Users instance");
		try {
			session = getSession();
			transaction = session.beginTransaction();
			session.save(transientInstance);
			transaction.commit();
			session.close();
			log.debug("save successful");
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Users findById(java.lang.String id) {
		log.debug("getting Users instance with id: " + id);
		try {
			System.out.println(id);
			Users instance = (Users) getSession().get("bean.Users", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Users> findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			List results = getSession().createCriteria("bean.Users").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Users> findByProperty(String propertyName, Object value) {
		log.debug("finding Users instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Users as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Users> findByIdAndPsd(Users instance) {
		log.debug("finding Users instance with property: id, value: " + instance.getUId() + "and psd,value: "
				+ instance.getPsd());
		try {
			String queryString = "from Users as model where model.UId= ? and model.psd = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, instance.getUId());
			queryObject.setParameter(1, instance.getPsd());
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by id and psd failed", re);
			throw re;
		}
	}

	public List findByUName(Object UName) {
		return findByProperty(_UNAME, UName);
	}

	public List<Users> findByPsd(Object psd) {
		return findByProperty(PSD, psd);
	}

	public List findByNumber(Object number) {
		return findByProperty(NUMBER, number);
	}

	public List findByUAbouts(Object UAbouts) {
		return findByProperty(_UABOUTS, UAbouts);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Users> findAll() {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}