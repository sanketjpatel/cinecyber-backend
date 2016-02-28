package com.vdoshi3.dao;

import java.util.List;

import com.vdoshi3.entity.Movie;

public interface MovieDao {

	public Movie create(Movie movie);

	public List<Movie> findAll();

	public Movie findById(String mid);

	public List<Movie> findByTitle(String title);

	public Movie update(Movie movie);

	public void delete(Movie movie);
}
