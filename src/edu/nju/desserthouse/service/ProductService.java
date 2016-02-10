package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.util.ResultMessage;

public interface ProductService {
	public ResultMessage addProduct(Product product);

	public List<Product> getAllProducts();
}
