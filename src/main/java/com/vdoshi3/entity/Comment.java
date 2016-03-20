package com.vdoshi3.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Comment.findByCid", query = " SELECT c FROM Comment c WHERE c.cid = :vCid"),
		@NamedQuery(name = "Comment.findByMid", query = " SELECT c FROM Comment c WHERE c.movie.mid = :vMid"),
		@NamedQuery(name = "Comment.findByUid", query = " SELECT c FROM Comment c WHERE c.user.uid = :vUid"),
		@NamedQuery(name = "Comment.findByMidUid", query = " SELECT c FROM Comment c WHERE c.movie.mid = :vMid AND c.user.uid = :vUid") })
public class Comment {
	@Id
	@GeneratedValue
	@JsonView(View.Public.class)
	private int cid;
	@JsonView(View.Public.class)
	@Column(length = 400)
	private String ucomment;
	@JsonView(View.Public.class)
	private Date timestamp;

//	@JsonIgnore
	@JsonView(View.Public.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mid", referencedColumnName = "mid", nullable = false)
	private Movie movie;

//	@JsonIgnore
	@JsonView(View.Public.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
	private User user;
}