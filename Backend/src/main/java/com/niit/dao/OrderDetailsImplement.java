package com.niit.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.camp.OrderDetails;

@Repository("orderDao")
@Transactional

public class OrderDetailsImplement implements OrderDetailsDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean insertOrderDetail(OrderDetails OrderDetails) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(OrderDetails);
			return true;
		}
		catch(Exception e) 
		{
			return false;
		}
		
	}
}
