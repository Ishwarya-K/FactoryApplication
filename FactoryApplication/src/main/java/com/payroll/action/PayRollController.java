package com.payroll.action;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.servletcomponents.ServletActionContext;

//import org.apache.struts2.components.ActionComponent;

import com.opensymphony.xwork2.ActionSupport;

public class PayRollController extends ActionSupport{
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	//private String userId;
	//private String passwd;
	//private boolean isValidLogin;
	private String addEmployeeStatus;
	private int empId;
	private String empName;
	private String team;
	private String mentor;
	private String payGrade;
	private String dob;
	private String joiningDate;
	private String phoneNo, email, degree,teamName,skills,role;
	private String date;
	private Map<Integer,Attendance> attendanceMap;
	public String getAddEmployeeStatus() {
		return addEmployeeStatus;
	}

	public void setAddEmployeeStatus(String addEmployeeStatus) {
		this.addEmployeeStatus = addEmployeeStatus;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getMentor() {
		return mentor;
	}

	public void setMentor(String mentor) {
		this.mentor = mentor;
	}

	public String getPayGrade() {
		return payGrade;
	}

	public void setPayGrade(String payGrade) {
		this.payGrade = payGrade;
	}
	private Map<Integer,Employee> employeeMap;

	/*
	 * public String validateLogin() { PayRollService payrollService = new
	 * PayRollService(); HttpSession session =
	 * ServletActionContext.getRequest().getSession();
	 * session.setAttribute("userId", userId); isValidLogin =
	 * payrollService.isValidLogin(userId, passwd);
	 * System.out.println("User id : "+session.getAttribute("userId"));
	 * logger.log(Level.INFO, "IsValid Login :"+isValidLogin); if(isValidLogin) {
	 * return SUCCESS; } else { return ERROR; } }
	 */
	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidates the session
        }
        return SUCCESS;
	}
	
	public String getEmployeeDetails() {
		System.out.println("empId: "+empId+" empName: "+empName+" team:"+team+" mentor:"+mentor+" payGrade: "+payGrade);
		//System.out.println(empName.length()+" "+team.length());
		PayRollService payrollService = new PayRollService();
		if(empId!=0 ||  empName!=null &&(empName.length()>0) ||team!=null &&(team.length()>0) ||mentor!=null &&(mentor.length()>0)  ||payGrade!=null &&(payGrade.length()>0) ) {
			employeeMap=payrollService.getEmployeeDetails(empId,empName,team,mentor,payGrade);
		}
		else {
			employeeMap=payrollService.getEmployeeDetails();
		}
		logger.log(Level.INFO, "Employee Details : "+employeeMap);
		if(employeeMap.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	
	public String addEmployee() throws ParseException {
		PayRollService payrollService = new PayRollService();
		System.out.println("dob : "+dob+" joiningDate : "+joiningDate);
		 //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 //Date dobDate = (Date) dateFormat.parse(dob);
	     //Date joinDate = dateFormat.parse(joiningDate);
		Date dobDate=Date.valueOf(dob);
		Date joinDate=Date.valueOf(joiningDate);
		boolean validateSkills=payrollService.validateSkills(skills);
		if(!validateSkills) {
			addEmployeeStatus="error";
			return ERROR;
		}
		boolean addedEmployee=payrollService.addEmployee(empName, dobDate, phoneNo, email, degree, teamName, skills, joinDate, payGrade, role, mentor);
		int employeeId=payrollService.getMentorId(empName);
		boolean addedEmpSkill=payrollService.insertSkills(employeeId, skills);
		System.out.println("added : "+addedEmployee);
		if(addedEmployee && addedEmpSkill) {
			logger.log(Level.INFO, "Employee added successfully : emp id - "+employeeId);
			addEmployeeStatus="success";
			return SUCCESS;
		}
		else {
			logger.log(Level.SEVERE, "Failed to add employee : emp id - "+employeeId);
			addEmployeeStatus="error";
			return ERROR;
		}
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAttendance() {
		PayRollService payrollService=new PayRollService();
		if(date!=null) {
			attendanceMap=payrollService.getAttendance(empId, empName, team, date);
		}
		else {
			attendanceMap=payrollService.getAttendance();
		}
		logger.log(Level.INFO, "Attendance data : "+attendanceMap);
		if(attendanceMap.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public Map<Integer, Attendance> getAttendanceMap() {
		return attendanceMap;
	}

	public void setAttendanceMap(Map<Integer, Attendance> attendanceMap) {
		this.attendanceMap = attendanceMap;
	}

	public Map<Integer, Employee> getEmployeeMap() {
		return employeeMap;
	}

	public void setEmployeeMap(Map<Integer, Employee> employeeMap) {
		this.employeeMap = employeeMap;
	}

	/*
	 * public boolean isValidLogin() { return isValidLogin; } public void
	 * setValidLogin(boolean isValidLogin) { this.isValidLogin = isValidLogin; }
	 * public String getUserId() { return userId; } public void setUserId(String
	 * userId) { this.userId = userId; } public String getPasswd() { return passwd;
	 * } public void setPasswd(String passwd) { this.passwd = passwd; }
	 */
}
