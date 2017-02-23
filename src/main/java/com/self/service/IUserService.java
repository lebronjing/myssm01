package com.self.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.self.domain.User;

public interface IUserService {
	public User getUserById(String userId);
	public User login(String username, String password);
	PageInfo<User> queryByPage(String userName,Integer pageNo,Integer pageSize);
	List<User> findAll();
	public void save(User user);
}
