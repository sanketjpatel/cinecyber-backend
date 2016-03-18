package com.vdoshi3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.vdoshi3.entity.Movie;
import com.vdoshi3.entity.Rating;

@Repository
public class RatingDaoImp implements RatingDao {
	@PersistenceContext
	EntityManager em;

	@Override
	public Rating create(Rating rating) {
		em.persist(rating);
		return rating;
	}

	@Override
	public List<Rating> findAll() {
		TypedQuery<Rating> query = em.createQuery("SELECT r FROM Rating r", Rating.class);
		return query.getResultList();
	}

	@Override
	public Rating findByRid(String rid) {
		TypedQuery<Rating> query = em.createNamedQuery("Rating.findByRid", Rating.class);
		query.setParameter("vRid", rid);
		List<Rating> ratings = query.getResultList();
		if (ratings != null && ratings.size() == 1) {
			return ratings.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Rating> findByMid(String mid) {
		TypedQuery<Rating> query = em.createNamedQuery("Rating.findByMid", Rating.class);
		query.setParameter("vMid", mid);
		return query.getResultList();
	}

	@Override
	public List<Rating> findByUid(String uid) {
		TypedQuery<Rating> query = em.createNamedQuery("Rating.findByUid", Rating.class);
		query.setParameter("vUid", uid);
		return query.getResultList();
	}

	@Override
	public List<Rating> findByMidUid(String mid, String uid) {
		TypedQuery<Rating> query = em.createNamedQuery("Rating.findByMidUid", Rating.class);
		query.setParameter("vMid", mid);
		query.setParameter("vUid", uid);
		return query.getResultList();
	}

	@Override
	public Rating update(Rating rating) {
		return em.merge(rating);
	}
	
	@Override
	public void delete(Rating rating) {
		em.remove(rating);
	}
}
