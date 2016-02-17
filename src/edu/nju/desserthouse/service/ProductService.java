package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.util.ResultMessage;

public interface ProductService {
	public ResultMessage addProduct(Product product);// 添加产品

	public List<Product> getAllProducts();// 得到所有产品

	public List<Product> vagueSelect(String info);// 模糊查找
}
