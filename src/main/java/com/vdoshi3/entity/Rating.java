package com.vdoshi3.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Rating.findByRid", query = "SELECT r FROM Rating r WHERE r.rid = :vRid"),
		@NamedQuery(name = "Rating.findByMid", query = " SELECT r FROM Rating r WHERE r.movie.mid = :vMid"),
		@NamedQuery(name = "Rating.findByUid", query = " SELECT r FROM Rating r WHERE r.user.uid = :vUid"),
		@NamedQuery(name = "Rating.findByMidUid", query = " SELECT r FROM Rating r WHERE r.movie.mid = :vMid AND r.user.uid = :vUid") })
public class Rating {

	@Id
	@GeneratedValue
	private int rid;
	private double urating;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mid", referencedColumnName = "mid", nullable = false)
	private Movie movie;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
	private User user;
}