package com.example.tdd;

import com.example.tdd.entities.User;
import com.example.tdd.enums.Permissions;
import com.example.tdd.enums.Resource;
import com.example.tdd.exceptions.UsernameOrPasswordException;
import com.example.tdd.utils.SecureUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TddProjectApplicationTests {
	List<User> userList = new ArrayList<>();

	@Autowired
	Login login;

	@Autowired
	UserService userService;

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
		userService.createUser(secureUtils,"anna", "losen", Resource.ACCOUNT, List.of(Permissions.READ));
		userService.createUser(secureUtils,"berit", "123456", Resource.ACCOUNT, List.of(Permissions.READ, Permissions.WRITE));
		userService.createUser(secureUtils,"kalle", "password", Resource.PROVISION_CALC, List.of(Permissions.EXECUTE));

		userList = userService.getAllUsers();
		assertNotNull(userList);
	}

	@Test
	void user_login_test_success() throws UsernameOrPasswordException {
		String token = login.loginValidator(userList,"berit", "123456");
		System.out.println(token);
		assertFalse(token.isEmpty());
	}

	@Test
	void token_validation_test_success() throws UsernameOrPasswordException {
		String token = login.loginValidator(userList,"berit", "123456");
		assertTrue(jwtService.jwtIsValid(token));
	}


	@Test
	void user_authorities_test_success() throws UsernameOrPasswordException {
		String token = login.loginValidator(userList,"berit", "123456");
		assertNotNull(userService.getUserPermissions(token, Resource.ACCOUNT));
		assertEquals(List.of(Permissions.READ, Permissions.WRITE), userService.getUserPermissions(token, Resource.ACCOUNT));
	}





}
