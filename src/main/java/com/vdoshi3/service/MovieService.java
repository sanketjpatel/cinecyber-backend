package com.vdoshi3.service;

import java.util.List;

import com.vdoshi3.entity.Movie;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;

public interface MovieService {
	
	public Movie create(Movie movie) throws ResourceAlreadyExistsException;

	public List<Movie> findAll();

	public Movie findById(String mid) throws ResourceNotFoundException;

	public List<Movie> findByTitle(String title);

	public Movie update(String mid, Movie movie) throws ResourceNotFoundException;

	public void delete(String mid) throws ResourceNotFoundException;

}
