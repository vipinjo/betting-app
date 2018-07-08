package com.betting.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betting.BetReq;
import com.betting.entity.Bet;
import com.betting.entity.BetReport;
import com.betting.entity.BetSoldPerHour;
import com.betting.entity.BetTypeInvestment;
import com.betting.entity.BetTypeSold;
import com.betting.entity.CustomerInvestment;
import com.betting.repository.BetRepository;

@Service
public class BetService {

	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH";
	public static final String DATE_FORMAT_NOW_WITH_HOUR_MIN = "yyyy-MM-dd HH:mm:ss";

	private BetRepository betRepository;

	@Autowired
	public BetService(BetRepository betRepository) {
		super();
		this.betRepository = betRepository;
	}

	public List<Bet> getAllBets() {
		List<Bet> bets = new ArrayList<Bet>();
		this.betRepository.findAll().forEach(bets::add);

		return bets;
	}

	/**
	 * Save new bet
	 * @param bet
	 */
	public void createBet(BetReq bet) {
		this.betRepository.save(getBetEntity(bet));
	}

	/**
	 * Generate bet reports
	 * @return
	 */
	public BetReport getDetailedReport() {
		BetReport report = new BetReport();
		report.setTotalInvestmentPerCustomerID(getCustomersInvestment());
		report.setTotalInvestmentPerBetType(getInverstmentPerBetType());
		report.setTotalBetsSoldPerBetType(getTotalBetsSoldPerBetType());
		report.setTotalBetsSoldPerHour(getTotalNumberOfBetSoldPerHour());

		return report;
	}

	/**
	 * get list of investment per bet type .
	 * @return
	 */
	private List<BetTypeInvestment> getInverstmentPerBetType() {
		List<BetTypeInvestment> betTypeInvestments = new ArrayList<>();

		Map<String, Double> investmentPerBetType = calculateInvestmentPerBetType();
		for (String betType : investmentPerBetType.keySet()) {
			betTypeInvestments.add(new BetTypeInvestment(betType, investmentPerBetType.get(betType)));
		}
		return betTypeInvestments;
	}

	/**
	 * get a list of bets sold per bet type.
	 * @return
	 */
	private List<BetTypeSold> getTotalBetsSoldPerBetType() {
		List<BetTypeSold> betsSoldPerBetTypeList = new ArrayList<>();
		Map<String, Integer> betsSoldPerBetType = calculateBetsSoldPerBetType();
		for (String betType : betsSoldPerBetType.keySet()) {
			betsSoldPerBetTypeList.add(new BetTypeSold(betType, betsSoldPerBetType.get(betType)));
		}
		return betsSoldPerBetTypeList;
	}

	/**
	 * get list of bets sold per hour.
	 * @return
	 */
	private List<BetSoldPerHour> getTotalNumberOfBetSoldPerHour() {
		List<BetSoldPerHour> betSoldPerHourList = new ArrayList<BetSoldPerHour>();
		Map<Date, Integer> betsSoldPerHourMap = calculateBetsSoldPerHour();
		for (Date betSoldDate : betsSoldPerHourMap.keySet()) {
			betSoldPerHourList.add(new BetSoldPerHour(getFormattedStartTime(betSoldDate),
					getFormattedEndTime(betSoldDate), betsSoldPerHourMap.get(betSoldDate)));
		}

		return betSoldPerHourList;
	}

	private String getFormattedStartTime(Date betSoldDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW_WITH_HOUR_MIN);
		return sdf.format(betSoldDate);
	}

	private String getFormattedEndTime(Date betSoldDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW_WITH_HOUR_MIN);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(betSoldDate);
		calendar.add(Calendar.MINUTE, 59);
		calendar.add(Calendar.SECOND, 59);
		Date endTime = calendar.getTime();
		return sdf.format(endTime);
	}

	/**
	 * get list of customer investment.
	 * @return
	 */
	private List<CustomerInvestment> getCustomersInvestment() {
		List<CustomerInvestment> customerInvestmentList = new ArrayList<>();
		Map<Integer, Double> investmentPerCustomer = calculateInvestmentPerCustomer();
		for (Integer customerId : investmentPerCustomer.keySet()) {
			customerInvestmentList.add(new CustomerInvestment(customerId, investmentPerCustomer.get(customerId)));
		}
		return customerInvestmentList;
	}
	
	private Map<Integer, Double> calculateInvestmentPerCustomer() {
		List<Bet> allBets = getAllBets();
		Map<Integer, Double> customerMap = new HashMap<>();
		for (Bet bet : allBets) {
			if (customerMap.containsKey(bet.getCustomerId())) {
				double totalInvestment = bet.getInvestment() + customerMap.get(bet.getCustomerId());
				customerMap.put(bet.getCustomerId(), totalInvestment);
			} else {
				customerMap.put(bet.getCustomerId(), bet.getInvestment());
			}
		}
		return customerMap;
	}

	private Map<String, Double> calculateInvestmentPerBetType() {
		List<Bet> allBets = getAllBets();
		Map<String, Double> betTypeMap = new HashMap<>();
		for (Bet bet : allBets) {
			if (betTypeMap.containsKey(bet.getBetType())) {
				double totalInvestment = bet.getInvestment() + betTypeMap.get(bet.getBetType());
				betTypeMap.put(bet.getBetType(), totalInvestment);
			} else {
				betTypeMap.put(bet.getBetType(), bet.getInvestment());
			}
		}

		return betTypeMap;
	}

	private Map<String, Integer> calculateBetsSoldPerBetType() {
		List<Bet> allBets = getAllBets();
		Map<String, Integer> betTypeMap = new HashMap<>();
		for (Bet bet : allBets) {
			if (betTypeMap.containsKey(bet.getBetType())) {
				int totalbetsSold = betTypeMap.get(bet.getBetType()) + 1;
				betTypeMap.put(bet.getBetType(), totalbetsSold);
			} else {
				betTypeMap.put(bet.getBetType(), 1);
			}
		}

		return betTypeMap;
	}

	/**
	 * This method calculated the bets sold per hour Return a Hash Map with date
	 * value with hour as key and bet count as value.
	 * 
	 * @return
	 */
	private Map<Date, Integer> calculateBetsSoldPerHour() {
		Map<Date, Integer> betsSoldPerHourMap = new HashMap<>();
		List<Bet> allBets = getAllBets();
		for (Bet bet : allBets) {
			if (bet.getDateTime() != null) {
				Date betDate = getBetDateWithSoldHourOnly(bet.getDateTime());
				if (betDate != null) {
					if (betsSoldPerHourMap.containsKey(betDate)) {
						int betCount = betsSoldPerHourMap.get(betDate) + 1;
						betsSoldPerHourMap.put(betDate, betCount);
					} else {
						betsSoldPerHourMap.put(betDate, 1);
					}
				}
			}
		}
		return betsSoldPerHourMap;
	}

	private Date getBetDateWithSoldHourOnly(String betDateStr) {
		Date betDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		if (betDateStr != null) {
			try {
				betDate = sdf.parse(betDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return betDate;
	}
	
	private Bet getBetEntity(BetReq request) {
		return new Bet(request.getDateTime(), request.getBetType(), request.getPropNumber(), request.getCustomerId(),
				request.getInvestment());
	}
}
