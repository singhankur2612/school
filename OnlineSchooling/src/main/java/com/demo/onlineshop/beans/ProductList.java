package com.demo.onlineshop.beans;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductList {
	
	@XmlElement(name="product")
	public ArrayList<Product> products;
	
	public ProductList() {}

	public ProductList(ArrayList<Product> products) {
		super();
		this.products = products;
	}
}