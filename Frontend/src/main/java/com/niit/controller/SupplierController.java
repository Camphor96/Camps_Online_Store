package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.camp.Supplier;
import com.niit.dao.SupplierDao;

@Controller
public class SupplierController {
	@Autowired
	SupplierDao supplierdao;
	@RequestMapping("/supplier")
	public String showSupplierPage(Model m) {
		
		List<Supplier> listSupplier=supplierdao.listSupplier();
		m.addAttribute("categoryList",listSupplier);
		return "supplier";
	}
	
	@RequestMapping(value="/addSupplier",method=RequestMethod.POST)
	public String addSupplier(@RequestParam("supplierName")String supplierName,@RequestParam("supplierAddress")String supplierAddress,Model m) {
	
		Supplier supplier=new Supplier();
		supplier.setSupplierName(supplierName);
		supplier.setSupplierAddress(supplierAddress);
		
		supplierdao.addSupplier(supplier);
		List<Supplier> listSupplier=supplierdao.listSupplier();
		m.addAttribute("supplierList",listSupplier);
		return "supplier";
	}
	
	@RequestMapping(value="/updateSupplier",method=RequestMethod.POST)
	public  String updateSupplier(@RequestParam("SupplierId")int SupplierId,@RequestParam("supplierName")String supplierName,@RequestParam("supplierAddress")String supplierAddress,Model m) {
	
		Supplier supplier=new Supplier();
		supplier.setSupplierName(supplierName);
		supplier.setSupplierAddress(supplierAddress);
		
		supplierdao.updateSupplier(supplier);
		List<Supplier> listSupplier=supplierdao.listSupplier();
		m.addAttribute("supplierList",listSupplier);
		return "supplier";
	}
	
	@RequestMapping(value="/editSuppliery/{SupplierId}")
	public String editSupplier(@PathVariable("SupplierId")int SupplierId,Model m){
		
		Supplier supplier = supplierdao.getSupplierById(SupplierId);
		m.addAttribute("supplierData",supplier);
		return "UpdateSupplier";
		
		
	}
	
	@RequestMapping(value="/deleteCategory/{SupplierId}")
	public String deleteSupplier(@PathVariable("SupplierId")int SupplierId,Model m){
		Supplier supplier = supplierdao.getSupplierById(SupplierId);
		supplierdao.deleteSupplier(supplier);
		List<Supplier> listSupplier=supplierdao.listSupplier();
		m.addAttribute("supplierList",listSupplier);
		return "supplier";
	}
}
