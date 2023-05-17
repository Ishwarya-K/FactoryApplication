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

import com.opensymphony.xwork2.ActionSupport;

public class SkillService extends ActionSupport{
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public ArrayList<String> getProductSkills(int productId){
		ArrayList<String> skills = new ArrayList<String>();
		try {
			System.out.println(productId);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("select s.skillName from productToSkillRel ps left join Skill s on ps.skill_id=s.skill_id where productId=?");
			ps.setInt(1, productId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				skills.add(rs.getString(1));
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return skills;
		}
		
	}
	
	public Map<Integer,String> getSkillsMap(int productId){
		Map<Integer,String> skillMap = new HashMap<Integer,String>();
		ArrayList<String> skills=getProductSkills(productId);
		int skillCount=skills.size();
		for(int idx=0;idx<skillCount;idx++) {
			skillMap.put(idx, skills.get(idx));
		}
		return skillMap;
		
	}
}
