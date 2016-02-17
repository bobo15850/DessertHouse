package edu.nju.desserthouse.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.ProductDao;
import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.service.ProductService;
import edu.nju.desserthouse.util.ResultMessage;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public ResultMessage addProduct(Product product) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		product.setCreatedTime(stamp);
		return productDao.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productDao.getAllList(Product.class);
		return products;
	}

	@Override
	public List<Product> vagueSelect(String info) {
		if (info == null || info.length() == 0) {
			return productDao.getAllList(Product.class);
		}
		return productDao.vagueSelect(info);
	}

}
