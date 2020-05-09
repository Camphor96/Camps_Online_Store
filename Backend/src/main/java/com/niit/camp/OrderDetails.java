package com.niit.camp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MyOrder")
public class OrderDetails {

	@Id
	@GeneratedValue
	private int orderId;
	
	private String username;
	private Date orderDate;
	private int totalShoppingAmount;
	private String pmode;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getTotalShoppingAmount() {
		return totalShoppingAmount;
	}
	public void setTotalShoppingAmount(int totalShoppingAmount) {
		this.totalShoppingAmount = totalShoppingAmount;
	}
	public String getPmode() {
		return pmode;
	}
	public void setPmode(String pmode) {
		this.pmode = pmode;
	}
	
	

}
