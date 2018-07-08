package com.betting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BET")
public class Bet {

	@Id
	@Column(name="id")
	@GeneratedValue
	private long id;
	
	@Column(name="date_time")
	private String dateTime;
	
	@Column(name="bet_type")
	private String betType;
	
	@Column(name="prop_number")
	private int propNumber;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="investment")
	private double investment;

	public Bet() {

	}

	public Bet(String dateTime, String betType, int propNumber, int customerId, double investment) {
        this.dateTime = dateTime;
        this.betType = betType;
        this.propNumber = propNumber;
        this.customerId = customerId;
        this.investment = investment;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getPropNumber() {
		return propNumber;
	}

	public void setPropNumber(int propNumber) {
		this.propNumber = propNumber;
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
