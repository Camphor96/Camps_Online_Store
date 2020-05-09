package com.niit.modeltest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.camp.category;
import com.niit.dao.CategoryDao;

public class Categorytest {
	
	static CategoryDao categorydao;
	
	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		categorydao=(CategoryDao)context.getBean("categorydao");
	}

	@Ignore
	@Test
	public void addCategoryTest() {
		category category = new category();
		category.setName("food");
		category.setDescription("veg foodd");
		assertTrue("Problem in adding",categorydao.addcategory(category));
	}
	
	@Ignore
	@Test
	public void updateCategory()
	{
		category category = categorydao.getcategoryById(4);
		category.setName("beverages");
		category.setDescription("coffee");
		assertTrue("Problem in updating",categorydao.updatecategory(category));
		
	}
	
	@Ignore
	@Test
	public void deleteCategory()
	{
		category category = categorydao.getcategoryById(4);
		assertTrue("Problem in updating",categorydao.deletecategory(category));
	}
	
	@Test
	public void listCategory()
	{
		List<category> listcategory = categorydao.listcategory();
		assertNotNull("Problem in listing",listcategory);
		for(category c:listcategory)
		{
			System.out.println(c.getId()+"::::::"+c.getName()+":::::"+c.getDescription());
		}
		
	}
	}
