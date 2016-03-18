package com.vdoshi3.service;

import java.util.List;

import com.vdoshi3.entity.Rating;
import com.vdoshi3.exception.ResourceNotFoundException;

public interface RatingService {

	public Rating create(String mid, String uid, Rating rating) throws ResourceNotFoundException;

	public List<Rating> findAll();

	public Rating findByRid(String rid) throws ResourceNotFoundException;

	public List<Rating> findByMid(String mid);

	public List<Rating> findByUid(String uid);

	public List<Rating> findByMidUid(String mid, String uid);
	
	public Rating update(Rating rating) throws ResourceNotFoundException;

	public void delete(String rid) throws ResourceNotFoundException;

}
