package com.vdoshi3.controller;

import java.util.List;

import com.vdoshi3.entity.User;
import com.vdoshi3.exception.InvalidCredentialsException;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.utils.DecodedToken;

public interface UserController {
	public User create(User user) throws ResourceAlreadyExistsException;

	public List<User> findAll(DecodedToken requestor);

	public User findById(String uid) throws ResourceNotFoundException;

	public User update(DecodedToken requestor, String uid, User user) throws ResourceNotFoundException;

	public void delete(String uid) throws ResourceNotFoundException;

	public String login(User user) throws ResourceNotFoundException, InvalidCredentialsException;
}
