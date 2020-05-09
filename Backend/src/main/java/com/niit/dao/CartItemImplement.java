package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.camp.CartItem;


@Repository("cartitemdao")
@Transactional
public class CartItemImplement implements CartItemDao {
	@Autowired
	SessionFactory sessionfactory;
	@Override
	public boolean addCartItem(CartItem cartItem) {
		try{
			sessionfactory.getCurrentSession().save(cartItem);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	@Override
	public boolean deleteCartItem(CartItem cartItem) {
		try{
			sessionfactory.getCurrentSession().delete(cartItem);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Override
	public boolean updateCartItem(CartItem cartItem) {
		try{
			sessionfactory.getCurrentSession().update(cartItem);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public List<CartItem> listCartItem(String username) {
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from CartItem where username=:uname and pstatus='NP'");
		query.setParameter("uname", username);
		List<CartItem> listCartItem = query.list();
		return listCartItem;
	}
	
	@Override
	public CartItem getCartItemById(int cartItemId) {
		Session session =  sessionfactory.openSession();
		CartItem cartItem=(CartItem) session.get(CartItem.class,cartItemId);
		session.close();
		return cartItem;
	}

}
