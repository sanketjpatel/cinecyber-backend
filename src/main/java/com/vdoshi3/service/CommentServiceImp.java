package com.vdoshi3.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdoshi3.dao.CommentDao;
import com.vdoshi3.dao.MovieDao;
import com.vdoshi3.dao.UserDao;
import com.vdoshi3.entity.Comment;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.ResourceNotFoundException;

@Service
@Transactional
public class CommentServiceImp implements CommentService {

	@Autowired
	CommentDao repo;
	@Autowired
	MovieDao movieRepo;
	@Autowired
	UserDao userRepo;

	@Override
	public Comment create(String mid, String uid, Comment comment) throws ResourceNotFoundException {
		Movie m = movieRepo.findById(mid);
		User u = userRepo.findById(uid);
		if (m != null && u != null) {
			comment.setMovie(m);
			comment.setUser(u);
			return repo.create(comment);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<Comment> findAll() {
		return repo.findAll();
	}

	@Override
	public Comment findByCid(String cid) throws ResourceNotFoundException {
		Comment c = repo.findByCid(cid);
		if (c != null) {
			return c;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<Comment> findByMid(String mid) {
		return repo.findByMid(mid);
	}

	@Override
	public List<Comment> findByUid(String uid) {
		return repo.findByUid(uid);
	}

	@Override
	public List<Comment> findByMidUid(String mid, String uid) {
		return repo.findByMidUid(mid, uid);
	}

	@Override
	public void delete(String cid) throws ResourceNotFoundException {
		Comment c = repo.findByCid(cid);
		if (c != null) {
			repo.delete(c);
		} else {
			throw new ResourceNotFoundException();
		}
	}

}
