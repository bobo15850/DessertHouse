package edu.nju.desserthouse.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao {
	@Autowired
	protected SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session getNewSession() {
		return sessionFactory.openSession();
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public Object load(Class<?> c, String id) {
		Session session = getSession();
		return session.load(c, id);
	}

	public List<?> getAllList(Class<?> c) {
		String hql = "from " + c.getName();
		Session session = getSession();
		return session.createQuery(hql).list();
	}

	public Long getTotalCount(Class<?> c) {
		Session session = getNewSession();
		String hql = "select count(*) from " + c.getName();
		Long count = (Long) session.createQuery(hql).uniqueResult();
		session.close();
		return count != null ? count.longValue() : 0;
	}

	public void save(Object bean) throws Exception{
		Session session = getNewSession();
		session.saveOrUpdate(bean);
		session.flush();
		session.clear();
		session.close();
	}

	public void update(Object bean) {
		Session session = getNewSession();
		session.update(bean);
		session.flush();
		session.clear();
		session.close();
	}

	public void delete(Object bean) {
		Session session = getNewSession();
		session.delete(bean);
		session.flush();
		session.clear();
		session.close();
	}

	public void delete(Class<?> c, Serializable id) {
		Session session = getNewSession();
		Object obj = session.get(c, id);
		session.delete(obj);
		session.flush();
		session.clear();
		session.close();
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
