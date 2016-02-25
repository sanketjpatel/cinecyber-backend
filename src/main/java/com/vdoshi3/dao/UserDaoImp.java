package com.vdoshi3.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vdoshi3.entity.User;

@Repository
public class UserDaoImp implements UserDao{
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	@Transactional
	public User create(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u from User u", User.class);
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public User findUserById(String userid) {
		System.out.println("Call");
		TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
		query.setParameter("vUserId", userid);
		List<User> users =  query.getResultList();
		System.out.println("Called");
		if(users != null && users.size() == 1){
			return users.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}



}
