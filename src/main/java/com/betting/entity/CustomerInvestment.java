package com.betting.entity;

public class CustomerInvestment {
	
	private int customerId;
	private double investment;
	
	public CustomerInvestment(int customerId, double investment) {
		this.customerId = customerId;
		this.investment = investment;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getInvestment() {
		return investment;
	}
	public void setInvestment(double investment) {
		this.investment = investment;
	}

}
