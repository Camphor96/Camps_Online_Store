package com.niit.dao;

import java.util.List;

import com.niit.camp.category;

public interface CategoryDao {
public boolean addcategory(category category);
public boolean updatecategory(category category);
public boolean deletecategory(category category);
public category getcategoryById(int categoryId);
public List<category> listcategory();


}
