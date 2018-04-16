package com.czhang.cpms.service;


import java.util.List;

import com.czhang.cpms.model.db.UserDAO;
import com.czhang.cpms.model.domain.User;

public interface UserService {
	
	UserDAO findById(Long id);

	UserDAO findByUserName(String name);

	UserDAO saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<UserDAO> findAllUsers();

	boolean isUserExist(User user);
}