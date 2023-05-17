package com.shift.action;

import java.sql.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class ShiftController extends ActionSupport{
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Map<Integer,Shift> shiftPlan;
	private int empId,numOfHrs;
	private String shiftDate;
	private String startTime,endTime;
	private Map<Integer,ShiftSchedule> shiftScheduleMap;
	private Map<Integer,TeamShift> teamShiftMap;
	public Map<Integer, TeamShift> getTeamShiftMap() {
		return teamShiftMap;
	}

	public void setTeamShiftMap(Map<Integer, TeamShift> teamShiftMap) {
		this.teamShiftMap = teamShiftMap;
	}

	public int getEmpId() {
		return empId;
	}

	public void setShiftScheduleMap(Map<Integer, ShiftSchedule> shiftScheduleMap) {
		this.shiftScheduleMap = shiftScheduleMap;
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

	public String getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(String shiftDate) {
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

	public String getShiftPlanMap() {
		ShiftService shiftService = new ShiftService();
		shiftPlan=shiftService.getEmployeeWorkingHours();
		logger.log(Level.INFO, "shiftPlan : "+shiftPlan);
		System.out.println("shiftPlan : "+shiftPlan);
		if(shiftPlan.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
		
	}
	
	public String addShift() {
		ShiftService shiftService=new ShiftService();
		Date shift=Date.valueOf(shiftDate);
		boolean insertedShift=shiftService.addShift(empId, shift, numOfHrs, startTime, endTime);
		if(insertedShift) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
		
	}
	
	public String getShiftSchedule() {
		ShiftService shiftService = new ShiftService();
		shiftScheduleMap=shiftService.getShiftScheduleMap();
		logger.log(Level.INFO, "shiftScheduleMap : "+shiftScheduleMap);
		System.out.println("shiftScheduleMap : "+shiftScheduleMap);
		if(shiftScheduleMap.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public String getTeamShiftDetails() {
		ShiftService shiftService=new ShiftService();
		teamShiftMap=shiftService.getTeamShiftDetails();
		if(teamShiftMap.size()>0) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
	
	public Map<Integer, ShiftSchedule> getShiftScheduleMap() {
		return shiftScheduleMap;
	}

	public Map<Integer, Shift> getShiftPlan() {
		return shiftPlan;
	}
	public void setShiftPlan(Map<Integer, Shift> shiftPlan) {
		this.shiftPlan = shiftPlan;
	}
	
}
