package com.shift.action;

public class Shift {
	private int empId, teamId, productId, workingHrs, extraHrs;
	private String empName, teamName,productName;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getWorkingHrs() {
		return workingHrs;
	}

	public void setWorkingHrs(int workingHrs) {
		this.workingHrs = workingHrs;
	}

	public int getExtraHrs() {
		return extraHrs;
	}

	public void setExtraHrs(int extraHrs) {
		this.extraHrs = extraHrs;
	}
	
}
