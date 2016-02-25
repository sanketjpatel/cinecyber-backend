package com.vdoshi3.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vdoshi3.entity.User;
import com.vdoshi3.exception.UserAlreadyExistsException;
import com.vdoshi3.exception.UserNotFoundException;
import com.vdoshi3.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(tags="users",description="User Related CRUD operations")
public class UserControllerImp implements UserController{
	@Autowired
	private UserService service;

	@Override
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Create an user", notes="Returns the created user")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=409, message = "User Exists"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public User create(@RequestBody User user) throws UserAlreadyExistsException {
		return service.create(user);
	}

	@Override
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Find all users", notes="Returns the list of users")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public List<User> findAll() {
		return service.findAll();
	}

	@Override
	@RequestMapping(value="{id}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Find user by userid", notes="Returns the user whose userid is provided.")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404, message = "User Not Found"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public User findById(@PathVariable("id") String userid) throws UserNotFoundException{
		return service.findById(userid);
	}

	@Override
	@RequestMapping(value="{id}",method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Update an user", notes="Returns the updated user.")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404, message = "User Not Found"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public User update(@PathVariable("id") String userid,@RequestBody User user) throws UserNotFoundException {
		return service.update(userid, user);
	}

	@Override
	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Delete an user", notes="Returns the deleted user.")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404, message = "User Not Found"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public User delete(String userid) throws UserNotFoundException {
		return service.delete(userid);
	}

}
