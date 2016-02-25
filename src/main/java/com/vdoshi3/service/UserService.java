package com.vdoshi3.service;

import java.util.List;

import com.vdoshi3.entity.User;
import com.vdoshi3.exception.UserAlreadyExistsException;
import com.vdoshi3.exception.UserNotFoundException;

public interface UserService {
	public User create(User user) throws UserAlreadyExistsException;
	public List<User> findAll();
	public User findById(String userid) throws UserNotFoundException;
	public User update(String userid, User user) throws UserNotFoundException;
	public User delete(String userid) throws UserNotFoundException;
}
