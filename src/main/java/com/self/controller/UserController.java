package com.self.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.self.domain.User;
/**
 * SpringMVC配置多视图-内容协商原理
 * **/
@Controller
public class UserController {
	@RequestMapping(value="/getUser")
	public User getUser() {
		User user = new User();
		user.setUsername("wy");
		user.setPassword("123");
		user.setAge(123);
		user.setSex("male");
		user.setBirthday("2013-09-10");
		return user;
	}
}
