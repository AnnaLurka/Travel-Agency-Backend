package com.travelagency.TravelAgency.repository;

import com.travelagency.TravelAgency.domain.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TokenRepository extends CrudRepository<Token, Long> {
}