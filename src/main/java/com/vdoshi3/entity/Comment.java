package com.vdoshi3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Comment.findByCid", query = " SELECT c FROM Comment c WHERE c.cid = :vCid"),
		@NamedQuery(name = "Comment.findByMid", query = " SELECT c FROM Comment c WHERE c.mid = :vMid"),
		@NamedQuery(name = "Comment.findByUid", query = " SELECT c FROM Comment c WHERE c.uid = :vUid"),
		@NamedQuery(name = "Comment.findByMidUid", query = " SELECT c FROM Comment c WHERE c.mid = :vMid AND c.uid = :vUid") })
public class Comment {
	@Id
	@GeneratedValue
	private int cid;
	private String mid;
	private String uid;
	private String ucomment;
}