package com.niit.dao;

import java.util.List;

import com.niit.camp.UserDetails;


public interface UserDetailsDao {
	public boolean addUserDetails(UserDetails userdetails);
	public boolean updateUserDetails(UserDetails userdetails);
	public boolean deleteUserDetails(UserDetails userdetails);
	public UserDetails getUserDetailsById(int userdetailsId);
	public List<UserDetails> listUserDetails();


}
