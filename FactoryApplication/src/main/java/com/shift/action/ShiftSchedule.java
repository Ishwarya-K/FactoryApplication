package com.shift.action;

import java.sql.Date;

public class ShiftSchedule {
	private int empId, numOfHrs;
	private Date shiftDate;
	private String startTime, endTime,empName;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getNumOfHrs() {
		return numOfHrs;
	}
	public void setNumOfHrs(int numOfHrs) {
		this.numOfHrs = numOfHrs;
	}
	public Date getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
