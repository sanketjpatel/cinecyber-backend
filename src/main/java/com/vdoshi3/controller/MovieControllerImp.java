package com.vdoshi3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vdoshi3.entity.Movie;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.service.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movies")
@Api(tags = "movies", description = "service to perform CRUD on Movies")
public class MovieControllerImp implements MovieController {
	@Autowired
	MovieService service;

	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create movie", notes = "Returns the created movie")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 409, message = "Movie already exists"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie create(@RequestBody Movie movie) throws ResourceAlreadyExistsException {
		return service.create(movie);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all movies", notes = "Returns the list of movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> findAll() {
		return service.findAll();
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find movie by movie id", notes = "returns the movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie findById(@RequestParam("id") String mid) throws ResourceNotFoundException {
		return service.findById(mid);
	}

	@Override
	@RequestMapping(value = "/titles/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get movie by title", notes = "Returns list of movies with specified word in the title")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Movie> findByTitle(@RequestParam("title") String title) {
		return service.findByTitle(title);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a movie", notes = "Returns the modified movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Movie update(@RequestParam("id") String mid, @RequestBody Movie movie) throws ResourceNotFoundException {
		return service.update(mid, movie);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a movie", notes = "Deletes the specified movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void delete(@RequestParam("id") String mid) throws ResourceNotFoundException {
		service.delete(mid);
	}

}
