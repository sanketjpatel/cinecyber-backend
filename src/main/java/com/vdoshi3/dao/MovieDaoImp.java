package com.vdoshi3.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.vdoshi3.entity.Movie;

@Repository
public class MovieDaoImp implements MovieDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public Movie create(Movie movie) {
		em.persist(movie);
		return movie;
	}

	@Override
	public List<Movie> findAll() {
		TypedQuery<Movie> query = em.createQuery("SELECT m from Movie m", Movie.class);
		List<Movie> movies = query.getResultList();
		return movies;
	}

	@Override
	public Movie findById(String mid) {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findById", Movie.class);
		query.setParameter("vMid", mid);
		List<Movie> movies = query.getResultList();
		if (movies != null && movies.size() == 1) {
			return movies.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Movie> findByTitle(String title) {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTitle", Movie.class);
		query.setParameter("vTitle", title);
		List<Movie> movies = query.getResultList();
		if (movies != null) {
			return movies;
		} else {
			return new ArrayList<Movie>();
		}
	}

	@Override
	public Movie update(Movie movie) {
		return em.merge(movie);
	}

	@Override
	public void delete(Movie movie) {
		em.remove(movie);
	}

}
