package com.vdoshi3.controller;

import java.util.List;

import com.vdoshi3.entity.Rating;
import com.vdoshi3.exception.InvalidCredentialsException;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.utils.DecodedToken;

public interface RatingController {

	public Rating create(String mid, String uid, Rating rating) throws ResourceNotFoundException;

	public List<Rating> findAll(String filterByMid, String filterByUid);

	public Rating findByRid(String rid) throws ResourceNotFoundException;
	
	public void delete(String rid,DecodedToken requestor) throws ResourceNotFoundException, InvalidCredentialsException;

}
