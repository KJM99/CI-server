package com.example.devopsboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevopsBoardApplicationTests {
	@Value("${spring.datasource.username}") String name;

	@Test
	void main(){
		DevopsBoardApplication.main(new String[] {});
	}

	@Test
	void contextLoads() {
	}

	@Test
	void dbUserNameIsSa(){
		assertEquals(name, "sa");
	}
}
