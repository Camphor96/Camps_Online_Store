package com.niit.dao;

import java.util.List;

import com.niit.camp.Product;


public interface ProductDao {
	public boolean addProduct(Product Product);
	public boolean updateProduct(Product Product);
	public boolean deleteProduct(Product Product);
	public Product getProductById(int ProductId);
	public List<Product> listProduct();
}
