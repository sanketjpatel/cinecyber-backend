package com.vdoshi3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vdoshi3.entity.Comment;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movies/comment")
@Api(tags = "comments", description = "Comments API")
public class CommentControllerImp implements CommentController {

	@Autowired
	CommentService service;

	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a comment", notes = "Returns the created comment")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Comment create(@RequestBody Comment comment) {
		return service.create(comment);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all comments", notes = "Returns the list of comments")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Comment> findAll() {
		return service.findAll();
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find comment by id", notes = "Returns the comment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Comment findByCid(@RequestParam("id") String cid) throws ResourceNotFoundException {
		return service.findByCid(cid);
	}

	@Override
	@RequestMapping(value = "/mid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find comments by mid", notes = "Returns the list of comments")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Comment> findByMid(@RequestParam("id") String mid) {
		return service.findByMid(mid);
	}

	@Override
	@RequestMapping(value = "/uid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find comments by uid", notes = "Returns the list of comments")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Comment> findByUid(@RequestParam("id") String uid) {
		return service.findByUid(uid);
	}

	@Override
	@RequestMapping(value = "/mid/{mid}/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find comments by mid-uid", notes = "Returns the list of comments")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Comment> findByMidUid(@RequestParam("mid") String mid, @RequestParam("uid") String uid) {
		return service.findByMidUid(mid, uid);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void delete(@RequestParam("id") String cid) throws ResourceNotFoundException {
		service.delete(cid);
	}

}
