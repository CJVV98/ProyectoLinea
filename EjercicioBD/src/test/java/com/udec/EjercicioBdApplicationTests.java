package com.udec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class EjercicioBdApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Test
	void contextLoads() {
		System.out.println("Resultado de encriptacion.....");
		System.out.println(bcrypt.encode("David981216"));
	}


}
