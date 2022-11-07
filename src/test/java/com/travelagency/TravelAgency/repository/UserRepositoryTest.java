package com.travelagency.TravelAgency.repository;

import com.travelagency.TravelAgency.domain.Token;
import com.travelagency.TravelAgency.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    private User user1;
    private User user2;

    @Before
    public void init() {
        //Given
        Set<Token> user1Tokens = new HashSet<>();
        user1Tokens.add(new Token(
                "860c5c50-274f-4cb9-9735-c7cc58b9d701",
                LocalDateTime.of(2022, Month.JANUARY, 1, 12, 00, 00),
                LocalDateTime.of(2022, Month.JANUARY, 1, 12, 59,59)));
        user1Tokens.add(new Token(
                "d7660de1-da71-48a4-81db-494a002b5b5e",
                LocalDateTime.of(2022, Month.FEBRUARY, 2, 13, 00, 00),
                LocalDateTime.of(2022, Month.FEBRUARY, 2, 13, 59,59)));

        Set<Token> user2Tokens = new HashSet<>();
        user2Tokens.add(new Token(
                "6579cf0a-8a51-4b4e-bda6-f4b9764c2d01",
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 00, 00),
                LocalDateTime.of(2022, Month.MARCH, 3, 14, 59,59)));
        user2Tokens.add(new Token(
                "d33c697d-677d-4178-8e86-60880edabd05",
                LocalDateTime.of(2022, Month.APRIL, 4, 15, 00, 00),
                LocalDateTime.of(2022, Month.APRIL, 4, 15, 59,59)));

        user1 = new User("Anna", "Kowalska", "anna_kowalska", false, user1Tokens);
        user2 = new User("Barbara", "Kowalczyk", "barbara_kowalczyk", true, user2Tokens);

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @After
    public void cleanUp() {
        userRepository.deleteById(user1.getId());
        userRepository.deleteById(user2.getId());
        tokenRepository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        //Then
        Optional<User> readUser = userRepository.findById(user1.getId());
        assertTrue(readUser.isPresent());
    }

    @Test
    public void testFindAllUsers() {
        //When
        List<User> userList = userRepository.findAll();
        //Then
        assertEquals(2, userList.size());
    }

    @Test
    public void testFindUserByFirstname() {
        //When
        String firstname = user1.getFirstname();
        List<User> readUsers = userRepository.findByFirstname(firstname);
        //Then
        assertEquals(1, readUsers.size());
    }

    @Test
    public void testFindUserByLastname() {
        //When
        String lastname = user1.getLastname();
        List<User> readUsers = userRepository.findByLastname(lastname);
        //Then
        assertEquals(1, readUsers.size());
    }

    @Test
    public void testFindUserByUsername() {
        //When
        String username = user1.getUsername();
        List<User> readUsers = userRepository.findByUsername(username);
        //Then
        assertEquals(1, readUsers.size());
    }

    @Test
    public void testFindBlockedUsers() {
        //When
        List<User> userList = userRepository.findByBlocked(true);
        //Then
        assertEquals(1, userList.size());
    }
}