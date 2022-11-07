package com.travelagency.TravelAgency.repository;

import com.travelagency.TravelAgency.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    List<User> findByFirstname(String firstname);

    List<User> findByLastname(String lastname);

    List<User> findByUsername(String username);

    List<User> findByBlocked(boolean blocked);
}
