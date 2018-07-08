package com.betting.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.betting.entity.Bet;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {
	
	List<Bet> findByCustomerId(int customerId);
	
	List<Bet> findByBetType(String betType);
	
	List<Bet> findByDateTime(String dateTime);

}
