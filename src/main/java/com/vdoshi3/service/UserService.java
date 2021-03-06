package com.vdoshi3.service;

import java.util.List;

import com.vdoshi3.entity.LoginResponse;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.InvalidCredentialsException;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;

public interface UserService {
	public User create(User user) throws ResourceAlreadyExistsException;

	public List<User> findAll();

	public User findById(String uid) throws ResourceNotFoundException;

	public User findByEmail(String email) throws ResourceNotFoundException;

	public User update(String uid, User user) throws ResourceNotFoundException;

	public void delete(String uid) throws ResourceNotFoundException;
	
	public LoginResponse login(User user) throws ResourceNotFoundException, InvalidCredentialsException;
}
