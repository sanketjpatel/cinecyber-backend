package com.vdoshi3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.entity.Rating;
import com.vdoshi3.entity.View;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.service.MovieService;
import com.vdoshi3.service.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/ratings")
@Api(tags = "ratings", description = "Ratings API")
public class RatingControllerImp implements RatingController {

	@Autowired
	RatingService service;

	@JsonView(View.Public.class)
	@Override
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a rating", notes = "Returns the created rating")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Rating create(@RequestParam("mid") String mid, @RequestParam("uid") String uid, @RequestBody Rating rating)
			throws ResourceNotFoundException {
			return service.create(mid, uid, rating);
	}

	@JsonView(View.Public.class)
	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all ratings", notes = "Returns the list of ratings")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Rating> findAll(@RequestParam(value = "mid", required = false) String filterByMid,
			@RequestParam(value = "uid", required = false) String filterByUid) {
		if (filterByMid == null && filterByUid == null) {
			return service.findAll();
		} else if (filterByMid != null && filterByUid == null) {
			return service.findByMid(filterByMid);
		} else if (filterByMid == null && filterByUid != null) {
			return service.findByUid(filterByUid);
		} else {
			return service.findByMidUid(filterByMid, filterByUid);
		}
	}

	@JsonView(View.Public.class)
	@Override
	@RequestMapping(value = "{rid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find rating by id", notes = "Returns the rating")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Rating findByRid(@PathVariable("rid") String rid) throws ResourceNotFoundException {
		return service.findByRid(rid);
	}

	@Override
	@RequestMapping(value = "{rid}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete rating by id", notes = "Delete the rating")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void delete(@PathVariable("rid") String rid) throws ResourceNotFoundException {
		service.delete(rid);
	}

}
