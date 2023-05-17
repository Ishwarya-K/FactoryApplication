package com.product.action;

public class Product {
	private int productId,availableQuantity,price,workingHrsForSingleProd,managerId;
	private String productName, category,managerName;
	public int getProductId() {
		return productId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(int availableQuanitity) {
		this.availableQuantity = availableQuanitity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getWorkingHrsForSingleProd() {
		return workingHrsForSingleProd;
	}
	public void setWorkingHrsForSingleProd(int workingHrsForSingleProd) {
		this.workingHrsForSingleProd = workingHrsForSingleProd;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
