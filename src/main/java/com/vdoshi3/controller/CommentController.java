package com.vdoshi3.controller;

import java.util.List;

import com.vdoshi3.entity.Comment;
import com.vdoshi3.exception.ResourceNotFoundException;

public interface CommentController {

	public Comment create(String mid, String uid, Comment comment) throws ResourceNotFoundException;

	public List<Comment> findAll(String filterByMid, String filterByUid);

	public Comment findByCid(String cid) throws ResourceNotFoundException;

	public void delete(String cid) throws ResourceNotFoundException;

}
