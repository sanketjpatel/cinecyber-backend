package com.vdoshi3.dao;

import java.util.List;

import com.vdoshi3.entity.Comment;

public interface CommentDao {

	public Comment create(Comment comment);

	public List<Comment> findAll();

	public Comment findByCid(String cid);

	public List<Comment> findByMid(String mid);

	public List<Comment> findByUid(String uid);

	public List<Comment> findByMidUid(String mid, String uid);

	public void delete(Comment comment);
}
