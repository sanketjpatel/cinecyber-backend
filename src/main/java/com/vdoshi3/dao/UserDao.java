package com.vdoshi3.dao;

import java.util.List;

import com.vdoshi3.entity.User;

public interface UserDao {

	public User create(User user);

	public List<User> findAll();

	public User findById(String userid);

	public User findByEmail(String email);

	public User update(User user);

	public boolean delete(String userid);
}
