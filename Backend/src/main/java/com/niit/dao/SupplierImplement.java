package com.niit.dao;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.camp.Supplier;

@Repository("supplierdao")
@Transactional

public class SupplierImplement implements SupplierDao {
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public boolean addSupplier(Supplier Supplier) {
		
		try
		{
			sessionfactory.getCurrentSession().save(Supplier);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateSupplier(Supplier Supplier) {
		
		try
		{
			sessionfactory.getCurrentSession().update(Supplier);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean deleteSupplier(Supplier Supplier) {
		
		try
		{
			sessionfactory.getCurrentSession().delete(Supplier);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Supplier getSupplierById(int SupplierId) {
		
		Session session = sessionfactory.openSession();
		Supplier supply=(Supplier) session.get(Supplier.class, SupplierId);
		session.close();
		return supply;
	}

	@Override
	public List<Supplier> listSupplier() {
		
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Supplier");
		List<Supplier> listSupplier = query.list();
		session.close();
		return listSupplier;
	}

}
