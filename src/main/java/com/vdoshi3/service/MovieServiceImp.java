package com.vdoshi3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdoshi3.dao.MovieDao;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.exception.ResourceAlreadyExistsException;

@Service
public class MovieServiceImp implements MovieService {
	@Autowired
	MovieDao repo;
	
	@Override
	public Movie create(Movie movie) {
		if (repo.findById(movie.getMid()) != null){
			return repo.create(movie);
		}else{
//			throw new ResourceAlreadyExistsException();
		}
		return null;
	}

	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie findById(String mid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie update(String mid, Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String mid) {
		// TODO Auto-generated method stub
		
	}

}
