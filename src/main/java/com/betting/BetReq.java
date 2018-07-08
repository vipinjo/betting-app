package com.betting;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class BetReq {

	@NotNull(message = "Please provide date")
	@NotEmpty
	private String dateTime;
	
	@NotNull(message = "Please provide bet Type")
	@NotEmpty
	@Pattern(regexp = "WIN|PLACE|TRIFECTA|DOUBLE|QUADDIE", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String betType;
	
	@NotNull(message = "Please provide property number")
	private Integer propNumber;
	
	@NotNull(message = "Please provide customer id")
	private Integer customerId;
	
	@NotNull(message = "Please provide investment")
	private Double investment;

	public BetReq() {

	}

	public BetReq(String dateTime, String betType, Integer propNumber, Integer customerId, Double investment) {
        this.dateTime = dateTime;
        this.betType = betType;
        this.propNumber = propNumber;
        this.customerId = customerId;
        this.investment = investment;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public Integer getPropNumber() {
		return propNumber;
	}

	public void setPropNumber(int propNumber) {
		this.propNumber = propNumber;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Double getInvestment() {
		return investment;
	}

	public void setInvestment(double investment) {
		this.investment = investment;
	}

}
