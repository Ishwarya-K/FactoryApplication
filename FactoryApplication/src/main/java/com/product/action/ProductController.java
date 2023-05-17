package com.product.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductController extends ActionSupport{
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private String addProductStatus;
	private ArrayList<Product> productList;
	private Map<Integer,Product> productMap;
	private ArrayList<String> skills;
	private Map<Integer,Spare> spareMap;
	private int productId;
	private Map<Integer,String> skillMap;
	private Map<String,Integer> capacityMap;
	private String productName;
	private String category;
	private int availableQuantity;
	private String spares;
	private int spareQuantity;
	private String skillString;
	public String getAddProductStatus() {
		return addProductStatus;
	}

	public void setAddProductStatus(String addProductStatus) {
		this.addProductStatus = addProductStatus;
	}

	public String getSkillString() {
		return skillString;
	}

	public void setSkillString(String skillString) {
		this.skillString = skillString;
	}

	public int getSpareQuantity() {
		return spareQuantity;
	}

	public void setSpareQuantity(int spareQuantity) {
		this.spareQuantity = spareQuantity;
	}

	public String getSpares() {
		return spares;
	}

	public void setSpares(String spares) {
		this.spares = spares;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getWorkingHrs() {
		return workingHrs;
	}

	public void setWorkingHrs(int workingHrs) {
		this.workingHrs = workingHrs;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}
	private int price;
	private int workingHrs;
	private int managerId;
	public String getProducts() {
		ProductService prdService = new ProductService();
		productList=prdService.getProducts();
		logger.log(Level.INFO, "getProducts - productList : "+productList);
		if(productList.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
		
	}
	
	public String getProductListJSON() {
		ProductService prdService = new ProductService();
		productMap=prdService.getProductsMap();
		logger.log(Level.INFO, "getProductListJSON - productMap : "+productMap);
		if(productMap.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public String getSkillsRequired() {
		SkillService skillService = new SkillService();
		skills=skillService.getProductSkills(productId);
		System.out.println("productId : "+productId);
		logger.log(Level.INFO, "getSkillsRequired - skills : "+skills);
		if(skills.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
		
	}
	
	public String getSkillsListJSON() {
		SkillService skillService = new SkillService();
		skillMap=skillService.getSkillsMap(productId);
		System.out.println("skill map = "+skillMap+" prd id = "+productId);
		logger.log(Level.INFO, "getSkillsListJSON - skillMap : "+skillMap);
		if(skillMap.size()>0) {
			return SUCCESS;

		}
		else {
			return ERROR;
		}
	}
	
	public String getSpareListJSON() {
		SpareRequirementService spareReqService = new SpareRequirementService();
		spareMap=spareReqService.getSpareMap(productId);
		System.out.println(spareMap);
		logger.log(Level.INFO, "getSpareListJSON - spareMap : "+spareMap);
		if(spareMap.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public String getCapacityPlanJSON() {
		CapacityPlanningService capacityService=new CapacityPlanningService();
		capacityMap=capacityService.planCapacity(productId);
		System.out.println("CapacityMap : "+capacityMap);
		logger.log(Level.INFO, "getCapacityPlanJSON - capacityMap : "+capacityMap);
		if(capacityMap.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public String addProduct() {
		ProductService prdService = new ProductService();
		boolean validateSkills=prdService.validateSkills(skillString);
		boolean validateSpares=prdService.validateSpare(spares);
		System.out.println("validateSkills : "+validateSkills+" validateSpares : "+validateSpares);
		if(!validateSkills || !validateSpares) {
			addProductStatus="error";
			return ERROR;
		}
		boolean added = prdService.addProduct(productName, category, availableQuantity, price, workingHrs, managerId);
		boolean addedSkills=prdService.addSkills(productName, skillString);
		boolean addedSpares=prdService.addSpare(productName, spares, spareQuantity);
		logger.log(Level.INFO, "addProduct => productAdded : "+added+" ,addedSkills : "+addedSkills+" ,addedSpares : "+addedSpares);
		System.out.println("addProduct => productAdded : "+added+" ,addedSkills : "+addedSkills+" ,addedSpares : "+addedSpares);
		if(added==true && addedSkills==true && addedSpares==true) {
			addProductStatus="success";
			return SUCCESS;
		}
		else {
			addProductStatus="error";
			return ERROR;
		}
	}

	public Map<String, Integer> getCapacityMap() {
		return capacityMap;
	}

	public void setCapacityMap(Map<String, Integer> capacityMap) {
		this.capacityMap = capacityMap;
	}

	public Map<Integer, Spare> getSpareMap() {
		return spareMap;
	}

	public void setSpareMap(Map<Integer, Spare> spareMap) {
		this.spareMap = spareMap;
	}

	public Map<Integer, String> getSkillMap() {
		return skillMap;
	}

	public void setSkillMap(Map<Integer, String> skillMap) {
		this.skillMap = skillMap;
	}

	public Map<Integer, Product> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Integer, Product> productMap) {
		this.productMap = productMap;
	}

	public ArrayList<String> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	public ArrayList<Product> getProductList(ArrayList<Product> productList) {
		return productList;
	}
	
}
