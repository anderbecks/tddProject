package com.example.tdd;

import com.example.tdd.exceptions.UsernameOrPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TddProjectApplicationTests {
	List<User> userList = new ArrayList<>();

	@Test
	void contextLoads() {
	}

	@BeforeEach
	@Test
	void userFactory_test_success() throws NoSuchAlgorithmException {
		UserFactory userFactory = new UserFactory();
		SecureUtils secureUtils = new SecureUtils();
		User user = userFactory.createUser(secureUtils,"anna", "losen");
		User user1 = userFactory.createUser(secureUtils,"berit", "123456");
		User user2 = userFactory.createUser(secureUtils,"kalle", "password");
		userList = List.of(user,user1,user2);
		assertNotNull(user);
	}

	@Test
	void user_login_test_success() throws UsernameOrPasswordException {
		Login login = new Login();
		boolean success = login.loginValidator(userList,"berit", "123456");
		assertEquals(true, success);
	}




}
