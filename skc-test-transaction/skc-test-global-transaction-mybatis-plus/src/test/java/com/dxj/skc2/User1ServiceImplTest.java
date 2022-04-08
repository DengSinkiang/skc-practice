package com.dxj.skc2;

import com.dxj.skc2.domain.User1;
import com.dxj.skc2.service.User1Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = GlobalTransactionApplication.class)
public class User1ServiceImplTest {

	@Autowired
	private User1Service user1Service;

	@BeforeEach
	public void setUpBeforeClass() {
		user1Service.truncated();
	}

	@Test
	public void testAdd() {
		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.add(user1);
	}

}
