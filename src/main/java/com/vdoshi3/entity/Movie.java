package com.vdoshi3.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Movie {
	private int mid;
	private String title;
	private int myear;
	private String rated;
	private Date released;
	private String runtime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	private String plot;
	private String lang;
	private String country;
	private String awards;
	private String poster;
	private int metascore;
	private float imdbRating;
	private int imdbVotes;
	private String imdbID;
	private String mtype;
	private float avgRating;
}