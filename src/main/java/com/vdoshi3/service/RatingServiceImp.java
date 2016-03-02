package com.vdoshi3.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdoshi3.dao.MovieDao;
import com.vdoshi3.dao.RatingDao;
import com.vdoshi3.dao.UserDao;
import com.vdoshi3.entity.Rating;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.ResourceNotFoundException;

@Service
@Transactional
public class RatingServiceImp implements RatingService {

	@Autowired
	RatingDao repo;
	@Autowired
	MovieDao movieRepo;
	@Autowired
	UserDao userRepo;

	@Override
	public Rating create(String mid, String uid, Rating rating) throws ResourceNotFoundException {
		Movie m = movieRepo.findById(mid);
		User u = userRepo.findById(uid);
		if (m != null && u != null) {
			rating.setMovie(m);
			rating.setUser(u);
			return repo.create(rating);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<Rating> findAll() {
		return repo.findAll();
	}

	@Override
	public Rating findByRid(String rid) throws ResourceNotFoundException {
		Rating r = repo.findByRid(rid);
		if (r != null) {
			return r;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<Rating> findByMid(String mid) {
		return repo.findByMid(mid);
	}

	@Override
	public List<Rating> findByUid(String uid) {
		return repo.findByUid(uid);
	}

	@Override
	public List<Rating> findByMidUid(String mid, String uid) {
		return repo.findByMidUid(mid, uid);
	}

	@Override
	public void delete(String rid) throws ResourceNotFoundException {
		Rating r = repo.findByRid(rid);
		if (r != null) {
			repo.delete(r);
		} else {
			throw new ResourceNotFoundException();
		}
	}

}