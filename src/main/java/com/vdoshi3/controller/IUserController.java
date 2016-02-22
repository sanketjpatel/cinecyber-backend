package com.vdoshi3.controller;

import java.util.List;

import com.vdoshi3.entity.User;

public interface IUserController {
	public User create(User user);
	public List<User> findAll();
	public User find(String userid);
	public User update(User user);
	public User delete(String userid);
}
