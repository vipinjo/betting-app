package com.betting.entity;

import java.util.List;

public class BetReport {

	private List<CustomerInvestment> totalInvestmentPerCustomerID;
	private List<BetTypeInvestment> totalInvestmentPerBetType;
	private List<BetTypeSold> totalBetsSoldPerBetType;
	private List<BetSoldPerHour> totalBetsSoldPerHour;

	public List<CustomerInvestment> getTotalInvestmentPerCustomerID() {
		return totalInvestmentPerCustomerID;
	}

	public void setTotalInvestmentPerCustomerID(List<CustomerInvestment> totalInvestmentPerCustomerID) {
		this.totalInvestmentPerCustomerID = totalInvestmentPerCustomerID;
	}

	public List<BetTypeInvestment> getTotalInvestmentPerBetType() {
		return totalInvestmentPerBetType;
	}

	public void setTotalInvestmentPerBetType(List<BetTypeInvestment> totalInvestmentPerBetType) {
		this.totalInvestmentPerBetType = totalInvestmentPerBetType;
	}

	public List<BetTypeSold> getTotalBetsSoldPerBetType() {
		return totalBetsSoldPerBetType;
	}

	public void setTotalBetsSoldPerBetType(List<BetTypeSold> totalBetsSoldPerBetType) {
		this.totalBetsSoldPerBetType = totalBetsSoldPerBetType;
	}

	public List<BetSoldPerHour> getTotalBetsSoldPerHour() {
		return totalBetsSoldPerHour;
	}

	public void setTotalBetsSoldPerHour(List<BetSoldPerHour> totalBetsSoldPerHour) {
		this.totalBetsSoldPerHour = totalBetsSoldPerHour;
	}

	

}
