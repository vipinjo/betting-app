package com.betting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betting.entity.Bet;
import com.betting.entity.BetReport;
import com.betting.service.BetService;

@RestController
@RequestMapping("/api/bets/reports")
public class BetApiReportController {
	
	private BetService betService;
	
	@Autowired
	public BetApiReportController(BetService betService) {
		super();
		this.betService = betService;
	}
	
	@GetMapping
	public List<Bet> getAllBets() {
		return this.betService.getAllBets();
	}
	
	@GetMapping("/details")
	public BetReport getDetailedReport() {
		return this.betService.getDetailedReport();
	}
 
}
