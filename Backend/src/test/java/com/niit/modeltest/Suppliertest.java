package com.niit.modeltest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.camp.Supplier;

import com.niit.dao.SupplierDao;

public class Suppliertest {
static SupplierDao supplierdao;
	
	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		supplierdao=(SupplierDao)context.getBean("supplierdao");
	}
	
	@Ignore
	@Test
	public void addSupplierTest() {
		Supplier supplier = new Supplier();
		supplier.setSupplierName("Samsung");
		supplier.setSupplierAddress("delhi");
		assertTrue("Problem in adding",supplierdao.addSupplier(supplier));
		
	}
	@Ignore
	@Test
	public void updateSupplier()
	{
		Supplier supplier = supplierdao.getSupplierById(2);
		supplier.setSupplierName("beverages");
		supplier.setSupplierAddress("coffee");
		assertTrue("Problem in updating",supplierdao.updateSupplier(supplier));
		
	}
	
	@Ignore
	@Test
	public void deleteSupplier()
	{
		Supplier supplier = supplierdao.getSupplierById(2);
		assertTrue("Problem in updating",supplierdao.deleteSupplier(supplier));
	}
	@Test
	public void listSupplier()
	{
		List<Supplier> listsupplier = supplierdao.listSupplier();
		assertNotNull("Problem in listing",listsupplier);
		for(Supplier c:listsupplier)
		{
			System.out.println(c.getSupplierId()+"::::::"+c.getSupplierName()+":::::"+c.getSupplierAddress());
		}
		
	}
	

}
