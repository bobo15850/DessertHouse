package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Product;

public interface ProductDao extends BaseDao {
	public List<Product> vagueSelect(String info);
}
