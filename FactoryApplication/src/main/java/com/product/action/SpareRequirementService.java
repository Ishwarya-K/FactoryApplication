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

public class SpareRequirementService {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public ArrayList<Spare> getSparesList(){
		ArrayList<Spare> spareList=new ArrayList<Spare>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select * from ProductToSpareRel prdSpare left join MachineAndSpare machSpare on prdSpare.spareId=machSpare.spareId");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Spare spare = new Spare();
				spare.setSpareId(rs.getInt("spareId"));
				spare.setSpareName(rs.getString("spareName"));
				spare.setPrice(rs.getInt("price"));
				spare.setQuantityToProduceSingleProd(rs.getInt("spareQuantity"));
				spare.setAvailableQuantity(rs.getInt("availableQuantity"));
				
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			return spareList;
		}
		
	}
	
	public Map<Integer,Spare> getSpareMap(int productId){
		Map<Integer,Spare> spareMap = new HashMap<Integer,Spare>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select * from ProductToSpareRel prdSpare left join MachineAndSpare machSpare on prdSpare.spareId=machSpare.spareId where prdSpare.productId=?");
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Spare spare = new Spare();
				spare.setSpareId(rs.getInt("spareId"));
				spare.setSpareName(rs.getString("spareName"));
				spare.setPrice(rs.getInt("price"));
				spare.setQuantityToProduceSingleProd(rs.getInt("spareQuantity"));
				spare.setAvailableQuantity(rs.getInt("availableQuantity"));
				spareMap.put(spare.getSpareId(), spare);
				
			}
			con.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
		}
		finally {
			System.out.println("Sparemap = "+spareMap);
			return spareMap;
		}
	}
}
