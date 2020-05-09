package com.niit.dao;

import java.util.List;

import com.niit.camp.CartItem;


public interface CartItemDao {
	public boolean addCartItem(CartItem CartItem);
	public boolean updateCartItem(CartItem CartItem);
	public boolean deleteCartItem(CartItem CartItem);
	public CartItem getCartItemById(int CartItemId);
	public List<CartItem> listCartItem(String username);
}
