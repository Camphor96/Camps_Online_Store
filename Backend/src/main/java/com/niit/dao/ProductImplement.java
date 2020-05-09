package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.camp.Product;


@Repository("productdao")
@Transactional
public class ProductImplement implements ProductDao {
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public boolean addProduct(Product Product) {
		
		try
		{
			sessionfactory.getCurrentSession().save(Product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product Product) {
		
		try
		{
			sessionfactory.getCurrentSession().update(Product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean deleteProduct(Product Product) {
		
		try
		{
			sessionfactory.getCurrentSession().delete(Product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Product getProductById(int ProductId) {
		
		Session session = sessionfactory.openSession();
		Product pro=(Product) session.get(Product.class, ProductId);
		session.close();
		return pro;
	}

	@Override
	public List<Product> listProduct() {
		
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Product");
		List<Product> listProduct = query.list();
		session.close();
		return listProduct;
	}
	
	
	

}
