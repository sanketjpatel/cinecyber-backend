package com.vdoshi3.service;

import java.util.List;

import com.vdoshi3.entity.Movie;

public interface MovieService {
	
	public Movie create(Movie movie);

	public List<Movie> findAll();

	public Movie findById(String mid);

	public List<Movie> findByTitle(String title);

	public Movie update(Movie movie);

	public Movie delete(String mid);

}
