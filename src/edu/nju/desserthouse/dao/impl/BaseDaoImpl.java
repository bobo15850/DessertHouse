package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.util.ResultMessage;

public class BaseDaoImpl implements BaseDao {
	@Autowired
	protected SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session getNewSession() {
		return sessionFactory.openSession();
	}

	public ResultMessage flush() {
		try {
			getSession().flush();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}

	public ResultMessage clear() {
		try {
			getSession().clear();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.SUCCESS;
		}
	}

	public ResultMessage save(Object bean) {
		try {
			Session session = getNewSession();
			session.save(bean);
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}

	public ResultMessage delete(Object bean) {
		try {
			Session session = getNewSession();
			session.delete(bean);
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}

	public ResultMessage delete(Class<?> c, int id) {
		try {
			Session session = getNewSession();
			Object obj = session.get(c, id);
			session.delete(obj);
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAILURE;
		}
	}

	public ResultMessage update(Object bean) {
		try {
			Session session = getNewSession();
			session.update(bean);
			session.flush();
			session.clear();
			session.close();
			return ResultMessage.SUCCESS;
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("update失败");
			return ResultMessage.FAILURE;
		}
	}

	@Override
	public <T> T get(Class<T> c, int id) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		T t = (T) session.get(c, id);
		return t;
	}

	public <T> T load(Class<T> c, int id) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		T t = (T) session.load(c, id);
		return t;
	}

	public <T> List<T> getAllList(Class<T> c) {
		String hql = "from " + c.getName();
		Session session = getSession();
		@SuppressWarnings("unchecked")
		List<T> list = session.createQuery(hql).list();
		return list;
	}

	public Long getTotalCount(Class<?> c) {
		Session session = getNewSession();
		String hql = "select count(*) from " + c.getName();
		Long count = (Long) session.createQuery(hql).uniqueResult();
		session.close();
		return count != null ? count.longValue() : 0;
	}

	public <T> List<T> findByColumns(Class<T> c, String[] columns, Object[] values) {
		if (columns == null || values == null || columns.length != columns.length) {
			return null;
		}
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(c);
		Criterion[] crtns = new Criterion[columns.length];
		for (int i = 0; i < columns.length; i++) {
			crtns[i] = Restrictions.eq(columns[i], values[i]);
			criteria.add(crtns[i]);
		}
		@SuppressWarnings("unchecked")
		List<T> list = criteria.list();
		return list;
	}
}
