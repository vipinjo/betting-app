package com.betting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.betting.service.BetService;

@Controller
@RequestMapping("/bets")
public class BetController {
	
	private BetService betService;
	
	@Autowired
	public BetController(BetService betService) {
		super();
		this.betService = betService;
	}
	
	@GetMapping
	public String getAllBets(Model model) {
        model.addAttribute("bets", this.betService.getAllBets());
        return "bets";
	}

}
