package com.product.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CapacityPlanningService {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public int getTotalOrdersCount(int productId) {
		int ordersCount = 0;
		try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
				PreparedStatement ps = con.prepareStatement("SELECT SUM(productQuantity) as TotalQuantity FROM Orders WHERE productId = ? AND MONTH(DueDate) = MONTH(CURRENT_DATE()) AND YEAR(DueDate) = YEAR(CURRENT_DATE()) and StatusId=1");
				ps.setInt(1, productId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					ordersCount=rs.getInt(1);
				}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return ordersCount;
		}
	}
	
	public int getWorkingHoursToProduceSingleProduct(int productId) {
		int workingHours=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("SELECT workingHoursToProduceSingleProd FROM Product WHERE productId = ?");
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				workingHours=rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return workingHours;
		}
	}
	
	public int getTeamSize(int productId) {
		int teamSize=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("select count(*) as teamSize from employee where teamId=(select teamId from employee where empId=(select managerId from product where productId=?))");
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				teamSize=rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return teamSize;
		}
	}
	
	public Map<String,Integer> planCapacity(int productId) {
		Map<String,Integer> capacityMap=new HashMap<String,Integer>();
		int ordersCount=getTotalOrdersCount(productId);
		int workingHoursForSingleProd=getWorkingHoursToProduceSingleProduct(productId);
		int teamSize=getTeamSize(productId);
		int productPerDay=(int)(Math.ceil((teamSize*8)/workingHoursForSingleProd));
		int DaysRequired=(int) ((ordersCount*workingHoursForSingleProd)/(teamSize*8));
		capacityMap.put("workingHoursForSingleProd",workingHoursForSingleProd);
		capacityMap.put("teamSize", teamSize);
		capacityMap.put("totalOrders", ordersCount);
		capacityMap.put("productPerDay", productPerDay);
		capacityMap.put("DaysRequired", DaysRequired);
		return capacityMap;
		
	}
}
