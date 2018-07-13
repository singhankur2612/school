package com.demo.onlineshop.beans;

public class Product {
	 int productCode,productPrice;
	 String productDiscription , productName;
	public Product() {}
	public Product(int productCode, int productPrice, String productDiscription, String productName) {
		super();
		this.productCode = productCode;
		this.productPrice = productPrice;
		this.productDiscription = productDiscription;
		this.productName = productName;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDiscription() {
		return productDiscription;
	}
	public void setProductDiscription(String productDiscription) {
		this.productDiscription = productDiscription;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}