package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.camp.category;
import com.niit.dao.CategoryDao;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryDao categorydao;
	@RequestMapping("/category")
	public String showCategoryPage(Model m) {
		
		List<category> listCategory=categorydao.listcategory();
		m.addAttribute("categoryList",listCategory);
		return "category";
	}
	
	@RequestMapping(value="/addCategory",method=RequestMethod.POST)
	public String addCategory(@RequestParam("name")String name,@RequestParam("description")String description,Model m) {
	
		category category=new category();
		category.setName(name);
		category.setDescription(description);
		
		categorydao.addcategory(category);
		List<category> listCategory=categorydao.listcategory();
		m.addAttribute("categoryList",listCategory);
		return "category";
	}
	
	@RequestMapping(value="/updateCategory",method=RequestMethod.POST)
	public  String updateCategory(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("description")String description,Model m) {
	
		category category=new category();
		category.setName(name);
		category.setDescription(description);
		
		categorydao.updatecategory(category);
		List<category> listCategory=categorydao.listcategory();
		m.addAttribute("categoryList",listCategory);
		return "category";
	}
	
	@RequestMapping(value="/editCategory/{id}")
	public String editCategory(@PathVariable("id")int id,Model m){
		
		category category=categorydao.getcategoryById(id);
		m.addAttribute("categoryData",category);
		return "UpdateCategory";
		
		
	}
	@RequestMapping(value="/deleteCategory/{id}")
	public String deleteCategory(@PathVariable("id")int id,Model m){
		category category=categorydao.getcategoryById(id);
		categorydao.deletecategory(category);
		List<category> listCategory=categorydao.listcategory();
		m.addAttribute("categoryList",listCategory);
		return "category";
	}
		
	}


