package com.vdoshi3.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.exception.InvalidCredentialsException;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.utils.DecodedToken;

public interface MovieController {

	public Movie create(Movie movie,DecodedToken requestor) throws ResourceAlreadyExistsException,InvalidCredentialsException;

	public List<Movie> findAll(String filterByTitle, HttpServletRequest hr, HttpServletResponse res);

	public Movie findById(String mid) throws ResourceNotFoundException;

	public Movie update(String mid, Movie movie, DecodedToken requestor) throws ResourceNotFoundException,InvalidCredentialsException;

	public void delete(String mid,DecodedToken requestor) throws ResourceNotFoundException,InvalidCredentialsException;

	public List<Movie> loadMovieData() throws ParseException, JsonParseException, JsonMappingException, IOException;

}
