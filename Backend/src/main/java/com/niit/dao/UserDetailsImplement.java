package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.camp.UserDetails;

@Repository("userdetailsdao")
@Transactional

public class UserDetailsImplement implements UserDetailsDao {
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public boolean addUserDetails(UserDetails userdetails) {
		
		try
		{
			sessionfactory.getCurrentSession().save(userdetails);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateUserDetails(UserDetails userdetails) {
		
		try
		{
			sessionfactory.getCurrentSession().update(userdetails);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean deleteUserDetails(UserDetails userdetails) {
		
		try
		{
			sessionfactory.getCurrentSession().delete(userdetails);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public UserDetails getUserDetailsById(int userdetailsId) {
		
		Session session = sessionfactory.openSession();
		UserDetails user=(UserDetails) session.get(UserDetails.class, userdetailsId);
		session.close();
		return user;
	}

	@Override
	public List<UserDetails> listUserDetails() {
		
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from UserDetails");
		List<UserDetails> listUserDetails = query.list();
		session.close();
		return listUserDetails;
	}
	

}
