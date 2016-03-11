package com.vdoshi3.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.mid = :vMid"),
		@NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title LIKE :vTitle") })
public class Movie {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String mid;
	private String title;
	private int myear;
	private String rated;
	private Date released;
	private String runtime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	@Column(length = 400)
	private String plot;
	private String lang;
	private String country;
	private String awards;
	private String poster;
	private int metascore;
	private double imdbRating;
	private int imdbVotes;
	private String imdbID;
	private String mtype;
	private double avgRating;
}