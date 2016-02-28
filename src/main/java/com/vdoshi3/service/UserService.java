package com.vdoshi3.service;

import java.util.List;

import com.vdoshi3.entity.User;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;

public interface UserService {
	public User create(User user) throws ResourceAlreadyExistsException;
	public List<User> findAll();
	public User findById(String userid) throws ResourceNotFoundException;
	public User findByEmail(String email) throws ResourceNotFoundException;
	public User update(String userid, User user) throws ResourceNotFoundException;
	public void delete(String userid) throws ResourceNotFoundException;
}
