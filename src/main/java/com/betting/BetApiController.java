package com.betting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betting.entity.Bet;
import com.betting.service.BetService;

@RestController
@RequestMapping("/api/bets")
public class BetApiController {
	
	private BetService betService;
	
	@Autowired
	public BetApiController(BetService betService) {
		super();
		this.betService = betService;
	}
	
	@PostMapping
	public void createBet(@RequestBody Bet bet) {
		this.betService.createBet(bet);
	}
 
}
