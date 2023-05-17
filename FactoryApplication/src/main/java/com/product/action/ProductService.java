package com.product.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductService {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public ArrayList<Product> getProducts(){
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select * from Product prd left join ProductCategory ctg on prd.category=ctg.categoryId");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product prd=new Product();
				prd.setProductId(rs.getInt("productId"));
				prd.setProductName(rs.getString("productName"));
				prd.setCategory(rs.getString("categoryName"));
				prd.setAvailableQuantity(rs.getInt("availableQuantity"));
				prd.setPrice(rs.getInt("price"));
				prd.setWorkingHrsForSingleProd(rs.getInt("workingHoursToProduceSingleProd"));
				prd.setManagerId(rs.getInt("managerId"));
				productList.add(prd);
				System.out.println("porduct id : "+prd.getProductId());
				
			}
			con.close();
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return productList;
		}
		
	}
	
	public Map<Integer,Product> getProductsMap(){
		Map<Integer,Product> prdMap = new HashMap<Integer,Product>();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select * from Product prd left join ProductCategory ctg on prd.category=ctg.categoryId");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product prd=new Product();
				prd.setProductId(rs.getInt("productId"));
				prd.setProductName(rs.getString("productName"));
				prd.setCategory(rs.getString("categoryName"));
				prd.setAvailableQuantity(rs.getInt("availableQuantity"));
				prd.setPrice(rs.getInt("price"));
				prd.setWorkingHrsForSingleProd(rs.getInt("workingHoursToProduceSingleProd"));
				prd.setManagerId(rs.getInt("managerId"));
				prdMap.put(prd.getProductId(), prd);
				
			}
			con.close();
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return prdMap;
		}
		
	}
	
	public int getProductCategoryId(String category) {
		int categoryId = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select categoryId from ProductCategory  where categoryName=?");
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				categoryId=rs.getInt(1);
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return categoryId;
		}
	}
	
	public boolean addProduct(String productName, String category, int availableQuantity, int price, int workingHrs, int managerId) {
		try {
			int categoryId=getProductCategoryId(category);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps=con.prepareStatement("Insert into product(productName, category, availableQuantity, price, workingHoursToProduceSingleProd,managerId) values(?,?,?,?,?,?);");
			ps.setString(1,productName);
			ps.setInt(2,categoryId);
			ps.setInt(3,availableQuantity);
			ps.setInt(4,price);
			ps.setInt(5,workingHrs);
			ps.setInt(6,managerId);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
			System.out.println(e.getMessage());
			return false;
		}
	
	}
	
	public int getProductId(String productName) {
		int productId = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select productId from Product  where productName=?");
			ps.setString(1, productName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				productId=rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return productId;
		}
	}
	public int getSkillId(String skill) {
		int skillId=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select skill_Id from skill where skillName=? ");
			ps.setString(1, skill);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				skillId=rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return skillId;
		}
	}
	
	public void insertSkill(int prodId,int skillId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps=con.prepareStatement("Insert into productToSkillRel(productId,skill_Id) values(?,?);");
			ps.setInt(1,prodId);
			ps.setInt(2,skillId);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
	}
	
	public boolean validateSkills(String skillStr) {
		String[] skills = skillStr.split("[,]", 0);
		for(String skill: skills) {
	          int skillId=getSkillId(skill);
	          if(skillId==0) {
	        	  return false;
	          }
	    }
		return true;
	}
	public boolean addSkills(String productName, String skillStr) {
		int prodId=getProductId(productName);
		try {
			String[] skills = skillStr.split("[,]", 0);
			for(String skill: skills) {
		          int skillId=getSkillId(skill);
		          insertSkill(prodId,skillId);
		    }
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
			return false;
		}
	}
	
	public int getSpareId(String spareName) {
		int spareId=0;
		try {
			System.out.println("spare Name : "+spareName);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select spareId from machineAndSpare where spareName=? ");
			ps.setString(1, spareName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				spareId=rs.getInt(1);
				System.out.println("spareId : "+spareId);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return spareId;
		}
	}
	
	public void insertSpare(int productId, int spareId, int spareQuantity) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps=con.prepareStatement("Insert into productToSpareRel(productId,spareId,spareQuantity) values(?,?,?);");
			ps.setInt(1,productId);
			ps.setInt(2,spareId);
			ps.setInt(3, spareQuantity);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
	}
	
	public boolean validateSpare(String spareStr) {
		String[] spares = spareStr.split("[,]", 0);
		for(String spare: spares) {
	          int spareId=getSpareId(spare);
	          if(spareId==0) {
	        	  return false;
	          }
	    }
		return true;
	}
	
	public boolean addSpare(String productName, String spareStr, int spareQuantity) {
		int prodId=getProductId(productName);
		try {
			String[] spares = spareStr.split("[,]", 0);
			for(String spare: spares) {
		          int spareId=getSpareId(spare);
		          insertSpare(prodId,spareId,spareQuantity);
		    }
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
			return false;
		}
	}
}
