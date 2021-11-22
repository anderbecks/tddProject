package com.example.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TddProjectApplicationTests {
	UserFactory userFactory = new UserFactory();
	User user = userFactory.createUser("Namn", "lösen");

	@Test
	void contextLoads() {
	}

	@BeforeEach
	@Test
	void userFactory_test_success(){

		assertNotNull(user);
	}

	@Test
	void user_login_test_success(){
		Login login = new Login();
		Boolean success = login.loginValidator(user, "Namn", "lösen");

		assertEquals(true, success);
	}



}
