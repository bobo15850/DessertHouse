package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.ProductDao;
import edu.nju.desserthouse.model.Product;

@Repository
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	@Override
	public List<Product> vagueSelect(String info) {
		Criteria criteria = super.getSession().createCriteria(Product.class);
		criteria.add(Restrictions.or(Restrictions.like("name", info, MatchMode.ANYWHERE),
				Restrictions.like("info", info, MatchMode.ANYWHERE)));
		@SuppressWarnings("unchecked")
		List<Product> products = criteria.list();
		return products;
	}

}
