package com.vdoshi3.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdoshi3.dao.UserDao;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao repo;

	@Override
	public User create(User user) throws ResourceAlreadyExistsException {
		if (repo.usernameExists(user.getUsername()) == true || repo.findByEmail(user.getEmail()) != null
				|| repo.findById(user.getUid()) != null) {
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
	public User findById(String uid) throws ResourceNotFoundException {
		User u = repo.findById(uid);
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
	public User update(String uid, User user) throws ResourceNotFoundException {
		User u = repo.findById(uid);
		if (u != null) {
			return repo.update(user);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public void delete(String uid) throws ResourceNotFoundException {
		User u = repo.findById(uid);
		if (u != null) {
			repo.delete(u);
		} else {
			throw new ResourceNotFoundException();
		}
	}

}
