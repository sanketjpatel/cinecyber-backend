package com.vdoshi3.controller;

import java.util.List;

import com.vdoshi3.entity.Movie;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;

public interface MovieController {
	
	public Movie create(Movie movie) throws ResourceAlreadyExistsException;

	public List<Movie> findAll(String filterByTitle);

	public Movie findById(String mid) throws ResourceNotFoundException;

	public Movie update(String mid, Movie movie) throws ResourceNotFoundException;

	public void delete(String mid) throws ResourceNotFoundException;

}
