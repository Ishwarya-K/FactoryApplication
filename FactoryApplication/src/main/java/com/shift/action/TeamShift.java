package com.shift.action;

public class TeamShift {
	int teamId, teamSize,numOfEightHourShift,numOfSixHourShift,extraMembersRequiredCount,extraMembersAvailableCount;
	String teamName;
	
	public int getExtraMembersRequiredCount() {
		return extraMembersRequiredCount;
	}
	public void setExtraMembersRequiredCount(int extraMembersRequiredCount) {
		this.extraMembersRequiredCount = extraMembersRequiredCount;
	}
	public int getExtraMembersAvailableCount() {
		return extraMembersAvailableCount;
	}
	public void setExtraMembersAvailableCount(int extraMembersAvailableCount) {
		this.extraMembersAvailableCount = extraMembersAvailableCount;
	}
	public int getTeamId() {
		return teamId;
	}   
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	public int getNumOfEightHourShift() {
		return numOfEightHourShift;
	}
	public void setNumOfEightHourShift(int numOfEightHourShift) {
		this.numOfEightHourShift = numOfEightHourShift;
	}
	public int getNumOfSixHourShift() {
		return numOfSixHourShift;
	}
	public void setNumOfSixHourShift(int numOfSixHourShift) {
		this.numOfSixHourShift = numOfSixHourShift;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}
