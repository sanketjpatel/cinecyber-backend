package com.vdoshi3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vdoshi3.entity.User;
import com.vdoshi3.service.IUserService;
import com.vdoshi3.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController implements IUserService{
	@Autowired
	private UserService service;

	@Override
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public User create(User user) {
		return service.create(user);
	}

	@Override
	public List<User> findAll() {
		return service.findAll();
	}

	@Override
	public List<User> find(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
