package com.dxj.skc2;

import com.dxj.skc2.TransactionApplication;
import com.dxj.skc2.domain.User2;
import com.dxj.skc2.service.User2Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TransactionApplication.class)
public class User2ServiceImplTest {

	@Autowired
	private User2Service user2Service;

	@BeforeEach
	public void setUpBeforeClass() {
		user2Service.truncated();
	}

	@Test
	public void testAdd() {
		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.add(user2);
	}

}
