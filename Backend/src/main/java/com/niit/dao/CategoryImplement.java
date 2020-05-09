package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.camp.category;

@Repository("categorydao")
@Transactional
public class CategoryImplement implements CategoryDao {
	
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public boolean addcategory(category category) {
		
		try
		{
			sessionfactory.getCurrentSession().save(category);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updatecategory(category category) {
		try
		{
			sessionfactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean deletecategory(category category) {
		try
		{
			sessionfactory.getCurrentSession().delete(category);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public category getcategoryById(int categoryId) {
		
		Session session = sessionfactory.openSession();
		category cat=(category) session.get(category.class, categoryId);
		session.close();
		return cat;
	}

	@Override
	public List<category> listcategory() {
		
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from category");
		List<category> listcategory = query.list();
		session.close();
		return listcategory;
		
	}
	 

}
