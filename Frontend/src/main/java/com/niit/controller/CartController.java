package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.camp.CartItem;
import com.niit.camp.Product;
import com.niit.dao.CartItemDao;
import com.niit.dao.ProductDao;

@Controller
public class CartController {
	
	@Autowired
	ProductDao productdao;
	
	@Autowired
	CartItemDao cartitemdao;

	@RequestMapping("/cart")
	public String showCart(HttpSession session,Model m) 
	{
		String username = (String)session.getAttribute("username");
		
		List<CartItem> listCartItem = cartitemdao.listCartItem(username);
		m.addAttribute("cartItem", listCartItem);
		m.addAttribute("grandTotal", this.calcGrandTotalValue(listCartItem));
		
		return "Cart";
	}
	
	@RequestMapping("/addToCart/{productId}")
	public String addToCart(@PathVariable("productId")int productId, @RequestParam("quantity")int quantity, HttpSession session,Model m) 
	{
	
		Product product = productdao.getProductById(productId);
		
		String username = (String)session.getAttribute("username");
		
		CartItem cartItem = new CartItem();
		cartItem.setProductid(product.getProductid());
		cartItem.setProductname(product.getProductname());
		cartItem.setPrice(product.getPrice());
		cartItem.setQuantity(quantity);
		cartItem.setPstatus("NP");
		cartItem.setUsername(username);
		
		cartitemdao.addCartItem(cartItem);
		
		List<CartItem> listCartItem = cartitemdao.listCartItem(username);
		m.addAttribute("cartItem", listCartItem);
		m.addAttribute("grandTotal", this.calcGrandTotalValue(listCartItem));
		
		return "Cart";
	}
	
	
	@RequestMapping(value="/updateCartItem/{cartItemId}")
	public String updateCartItem(@PathVariable("cartItemId")int cartItemId,@RequestParam("quantity")int quantity,Model m,HttpSession session) 
	{
		CartItem cartItem = cartitemdao.getCartItemById(cartItemId);
		cartItem.setQuantity(quantity);
		cartitemdao.updateCartItem(cartItem);
		
		String username = (String)session.getAttribute("username");

		List<CartItem> listCartItem = cartitemdao.listCartItem(username);
		m.addAttribute("cartItem", listCartItem);
		m.addAttribute("grandTotal", this.calcGrandTotalValue(listCartItem));
		
		return "Cart";
	}
	
	@RequestMapping(value="/deleteCartItem/{cartItemId}")
	public String deleteCartItem(@PathVariable("cartItemId")int cartItemId,Model m,HttpSession session) 
	{
		
		CartItem cartItem = cartitemdao.getCartItemById(cartItemId);
		
		cartitemdao.deleteCartItem(cartItem);
		
		String username = (String)session.getAttribute("username");
		
		List<CartItem> listCartItem = cartitemdao.listCartItem(username);
		m.addAttribute("cartItem", listCartItem);
		m.addAttribute("grandTotal", this.calcGrandTotalValue(listCartItem));
		
		return "Cart";
	}

	public long calcGrandTotalValue(List<CartItem> listCartItem) 
	{
		int count=0;
		long grandTotalPrice = 0;
		while(count<listCartItem.size())
		{
			grandTotalPrice+=listCartItem.get(count).getQuantity()*listCartItem.get(count).getPrice();
			count++;
		}
		return grandTotalPrice;
	}
	
	@RequestMapping(value="/checkout")
	public String checkOut(HttpSession session,Model m)
	{
String username = (String)session.getAttribute("username");
		
		List<CartItem> listCartItem = cartitemdao.listCartItem(username);
		m.addAttribute("cartItem", listCartItem);
		m.addAttribute("grandTotal", this.calcGrandTotalValue(listCartItem));
		
		return "OrderDetail";
	}



}
