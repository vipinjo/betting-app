package com.betting.entity;

public class BetSoldPerHour {
	
	private String startTime;
	private String endTime;
	private int count;
	
	public BetSoldPerHour(String startTime, String endTime, int count) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.count = count;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
