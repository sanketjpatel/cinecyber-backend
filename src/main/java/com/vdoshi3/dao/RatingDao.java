package com.vdoshi3.dao;

import java.util.List;

import com.vdoshi3.entity.Rating;

public interface RatingDao {

	public Rating create(Rating rating);

	public List<Rating> findAll();

	public Rating findByRid(String rid);

	public List<Rating> findByMid(String mid);

	public List<Rating> findByUid(String uid);

	public List<Rating> findByMidUid(String mid, String uid);

	public void delete(Rating rating);

}
