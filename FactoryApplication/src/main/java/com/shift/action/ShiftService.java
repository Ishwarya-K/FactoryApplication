package com.shift.action;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.product.action.CapacityPlanningService;
import com.product.action.Product;
import com.product.action.ProductService;

public class ShiftService {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public Map<Integer,Map<String,Integer>> getCapacityPlan(){
		Map<Integer,Map<String,Integer>> allCapacityMap=new HashMap<Integer,Map<String,Integer>>();
		Map<String, Integer> capacityMap = new HashMap<String, Integer>();
		CapacityPlanningService capacityPlanService=new CapacityPlanningService();
		ProductService prdService=new ProductService();
		ArrayList<Product> productList=prdService.getProducts();
		for (Product product : productList) {
			int productId=product.getProductId();
			System.out.println("prod Id : "+productId);
			capacityMap = capacityPlanService.planCapacity(productId);
			allCapacityMap.put(productId, capacityMap);
		}
		System.out.println(allCapacityMap);
		return allCapacityMap;
	}
	
	public int getTeamId(int productId) {
		int teamId=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("select teamId from employee where empId=(select managerId from product where productId=?)");
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				teamId=rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return teamId;
		}
	}
	
	public ArrayList<Integer> getEmployees(int teamId){
		ArrayList<Integer> employeeList=new ArrayList<Integer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("select empId from employee where teamId=?");
			ps.setInt(1, teamId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				employeeList.add(rs.getInt(1));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return employeeList;
		}
	}
	
	public String getTeamName(int teamId) {
		String teamName="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("select teamName from team where teamId=?");
			ps.setInt(1, teamId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				teamName=rs.getString(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return teamName;
		}
	}
	
	public String getEmpName(int empId) {
		String empName="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("select empName from employee where empId=?");
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				empName=rs.getString(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return empName;
		}
	}
	
	public String getProductName(int productId) {
		String productName="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("select productName from product where productId=?");
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				productName=rs.getString(1);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return productName;
		}
	}
	
	public Map<Integer,Shift> getEmployeeWorkingHours(){
		Map<Integer,Shift> employeeWorkingHrs=new HashMap<Integer,Shift>();
		Map<Integer,Map<String,Integer>> allCapacityMap=getCapacityPlan();
		for(int productId : allCapacityMap.keySet()) {
			String productName=getProductName(productId);
			Map<String,Integer> capacityMap = allCapacityMap.get(productId);
			int workingHrs=capacityMap.get("workingHoursForSingleProd");
			int teamSize=capacityMap.get("teamSize");
			int totalOrders=capacityMap.get("totalOrders");
			int productPerDay=capacityMap.get("productPerDay");
			int daysRequired=capacityMap.get("DaysRequired");
			int teamId=getTeamId(productId);
			String teamName=getTeamName(teamId);
			System.out.println("teamId : "+teamId+" teamSize : "+teamSize);
			ArrayList<Integer> employeeList=getEmployees(teamId);
			for(Integer empId:employeeList) {
				String empName=getEmpName(empId);
				Shift shift = new Shift();
				shift.setEmpId(empId);
				shift.setTeamId(teamId);
				shift.setWorkingHrs((workingHrs*totalOrders)/teamSize);
				shift.setEmpName(empName);
				shift.setTeamName(teamName);
				shift.setProductName(productName);
				int extraHrs=((workingHrs*totalOrders)/teamSize)-(22*8);
				if(extraHrs<0) {
					extraHrs=0;
				}
				shift.setExtraHrs(extraHrs);
				shift.setProductId(productId);
				employeeWorkingHrs.put(empId,shift);
				System.out.println("product id : "+productId+" empId : "+empId);
			}
		}
		System.out.println("allCapacityMap : "+allCapacityMap+" empWorking hrs : "+employeeWorkingHrs);
		return employeeWorkingHrs;
		
	}
	
	public boolean addShift(int empId, Date shiftDate, int numOfHrs, String startTime, String endTime) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps=con.prepareStatement("Insert into extraShift(empId,shiftDate,numOfHours, startTime, endTime) values(?,?,?,?,?);");
			ps.setInt(1,empId);
			ps.setDate(2,shiftDate);
			ps.setInt(3, numOfHrs);
			ps.setString(4, startTime);
			ps.setString(5, endTime);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
			return false;
		}
	}
	
	public Map<Integer,ShiftSchedule> getShiftScheduleMap(){
		Map<Integer,ShiftSchedule> shiftScheduleMap = new HashMap<Integer,ShiftSchedule>();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select * from ExtraShift es left join employee e on es.empId=e.empId");
			ResultSet rs = ps.executeQuery();
			int idx=1;
			while(rs.next()) {
				ShiftSchedule shiftSchedule=new ShiftSchedule();
				shiftSchedule.setEmpName(rs.getString("empName"));
				shiftSchedule.setEmpId(rs.getInt("empId"));
				shiftSchedule.setShiftDate(rs.getDate("shiftDate"));
				shiftSchedule.setNumOfHrs(rs.getInt("numOfHours"));
				shiftSchedule.setStartTime(rs.getString("startTime"));
				shiftSchedule.setEndTime(rs.getString("endTime"));
				shiftScheduleMap.put(idx, shiftSchedule);
				idx++;
				
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return shiftScheduleMap;
		}
		
	}
	
	public Map<Integer,TeamShift> getTeamShiftDetails(){
		ProductService prdService=new ProductService();
		CapacityPlanningService capacityPlanService = new CapacityPlanningService();
		Map<Integer,TeamShift> teamShiftMap =new HashMap<Integer,TeamShift>();
		ArrayList<Product> productList=prdService.getProducts();
		for (Product product : productList) {
			int productId=product.getProductId();
			int teamId=getTeamId(productId);
			String teamName=getTeamName(teamId);
			int totalOrdersCount=capacityPlanService.getTotalOrdersCount(productId);
			int workingHrForSingleProd=capacityPlanService.getWorkingHoursToProduceSingleProduct(productId);
			int teamSize=capacityPlanService.getTeamSize(productId);
			int totalWorkingHours=totalOrdersCount*workingHrForSingleProd;
			TeamShift teamShift = new TeamShift();
			teamShift.setTeamId(teamId);
			teamShift.setTeamName(teamName);
			teamShift.setTeamSize(teamSize);
			int numOfEightHourShift=totalWorkingHours/8;
			teamShift.setNumOfEightHourShift(numOfEightHourShift);
			int numOfSixHourShift=totalWorkingHours/6;
			teamShift.setNumOfSixHourShift(numOfSixHourShift);
			int membersRequired=0;
			int extraMembersCount=0;
			if(numOfEightHourShift>(teamSize*22)) {
				membersRequired=(numOfEightHourShift-(teamSize*22))/22;
			}
			else if(numOfEightHourShift<(teamSize*22)) {
				extraMembersCount=((teamSize*22)-numOfEightHourShift)/22;
			}
			teamShift.setExtraMembersAvailableCount(extraMembersCount);
			teamShift.setExtraMembersRequiredCount(membersRequired);
			teamShiftMap.put(teamId, teamShift);
		}
		return teamShiftMap;
	}
}
