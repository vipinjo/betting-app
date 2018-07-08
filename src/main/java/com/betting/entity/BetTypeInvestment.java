package com.betting.entity;

public class BetTypeInvestment {
	
	private String betType;
	private double investment;
	
	public BetTypeInvestment(String betType, double investment) {
		this.betType = betType;
		this.investment = investment;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public double getInvestment() {
		return investment;
	}
	public void setInvestment(double investment) {
		this.investment = investment;
	}

}
