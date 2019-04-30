package com.xt.mybatis.mapper;

import com.xt.mybatis.bean.Address;
import com.xt.mybatis.bean.SeasonEnum;
import com.xt.mybatis.bean.User;
import com.xt.mybatis.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TypeHandlerTest {
	
	private UserService userService;
	
	{
		userService = new ClassPathXmlApplicationContext("spring-context.xml").getBean(UserService.class);
	}
	
	@Test
	public void testQueryUser() {
		
		Integer userId = 6;
		
		User user = userService.getUserById(userId);
		
		System.out.println(user);
	}
	
	@Test
	public void testSaveUser() {
		
		User user = new User(null, "tom11", new Address("AAA", "BBB", "CCC"), SeasonEnum.AUTUMN);
		
		userService.saveUser(user);
		
	}

}
