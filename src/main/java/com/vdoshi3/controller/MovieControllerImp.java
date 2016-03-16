package com.vdoshi3.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.service.MovieService;
import com.vdoshi3.utils.DataLoader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "movies", description = "service to perform CRUD on Movies")
public class MovieControllerImp implements MovieController {
	@Autowired
	MovieService service;
	
	@Autowired
	DataLoader dataLoader;

	@Override
	@RequestMapping(value = "api/movies",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create movie", notes = "Returns the created movie")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 409, message = "Movie already exists"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie create(@RequestBody Movie movie) throws ResourceAlreadyExistsException {
		return service.create(movie);
	}

	@Override
	@RequestMapping(value = "/movies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all movies", notes = "Returns the list of movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> findAll(@RequestParam(value = "title", required = false) String filterByTitle, HttpServletRequest hr, HttpServletResponse res) {
		String token = hr.getHeader("Authorization");
		System.out.println("Authorization:"+token);
//		res.addHeader("", arg1);
		if (filterByTitle == null) {
			return service.findAll();
		} else {
			return service.findByTitle(filterByTitle);
		}
	}

	@Override
	@RequestMapping(value = "api/movies/{mid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find movie by movie id", notes = "returns the movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie findById(@PathVariable("mid") String mid) throws ResourceNotFoundException {
		return service.findById(mid);
	}

	@Override
	@RequestMapping(value = "api/movies/{mid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a movie", notes = "Returns the modified movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie update(@PathVariable("mid") String mid, @RequestBody Movie movie) throws ResourceNotFoundException {
		return service.update(mid, movie);
	}

	@Override
	@RequestMapping(value = "api/movies/{mid}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a movie", notes = "Deletes the specified movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void delete(@PathVariable("mid") String mid) throws ResourceNotFoundException {
		service.delete(mid);
	}

	@Override
	@RequestMapping(value = "movies/loadData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Load dummy data", notes = "Return the list of dummy data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> loadMovieData() throws ParseException, JsonParseException, JsonMappingException, IOException {
		return dataLoader.loadMovieData();
	}
}
