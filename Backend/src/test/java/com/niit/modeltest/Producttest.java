package com.niit.modeltest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.camp.Product;
import com.niit.dao.ProductDao;

public class Producttest {
	static ProductDao productdao;
	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		productdao=(ProductDao)context.getBean("productdao");
	}
	@Ignore
	@Test
	public void addProductTest() {
		Product Product = new Product();
		Product.setProductname("food");
		Product.setPdescription("veg foodd");
		Product.setPrice(50);
		Product.setQuantity(10);
		assertTrue("Problem in adding",productdao.addProduct(Product));
	}
	@Ignore
	@Test
	public void updateProductTest()
	{
		Product Product = productdao.getProductById(1);
		Product.setProductname("beverages");
		Product.setPdescription("coffee");
		Product.setPrice(55);
		Product.setQuantity(20);
		assertTrue("Problem in updating",productdao.updateProduct(Product));
		
	}
	@Ignore
	@Test
	public void deleteProduct()
	{
		Product Product = productdao.getProductById(2);
		assertTrue("Problem in updating",productdao.deleteProduct(Product));
	}
	@Test
	public void listProduct()
	{
		List<Product> listProduct = productdao.listProduct();
		assertNotNull("Problem in listing",listProduct);
		for(Product c:listProduct)
		{
			System.out.println(c.getId()+"::::::"+c.getProductname()+":::::"+c.getPdescription()+":::::"+c.getPrice()+":::::"+c.getQuantity());
		}
		
	}

}
