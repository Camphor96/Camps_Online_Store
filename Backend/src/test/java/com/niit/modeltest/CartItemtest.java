package com.niit.modeltest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.camp.CartItem;
import com.niit.dao.CartItemDao;
import com.niit.dao.CategoryDao;

public class CartItemtest {

static CartItemDao cartitemdao;
	
	@BeforeClass
	public static void initialize() {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		cartitemdao=(CartItemDao)context.getBean("cartitemdao");
	}
	@Ignore
	@Test
	public void addCartItemTest(){
		CartItem CartItem = new CartItem();
		CartItem.setProductid(81);
		CartItem.setProductname("Chicken briyani");
		CartItem.setPrice(130);
		CartItem.setQuantity(50);
		CartItem.setUsername("sakthi");
		CartItem.setPstatus("NP");
		assertTrue("problem in adding to cart:",cartitemdao.addCartItem(CartItem));
	}
	
	@Test
	public void listCartItemTest() {
		List<CartItem> listCartItem = cartitemdao.listCartItem("sakthi");
		
		assertTrue("problem in listing cart item:",listCartItem.size()>0);
		
		for(CartItem cartItem:listCartItem) 
		{
		 	System.out.println(cartItem.getProductid());
			System.out.println(cartItem.getProductname());
			System.out.println(cartItem.getQuantity());
		}
	}
}
