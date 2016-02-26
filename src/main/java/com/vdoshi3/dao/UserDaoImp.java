package com.vdoshi3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vdoshi3.entity.User;

@Repository
public class UserDaoImp implements UserDao {

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
	public User findById(String userid) {
		TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
		query.setParameter("vUserId", userid);
		List<User> users = query.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("vEmail", email);
		List<User> users = query.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);
		} else {
			return null;
		}

	}

	@Override
	@Transactional
	public User update(User user) {
		return em.merge(user);
	}

	@Override
	@Transactional
	public boolean delete(String userid) {
		User u = findById(userid);
		if (u != null) {
			em.remove(u);
			return true;
		} else {
			return false;
		}

	}

}
