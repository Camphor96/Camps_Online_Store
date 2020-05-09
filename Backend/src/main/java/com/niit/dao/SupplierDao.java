package com.niit.dao;

import java.util.List;

import com.niit.camp.Supplier;

public interface SupplierDao {
	public boolean addSupplier(Supplier Supplier);
	public boolean updateSupplier(Supplier Supplier);
	public boolean deleteSupplier(Supplier Supplier);
	public Supplier getSupplierById(int SupplierId);
	public List<Supplier> listSupplier();
	
	

}
