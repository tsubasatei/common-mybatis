package com.xt.mybatis.service;

import com.xt.mybatis.bean.User;
import com.xt.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	public void saveUser(User user) {
		userMapper.insert(user);
	}

	public User getUserById(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

}
