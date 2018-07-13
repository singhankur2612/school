package com.demo.onlineshop.controller;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.onlineshop.beans.Product;
import com.demo.onlineshop.beans.ProductList;
import com.demo.onlineshop.exceptions.ProductDetailsNotFoundException;

@RestController
public class ProductCatlogServicesController {
	private static Hashtable<Integer, Product>productsMap;
	static {	
		System.out.println("Inside block");
		productsMap = new Hashtable<>();
		productsMap.put(111, new Product(111, 12000, "ABC", "Pen"));
		productsMap.put(112, new Product(112, 12000, "HP ThinkPad", "Laoptop"));
		productsMap.put(113, new Product(113, 50000, "TouchScreen", "mobile"));
	}
	@RequestMapping(value={"/sayHello"},produces={"application/text"})
	public ResponseEntity<String> getHelloMessage(){
		ResponseEntity<String>responseEntity = 
				new ResponseEntity<String>("Hello World From RestFulWebService", HttpStatus.OK);
		return responseEntity;
	}
	@RequestMapping(value={"/allProductDetailsJSON"},produces=MediaType.APPLICATION_JSON_VALUE,
			headers ="Accept=application/json")
	public ResponseEntity<ArrayList<Product>> getAllProductDetailsJSON(){
		ArrayList<Product> productsList = new ArrayList<>(productsMap.values());
		return new ResponseEntity<>(productsList,HttpStatus.OK);
	}
	@RequestMapping(value={"/allProductDetailsXML"},produces=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ProductList> getAllProductDetailsXML(){
		ProductList productList= new ProductList(new ArrayList<>(productsMap.values()));
		return new ResponseEntity<>(productList,HttpStatus.OK);
	}
	@RequestMapping(value={"/productDetailsRequestParam"},produces=MediaType.APPLICATION_JSON_VALUE, 
			headers ="Accept=application/json")
	public ResponseEntity<Product> getProductDetailsRequestParam(@RequestParam("productCode") int productCode) 
			throws ProductDetailsNotFoundException{
		Product product = productsMap.get(productCode);
		if(product==null)throw new ProductDetailsNotFoundException("Product details with productCode "+productCode+" not found");
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	@RequestMapping(value={"/productDetailsPathVariable/{productCode}"},produces=MediaType.APPLICATION_JSON_VALUE,headers ="Accept=application/json")
	public ResponseEntity<Product> getProductDetailsPathVariable(@PathVariable("productCode") int productCode) throws ProductDetailsNotFoundException{
		Product product = productsMap.get(productCode);
		if(product==null)throw new ProductDetailsNotFoundException("Product details with productCode "+productCode+" not found");
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	@RequestMapping(value="/acceptProductDetails",method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> acceptProductDetails(@ModelAttribute Product product){
		productsMap.put(product.getProductCode(), product);
		return new ResponseEntity<>("Product details successfully added",HttpStatus.OK);
	}
	@RequestMapping(value="/deleteProductDetails/{productCode}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteProductDetails(@PathVariable("productCode") int productCode) throws ProductDetailsNotFoundException{
		Product product = productsMap.remove(productCode);
		if(product==null)
			if(product==null)throw new ProductDetailsNotFoundException("Product details with productCode "+productCode+" not found");
		return new ResponseEntity<>("Product details with productCode "+productCode+" successfully deleted",HttpStatus.OK);
	}
	@RequestMapping(value="/addProductDetailsBulkJSON", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProductDetailsBulkJSON(@RequestBody ArrayList<Product> products){
		for (Product product : products) 
			productsMap.put(product.getProductCode(), product);
		return new ResponseEntity<>("Products details successfully added",HttpStatus.OK);	
	}
}