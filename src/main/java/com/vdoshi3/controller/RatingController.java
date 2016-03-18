package com.vdoshi3.controller;

import java.util.List;

import com.vdoshi3.entity.Rating;
import com.vdoshi3.exception.ResourceNotFoundException;

public interface RatingController {

	public Rating create(String mid, String uid, Rating rating) throws ResourceNotFoundException;

	public List<Rating> findAll(String filterByMid, String filterByUid);

	public Rating findByRid(String rid) throws ResourceNotFoundException;
	
	public void delete(String rid) throws ResourceNotFoundException;

}
