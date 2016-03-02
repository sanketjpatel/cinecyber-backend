package com.vdoshi3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.vdoshi3.entity.Comment;

@Repository
public class CommentDaoImp implements CommentDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public Comment create(Comment comment) {
		em.persist(comment);
		return comment;
	}

	@Override
	public List<Comment> findAll() {
		TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c", Comment.class);
		return query.getResultList();
	}

	@Override
	public Comment findByCid(String cid) {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findByCid", Comment.class);
		query.setParameter("vCid", cid);
		List<Comment> comments = query.getResultList();
		if (comments != null && comments.size() == 1) {
			return comments.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> findByMid(String mid) {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findByMid", Comment.class);
		query.setParameter("vMid", mid);
		return query.getResultList();
	}

	@Override
	public List<Comment> findByUid(String uid) {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findByUid", Comment.class);
		query.setParameter("vUid", uid);
		return query.getResultList();
	}

	@Override
	public List<Comment> findByMidUid(String mid, String uid) {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findByMidUid", Comment.class);
		query.setParameter("vMid", mid);
		query.setParameter("vUid", uid);
		return query.getResultList();
	}

	@Override
	public void delete(Comment comment) {
		em.remove(comment);
	}

}
