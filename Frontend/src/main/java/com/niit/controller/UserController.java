package com.niit.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.camp.UserDetails;
import com.niit.dao.UserDetailsDao;

@Controller
public class UserController {
	@Autowired
	UserDetailsDao userdetailsdao;
	
	@RequestMapping("/login_success")
	public String loginProcess(HttpSession session,Model m) {
		
		String page=null;
		boolean loggedIn=true;
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		Authentication authentication = securityContext.getAuthentication();
		
		String username = authentication.getName();
		
		Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>)authentication.getAuthorities();
		
		for(GrantedAuthority role:roles) {
			
			String roleName = role.getAuthority();
			session.setAttribute("loggedIn", loggedIn);
			session.setAttribute("username", username);
			session.setAttribute("role", roleName);
			if(roleName.equals("ROLE_USER")) 
			{
				page="UserHome";
			}
			else 
			{
				page="AdminHome";
			}
		}
		return page;
	}
	
	@RequestMapping("/perform_logout")
	public String loggingOut(HttpSession session,Model m) {
		
		session.invalidate();
		m.addAttribute("errorInfo", "Logout successfully!");
		
		return "Home";
	}

	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public String addUser(@RequestParam("username")String username,@RequestParam("password")String password,
			@RequestParam("emailid")String emailid,
			@RequestParam("phonenumber")String phonenumber,Model m) {
		
		UserDetails user = new UserDetails();
		user.setUsername(username);
		user.setPassword(password);
		
		user.setEmailid(emailid);
		user.setPhonenumber(phonenumber);
		
		
		userdetailsdao.addUserDetails(user);
		
		List<UserDetails> listUserDetails = userdetailsdao.listUserDetails();
		m.addAttribute("userList", listUserDetails);
		
		m.addAttribute("Login Info","Thank you! Registered Successfully");
		
		return "Login";
		
	}
	

}
