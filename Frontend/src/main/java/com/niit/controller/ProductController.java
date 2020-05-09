package com.niit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.camp.Product;
import com.niit.camp.Supplier;
import com.niit.camp.category;
import com.niit.dao.CategoryDao;
import com.niit.dao.ProductDao;
import com.niit.dao.SupplierDao;

@Controller
public class ProductController {
	@Autowired
	ProductDao productdao;
	
	@Autowired
	CategoryDao categorydao;
	
	@Autowired
	SupplierDao supplierdao;
	
	@RequestMapping(value="/product")
	public String showManageProduct(Model m) {
		
		Product product = new Product();
		m.addAttribute("product", product);
		
		List<Product> listProduct = productdao.listProduct();
		m.addAttribute("productList", listProduct);
		
		List<category> listCategory = categorydao.listcategory();
		m.addAttribute("categoryList", this.getCategoryList(listCategory));
		
		List<Supplier> listSupplier = supplierdao.listSupplier();
		m.addAttribute("supplierList", this.getSupplierList(listSupplier));
		
		return "Product";
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product")Product product,@RequestParam("productImage")MultipartFile productImage,Model m,BindingResult result) {
				
		productdao.addProduct(product);
		
		String imagePath = "D:\\Hergin\\project\\frontend\\src\\main\\webapp\\resources\\images\\";
		imagePath=imagePath+String.valueOf(product.getProductid())+".jpg";
		
		File myfile = new File(imagePath);
		
		if(!productImage.isEmpty()) {
			try {
				byte[] fileBytes = productImage.getBytes();
				FileOutputStream fos = new FileOutputStream(myfile);
				BufferedOutputStream bs = new BufferedOutputStream(fos);
				bs.write(fileBytes);
				bs.close();
			}
			catch(Exception e) {
				m.addAttribute("errorInfo","Exception Arised:"+e);
			}
		}
		else {
			m.addAttribute("errorInfo", "Error in uploading the image");
		}
		
		Product product1 = new Product();
		m.addAttribute("product", product1);
		
		List<Product> listProduct = productdao.listProduct();
		m.addAttribute("productList", listProduct);
		
		List<category> listCategory = categorydao.listcategory();
		m.addAttribute("categoryList", this.getCategoryList(listCategory));
		
		List<Supplier> listSupplier = supplierdao.listSupplier();
		m.addAttribute("supplierList", this.getSupplierList(listSupplier));
		
		return "Product";
	}
	
public LinkedHashMap<Integer,String> getCategoryList(List<category> listCategory){
		
		LinkedHashMap<Integer,String> categoryData = new LinkedHashMap<Integer,String>();
		
		int count1 = 0;
		while(count1<listCategory.size()) {
			categoryData.put(listCategory.get(count1).getId(), listCategory.get(count1).getName());
			count1++;
		}
		
		return categoryData;
	}

public LinkedHashMap<Integer,String> getSupplierList(List<Supplier> listSupplier){
	
	LinkedHashMap<Integer,String> supplierData = new LinkedHashMap<Integer,String>();
	
	int count2 = 0;
	while(count2<listSupplier.size()) {
		supplierData.put(listSupplier.get(count2).getSupplierId(), listSupplier.get(count2).getSupplierName());
		count2++;
	}
	
	return supplierData;
	
}

@RequestMapping(value="/deleteProduct/{productID}")
public String deleteProduct(@PathVariable("productID")int productID,Model m) {
	
	Product product = productdao.getProductById(productID);
	productdao.deleteProduct(product);
	
	Product product1 = new Product();
	m.addAttribute("product", product1);
	
	List<Product> listProduct = productdao.listProduct();
	m.addAttribute("productList", listProduct);
	
	List<category> listCategory = categorydao.listcategory();
	m.addAttribute("categoryList", this.getCategoryList(listCategory));
	
	List<Supplier> listSupplier = supplierdao.listSupplier();
	m.addAttribute("supplierList", this.getSupplierList(listSupplier));
	
	return "Product";
}
@RequestMapping(value="/editProduct/{productid}")
public String editProduct(@PathVariable("productid")int productid,Model m) {
	
	Product product = productdao.getProductById(productid);
	m.addAttribute("productData",product);
	
	List<Product> listProduct = productdao.listProduct();
	m.addAttribute("productList", listProduct);
	
	List<category> listCategory = categorydao.listcategory();
	m.addAttribute("categoryList", this.getCategoryList(listCategory));
	
	List<Supplier> listSupplier = supplierdao.listSupplier();
	m.addAttribute("supplierList", this.getSupplierList(listSupplier));
	
	return "UpdateProduct";
}
@RequestMapping(value="/updateProduct",method=RequestMethod.POST)
public String updateProduct(@RequestParam("productid")int productid,@RequestParam("productname")String productname,@RequestParam("pdescription")String pdescription,@RequestParam("price")int price,@RequestParam("quantity")int quantity,Model m) {
	
	Product product = productdao.getProductById(productid);
	product.setProductname(productname);
	product.setPdescription(pdescription);
	product.setPrice(price);
	product.setQuantity(quantity);
	
	productdao.updateProduct(product);
	m.addAttribute("product", product);
	
	List<Product> listProduct = productdao.listProduct();
	m.addAttribute("productList", listProduct);
	
	return "Product";
	
}
@RequestMapping("/productCatalog")
public String displayAllProduct(Model m) {
	
	
	List<Product> listProduct = productdao.listProduct();
	m.addAttribute("productList", listProduct);
	
	return "ProductCatalog";
}

@RequestMapping("/productDisplay/{productID}")
public String displaySingleProduct(@PathVariable("productID")int productID,Model m){
	
	Product product = (Product)productdao.getProductById(productID);
	m.addAttribute("productInfo", product);
	m.addAttribute("categoryName", categorydao.getcategoryById(product.getId()).getName());
	m.addAttribute("supplierName", supplierdao.getSupplierById(product.getSupplierId()).getSupplierName());
	return "ProductDisplay";
}
}
