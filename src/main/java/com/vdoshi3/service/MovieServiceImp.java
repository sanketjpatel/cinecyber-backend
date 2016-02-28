package com.vdoshi3.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdoshi3.dao.MovieDao;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;

@Service
@Transactional
public class MovieServiceImp implements MovieService {
	@Autowired
	MovieDao repo;
	
	@Override
	public Movie create(Movie movie) throws ResourceAlreadyExistsException {
		if (repo.findById(movie.getMid()) == null){
			return repo.create(movie);
		}else{
			throw new ResourceAlreadyExistsException();
		}
	}

	@Override
	public List<Movie> findAll() {
		return repo.findAll();
	}

	@Override
	public Movie findById(String mid) throws ResourceNotFoundException {
		Movie m = repo.findById(mid);
		if (m != null){
			return m;
		}else{
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<Movie> findByTitle(String title) {
		return repo.findByTitle(title);
	}

	@Override
	public Movie update(String mid, Movie movie) throws ResourceNotFoundException {
		if (repo.findById(mid) != null){
			return repo.update(movie);
		}else{
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public void delete(String mid) throws ResourceNotFoundException {
		Movie m = repo.findById(mid);
		if (m != null){
			repo.delete(m);
		}else{
			throw new ResourceNotFoundException();
		}
	}

}
