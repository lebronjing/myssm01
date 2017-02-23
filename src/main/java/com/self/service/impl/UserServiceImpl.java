package com.self.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.self.IDao.IUserDao;
import com.self.domain.User;
import com.self.service.IUserService;
@Service
public class UserServiceImpl implements IUserService{
	@Resource
	private IUserDao userDao;
	
	@Override
	public User getUserById(String userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public User login(String username, String password) {
		return userDao.findUser(username, password);
	}

	@Override
	public PageInfo<User> queryByPage(String userName, Integer pageNo,
			Integer pageSize) {
		if(pageNo == null || pageNo == 0) {
			pageNo = 1;
		}
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<User> list = userDao.findUserByName(userName);
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	@Override
	public List<User> findAll() {
		return userDao.findUserByName("");
	}

	@Override
	public void save(User user) {
		try{
			userDao.insert(user);
			//throw new RuntimeException("simulate Error condition") ;
		} catch (Exception e) {
			System.out.println("Error in creating record, rolling back");
		    throw e;
		}
	}

	
}
