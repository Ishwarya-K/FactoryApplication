package com.payroll.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayRollService {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public boolean isValidLogin(String userId,String password) {
		boolean isValidLogin = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select empId from Employee  where email=? and password=md5(?)");
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int empId=rs.getInt(1);
				isValidLogin=true;
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception while login : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return isValidLogin;
		}
	}
	
	public Map<Integer,Employee> getEmployeeDetails(){
		Map<Integer,Employee> employeeMap=new HashMap<Integer,Employee>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select e.empId,e.empName,e.DOB,e.phoneNo,e.email,teamName,roleName,e2.empName as mentor,e.joiningDate,e.payGrade from Employee e left join team on e.teamId=team.teamId left join role on e.roleId=role.roleId left join employee e2 on e.empId=e2.empId;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Employee emp=new Employee();
				emp.setEmpId(rs.getInt("empId"));
				emp.setEmpName(rs.getString("empName"));
				emp.setDob(rs.getDate("DOB"));
				emp.setPhoneNo(rs.getString("phoneNo"));
				emp.setTeam(rs.getString("teamName"));
				emp.setRole(rs.getString("roleName"));
				emp.setMentor(rs.getString("mentor"));
				emp.setPayGrade(rs.getString("PayGrade"));
				emp.setJoiningDate(rs.getDate("joiningDate"));
				emp.setEmail(rs.getString("email"));
				employeeMap.put(rs.getInt("empId"), emp);
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in getEmployeeDetails : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return employeeMap;
		}
	}
	public Map<Integer,Employee> getEmployeeDetails(int empId,String empName,String team,String mentor, String payGrade){
		Map<Integer,Employee> employeeMap=new HashMap<Integer,Employee>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			//PreparedStatement ps = con.prepareStatement("Select e.empId,e.empName,e.DOB,e.phoneNo,e.email,teamName,roleName,e2.empName as mentor,e.joiningDate,e.payGrade from Employee e left join team on e.teamId=team.teamId left join role on e.roleId=role.roleId left join employee e2 on e.empId=e2.empId;");
			boolean hasConditionBefore=false;
			String query="Select e.empId,e.empName,e.DOB,e.phoneNo,e.email,teamName,roleName,e2.empName as mentor,e.joiningDate,e.payGrade from Employee e left join team on e.teamId=team.teamId left join role on e.roleId=role.roleId left join employee e2 on e.empId=e2.empId";
			if(empId!=0) {
				query+=" where e.empId="+empId;
				hasConditionBefore=true;
			}
			if(empName.length()>0) {
				if(!hasConditionBefore) {
					query+=" where ";
					hasConditionBefore=true;
				}
				else {
					query+=" and ";
				}
				query+=" e.empname ='"+empName+"'";
			}
			if(team.length()>0) {
				if(!hasConditionBefore) {
					query+=" where ";
					hasConditionBefore=true;
				}
				else {
					query+=" and ";
				}
				query+=" teamName ='"+team+"'";
			}
			if(mentor.length()>0) {
				if(!hasConditionBefore) {
					query+=" where ";
					hasConditionBefore=true;
				}
				else {
					query+=" and ";
				}
				query+=" e2.empName ='"+mentor+"'";
			}
			if(payGrade.length()>0) {
				if(!hasConditionBefore) {
					query+=" where ";
					hasConditionBefore=true;
				}
				else {
					query+=" and ";
				}
				query+=" e.payGrade ='"+payGrade+"'";
			}
			logger.log(Level.INFO, "getEmployeeDetails query : "+query);
			PreparedStatement ps = con.prepareStatement(query);
			
			System.out.println("Query : "+query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Employee emp=new Employee();
				emp.setEmpId(rs.getInt("empId"));
				emp.setEmpName(rs.getString("empName"));
				emp.setDob(rs.getDate("DOB"));
				emp.setPhoneNo(rs.getString("phoneNo"));
				emp.setTeam(rs.getString("teamName"));
				emp.setRole(rs.getString("roleName"));
				emp.setMentor(rs.getString("mentor"));
				emp.setPayGrade(rs.getString("PayGrade"));
				emp.setJoiningDate(rs.getDate("joiningDate"));
				emp.setEmail(rs.getString("email"));
				employeeMap.put(rs.getInt("empId"), emp);
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in getEmployeeDetails : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return employeeMap;
		}
	}
	
	public int getEmpTeamId(String teamName) {
		int teamId=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select teamId from team where teamName=? ");
			ps.setString(1, teamName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				teamId=rs.getInt(1);
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in getEmpTeamId : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return teamId;
		}
	}
	
	public int getEmpRoleId(String roleName) {
		int roleId=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select roleId from role where roleName=? ");
			ps.setString(1, roleName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				roleId=rs.getInt(1);
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in getEmpRoleId : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return roleId;
		}
	}
	public int getMentorId(String mentorName) {
		int mentorId=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("Select empId from employee where empName=? ");
			ps.setString(1, mentorName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				mentorId=rs.getInt(1);
			}
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in getEmployeeDetailsMentorId : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return mentorId;
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
			logger.log(Level.SEVERE, "Exception in getSkillId : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return skillId;
		}
	}
	
	public void insertSkill(int empId,int skillId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps=con.prepareStatement("Insert into employeeToSkillRel(empId,skill_Id) values(?,?);");
			ps.setInt(1,empId);
			ps.setInt(2,skillId);
			ps.executeUpdate();
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in insert skill : "+e.getMessage());
			System.out.println(e.getMessage());
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
	
	public boolean insertSkills(int empId, String skillStr) {
		try {
			String[] skills = skillStr.split("[,]", 0);
			for(String skill: skills) {
		          int skillId=getSkillId(skill);
		          insertSkill(empId,skillId);
		    }
			return true;
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in inserting Skills : "+e.getMessage());
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean addEmployee(String empName, Date dob, String phoneNo, String email, String degree, String teamName, String skills, Date joiningDate, String payGrade, String role, String mentor) {
		  try {
			  	System.out.println("empName: "+empName+" dob: "+dob+" phoneNo: "+phoneNo+" email: "+email+" degree: "+degree+" teamName:"+teamName+" skills: "+skills+" joiningDate: "+joiningDate+" role: "+role+" mentor: "+mentor);
				int teamId=getEmpTeamId(teamName);
				int roleId=getEmpRoleId(role);
				int mentorId=getMentorId(mentor);
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
				PreparedStatement ps=con.prepareStatement("Insert into employee(empName,DOB,Degree,phoneNo,email,teamId,roleId,reportingTo,joiningDate,payGrade) values(?,?,?,?,?,?,?,?,?,?);");
				ps.setString(1,empName);
				ps.setDate(2,dob);
				ps.setString(3,degree);
				ps.setString(4,phoneNo);
				ps.setString(5,email);
				ps.setInt(6,teamId);
				ps.setInt(7, roleId);
				ps.setInt(8, mentorId);
				ps.setDate(9, joiningDate);
				ps.setString(10, payGrade);
				ps.executeUpdate();
				return true;
			}
			catch(Exception e) {
				logger.log(Level.SEVERE, "Exception in addEmployee : "+e.getMessage());
				System.out.println(e.getMessage());
				return false;
			}
	}
	
	public Map<Integer,Attendance> getAttendance(){
		Map<Integer,Attendance> attendanceMap = new HashMap<Integer,Attendance>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			PreparedStatement ps = con.prepareStatement("SELECT e.empId, e.empName, teamName, CURDATE() as date, IF(a.AbsentDate IS NULL, 'present', 'absent') as status FROM Employee e LEFT JOIN Absentees a ON e.empId = a.empId AND a.AbsentDate = CURDATE() left join team on e.teamId=team.teamId");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Attendance attendance=new Attendance();
				attendance.setEmpId(rs.getInt("empId"));
				attendance.setEmpName(rs.getString("empName"));
				attendance.setTeam(rs.getString("teamName"));
				attendance.setDate(rs.getString("date"));
				attendance.setStatus(rs.getString("status"));
				attendanceMap.put(attendance.getEmpId(), attendance);
				
			}
			System.out.println(attendanceMap);
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in getAttendance : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return attendanceMap;
		}
	}
	
	public Map<Integer,Attendance> getAttendance(int empId, String empName, String team, String date){
		Map<Integer,Attendance> attendanceMap = new HashMap<Integer,Attendance>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory","root","");
			//PreparedStatement ps = con.prepareStatement("SELECT e.empId, e.empName, teamName, CURDATE() as date, IF(a.AbsentDate IS NULL, 'present', 'absent') as status FROM Employee e LEFT JOIN Absentees a ON e.empId = a.empId AND a.AbsentDate = CURDATE() left join team on e.teamId=team.teamId");
			boolean hasConditionBefore=false;
			Date attendanceDate=Date.valueOf(date);
			String query="SELECT e.empId, e.empName, teamName, ? as date, IF(a.AbsentDate IS NULL, 'present', 'absent') as status FROM Employee e LEFT JOIN Absentees a ON e.empId = a.empId AND a.AbsentDate = ? left join team on e.teamId=team.teamId";
			if(empId!=0) {
				if(!hasConditionBefore) {
					query+=" where ";
					hasConditionBefore=true;
				}
				else {
					query+=" and ";
				}
				query+=" e.empId='"+empId+"'";
			}
			if(team.length()>0) {
				if(!hasConditionBefore) {
					query+=" where ";
					hasConditionBefore=true;
				}
				else {
					query+=" and ";
				}
				query+=" teamName='"+team+"'";
			}
			if(empName.length()>0) {
				if(!hasConditionBefore) {
					query+=" where ";
					hasConditionBefore=true;
				}
				else {
					query+=" and ";
				}
				query+=" e.empName='"+empName+"'";
			}
			logger.log(Level.INFO, "getAttendance query : "+query);
			System.out.println("Attendance Query : "+query);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDate(1, attendanceDate);
			ps.setDate(2, attendanceDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Attendance attendance=new Attendance();
				attendance.setEmpId(rs.getInt("empId"));
				attendance.setEmpName(rs.getString("empName"));
				attendance.setTeam(rs.getString("teamName"));
				attendance.setDate(rs.getString("date"));
				attendance.setStatus(rs.getString("status"));
				attendanceMap.put(attendance.getEmpId(), attendance);
				
			}
			System.out.println(attendanceMap);
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "Exception in getAttendance : "+e.getMessage());
			System.out.println(e.getMessage());
		}
		finally {
			return attendanceMap;
		}
	}
	
}
