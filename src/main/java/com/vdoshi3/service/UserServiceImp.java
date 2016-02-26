package com.vdoshi3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdoshi3.dao.UserDao;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.UserAlreadyExistsException;
import com.vdoshi3.exception.UserNotFoundException;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao repo;

	@Override
	public User create(User user) throws UserAlreadyExistsException {
		if (repo.findById(user.getEmail()) != null || repo.findByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException();
		} else {
			return repo.create(user);
		}
	}

	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	public User findById(String userid) throws UserNotFoundException {
		User u = repo.findById(userid);
		if (u != null) {
			return u;
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public User findByEmail(String email) throws UserNotFoundException {
		User u = repo.findByEmail(email);
		if (u != null) {
			return u;
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public User update(String userid, User user) throws UserNotFoundException {
		System.out.println("Called update for: " + userid);
		User u = repo.findById(userid);
		if (u != null) {
			return repo.update(user);
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public void delete(String userid) throws UserNotFoundException {
		boolean status = repo.delete(userid);
		if (status == false) {
			throw new UserNotFoundException();
		}
	}

}
