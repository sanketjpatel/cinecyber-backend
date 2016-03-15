package com.vdoshi3.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vdoshi3.entity.LoginResponse;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.InvalidCredentialsException;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.service.UserService;
import com.vdoshi3.utils.DecodedToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping("/users")
@Api(tags = "users", description = "User Related CRUD operations")
public class UserControllerImp implements UserController {
	@Autowired
	private UserService service;

	@Override
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create an user", notes = "Returns the created user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 409, message = "User Exists"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public User create(@RequestBody User user) throws ResourceAlreadyExistsException {
		return service.create(user);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all users", notes = "Returns the list of users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<User> findAll(@ModelAttribute("requestor") DecodedToken requestor) {
		return service.findAll();
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find user by userid", notes = "Returns the user whose userid is provided.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public User findById(@PathVariable("id") String uid) throws ResourceNotFoundException {
		return service.findById(uid);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update an user", notes = "Returns the updated user.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public User update(@ModelAttribute("requestor") DecodedToken requestor, @PathVariable("id") String uid,
			@RequestBody User user) throws ResourceNotFoundException {
		System.out.println("Requestor:" + requestor.toString());
		return service.update(uid, user);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete an user", notes = "Returns the deleted user.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void delete(@PathVariable("id") String uid) throws ResourceNotFoundException {
		service.delete(uid);
	}

	@Override
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Log in an user", notes = "Log user in")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public LoginResponse login(@RequestBody User user, HttpServletResponse hr) throws ResourceNotFoundException, InvalidCredentialsException {
//		HttpHeaders responseHeaders = new HttpHeaders();
		String token = service.login(user);
//		responseHeaders.set("Authorization", "Bearer " + token);
//		hr.addHeader("Authorization", "Bearer " + token);
//		return new ResponseEntity<String>("Welcome", responseHeaders, HttpStatus.CREATED);
		LoginResponse lr= new LoginResponse();
		lr.setToken(token);
		hr.addHeader("Authorization", "Bearer " + token);
		hr.addHeader("Access-Control-Expose-Headers", "Authorization");
		hr.addHeader("Access-Control-Allow-Headers","Authorization");
//		hr.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Header is:"+hr.getHeader("Authorization"));
		return lr;
	}

	@ModelAttribute("requestor")
	public DecodedToken getDecodedToken(HttpServletRequest request) {
		return (DecodedToken) request.getAttribute("requestor");
	}

}
