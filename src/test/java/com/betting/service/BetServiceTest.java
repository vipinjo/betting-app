package com.betting.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.betting.entity.Bet;
import com.betting.entity.BetReport;
import com.betting.repository.BetRepository;

@RunWith(SpringRunner.class)
public class BetServiceTest {

	@TestConfiguration
	static class BetServiceImplTestContextConfiguration {

		@MockBean
		private BetRepository betRepository;

		@Bean
		public BetService betService() {
			Bet bet = new Bet("2018-07-06 12:56", "WIN", 103333, 1082, 500.5);
			Bet[] bets = { bet };
			Mockito.when(betRepository.findAll()).thenReturn(Arrays.asList(bets));

			return new BetService(betRepository);
		}
	}

	@Autowired
	private BetService betService;

	@Test
	public void testGetAll() {
		List<Bet> bets = betService.getAllBets();
		assertNotNull(bets);
	}
	
	@Test
	public void whenOneBetAvailable_thenCheckDetailedReport() {
		BetReport detailedReport = betService.getDetailedReport();
		
		if (!detailedReport.getTotalBetsSoldPerBetType().isEmpty()) {
		    assertThat(detailedReport.getTotalBetsSoldPerBetType().get(0).getBetType().equalsIgnoreCase("WIN"));
		    assertThat(detailedReport.getTotalBetsSoldPerBetType().get(0).getQuantitySold() == 1);
		}
		
		if (!detailedReport.getTotalInvestmentPerCustomerID().isEmpty()) {
		    assertThat(detailedReport.getTotalInvestmentPerCustomerID().get(0).getCustomerId() == 103333);
		    assertThat(detailedReport.getTotalInvestmentPerCustomerID().get(0).getInvestment() == 500.5);
		}
		
		if (!detailedReport.getTotalInvestmentPerBetType().isEmpty()) {
		    assertThat(detailedReport.getTotalInvestmentPerBetType().get(0).getBetType().equalsIgnoreCase("WIN"));
		    assertThat(detailedReport.getTotalInvestmentPerBetType().get(0).getInvestment() == 500.5);
		}
	}

}
