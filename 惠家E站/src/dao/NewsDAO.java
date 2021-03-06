package dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.News;
import util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for News
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see bean.News
 * @author MyEclipse Persistence Tools
 */
public class NewsDAO extends BaseDAO {
	private static final Logger log = LoggerFactory.getLogger(NewsDAO.class);
	// property constants
	public static final String _NFLAG = "NFlag";
	public static final String TITLE = "title";
	public static final String _NBODY = "NBody";
	public static final String AUTHOR = "author";
	public static final String _NABOUTS = "NAbouts";
	public static final String _NPASSFLAG = "NPassflag";
	private Session session;
	private Transaction transaction;

	public void save(News transientInstance) {
		log.debug("saving News instance");
		try {
			session = getSession();
			transaction = session.beginTransaction();
			session.save(transientInstance);
			transaction.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(News persistentInstance) {
		log.debug("deleting News instance");
		try {
			session = getSession();
			transaction = session.beginTransaction();
			session.delete(persistentInstance);
			transaction.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public News findById(java.lang.Integer id) {
		log.debug("getting News instance with id: " + id);
		try {
			News instance = (News) getSession().get("bean.News", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(News instance) {
		log.debug("finding News instance by example");
		try {
			List results = getSession().createCriteria("bean.News").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding News instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from News as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNFlag(Object NFlag) {
		return findByProperty(_NFLAG, NFlag);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByNBody(Object NBody) {
		return findByProperty(_NBODY, NBody);
	}

	public List findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	public List findByNAbouts(Object NAbouts) {
		return findByProperty(_NABOUTS, NAbouts);
	}

	public List findByNPassflag(Object NPassflag) {
		return findByProperty(_NPASSFLAG, NPassflag);
	}

	public List findAll() {
		log.debug("finding all News instances");
		try {
			String queryString = "from News";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public News merge(News detachedInstance) {
		log.debug("merging News instance");
		try {
			News result = (News) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(News instance) {
		log.debug("attaching dirty News instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(News instance) {
		log.debug("attaching clean News instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}