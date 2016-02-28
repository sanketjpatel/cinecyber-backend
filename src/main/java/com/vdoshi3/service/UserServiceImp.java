package com.vdoshi3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdoshi3.dao.UserDao;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao repo;

	@Override
	public User create(User user) throws ResourceAlreadyExistsException {
		if (repo.findById(user.getEmail()) != null || repo.findByEmail(user.getEmail()) != null) {
			throw new ResourceAlreadyExistsException();
		} else {
			return repo.create(user);
		}
	}

	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	public User findById(String userid) throws ResourceNotFoundException {
		User u = repo.findById(userid);
		if (u != null) {
			return u;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public User findByEmail(String email) throws ResourceNotFoundException {
		User u = repo.findByEmail(email);
		if (u != null) {
			return u;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public User update(String userid, User user) throws ResourceNotFoundException {
		System.out.println("Called update for: " + userid);
		User u = repo.findById(userid);
		if (u != null) {
			return repo.update(user);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public void delete(String userid) throws ResourceNotFoundException {
		boolean status = repo.delete(userid);
		if (status == false) {
			throw new ResourceNotFoundException();
		}
	}

}
