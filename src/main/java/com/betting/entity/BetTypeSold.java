package com.betting.entity;

public class BetTypeSold {
	
	private String betType;
	private int quantitySold;
	
	public BetTypeSold(String betType, int quantitySold) {
		this.betType = betType;
		this.quantitySold = quantitySold;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

}
