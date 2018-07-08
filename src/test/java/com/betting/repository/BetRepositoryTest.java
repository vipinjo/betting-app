package com.betting.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.betting.entity.Bet;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BetRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private BetRepository betRepository;

	@Test
	public void test() {
		
		Bet bet = new Bet("2018-07-06 12:56", "WIN", 103333, 1082, 500.5);
		entityManager.persist(bet);
	    entityManager.flush();
	    
	    List<Bet> bets = betRepository.findByCustomerId(bet.getCustomerId());
	    
	    assertThat(bet.getCustomerId() == bets.get(0).getCustomerId());
	}

}
