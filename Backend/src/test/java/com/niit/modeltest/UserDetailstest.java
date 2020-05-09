   package com.niit.modeltest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.niit.camp.UserDetails;

import com.niit.dao.UserDetailsDao;


public class UserDetailstest {

	static UserDetailsDao userdetailsdao;
	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userdetailsdao=(UserDetailsDao)context.getBean("userdetailsdao");
	}
	@Ignore
	@Test
	public void addCategoryTest() {
		UserDetails userdetails = new UserDetails();
		userdetails.setUsername("food");
		userdetails.setPassword("veg foodd");
		userdetails.setPhonenumber("7654334567");
		userdetails.setEmailid("akm@gmail.com");
		assertTrue("Problem in adding",userdetailsdao.addUserDetails(userdetails));
	}
	@Ignore
	@Test
	public void updateUserDetails()
	{
		UserDetails userdetails = userdetailsdao.getUserDetailsById(1);
		userdetails.setUsername("fan");
		userdetails.setPassword("page");
		userdetails.setPhonenumber("7654344567");
		userdetails.setEmailid("akm18@gmail.com");
		assertTrue("Problem in updating",userdetailsdao.updateUserDetails(userdetails));
		
	}
	@Ignore
	@Test
	public void deleteUserDetails()
	{
		UserDetails userdetails = userdetailsdao.getUserDetailsById(1);
		assertTrue("Problem in updating",userdetailsdao.deleteUserDetails(userdetails));
	}
	@Test
	public void listUserDetails()
	{
		List<UserDetails> listUserDetails = userdetailsdao.listUserDetails();
		assertNotNull("Problem in listing",listUserDetails);
		for(UserDetails c:listUserDetails)
		{
			System.out.println(c.getUserid()+"::::::"+c.getUsername()+":::::"+c.getPassword()+":::::"+c.getPhonenumber()+":::::"+c.getEmailid());
		}
		
	}
}
