package com.example.tdd;

import com.example.tdd.exceptions.UsernameOrPasswordException;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TddProjectApplicationTests {
	List<User> userList = new ArrayList<>();

	@Autowired
	Login login;

	@Autowired
	UserFactory userFactory;

	@Autowired
	SecureUtils secureUtils;

	@Autowired
	JwtService jwtService;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	@Test
	void userFactory_test_success() throws NoSuchAlgorithmException {
		User user = userFactory.createUser(secureUtils,"anna", "losen");
		User user1 = userFactory.createUser(secureUtils,"berit", "123456");
		User user2 = userFactory.createUser(secureUtils,"kalle", "password");
		userList = List.of(user,user1,user2);
		assertNotNull(user);
	}

	@Test
	void user_login_test_success() throws UsernameOrPasswordException {
		String token = login.loginValidator(userList,"berit", "123456");
		System.out.println(token);
		assertFalse(token.isEmpty());
	}

	@Test
	void  token_validation_test_success() throws UsernameOrPasswordException {
		String token = login.loginValidator(userList,"berit", "123456");
		assertTrue(jwtService.jwtIsValid(token));
	}



}
