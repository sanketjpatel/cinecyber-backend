package com.vdoshi3.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;

public interface MovieController {

	public Movie create(Movie movie) throws ResourceAlreadyExistsException;

	public List<Movie> findAll(String filterByTitle);

	public Movie findById(String mid) throws ResourceNotFoundException;

	public Movie update(String mid, Movie movie) throws ResourceNotFoundException;

	public void delete(String mid) throws ResourceNotFoundException;

	public List<Movie> loadMovieData() throws ParseException, JsonParseException, JsonMappingException, IOException;

}
