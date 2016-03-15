package com.vdoshi3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
@NamedQueries({ @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :vEmail"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.uid = :vUid") })
public class User {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String uid;
	private String fullname;
	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String encryptedPassword;
	@Transient
	private String userpassword;
	@JsonIgnore
	private String salt;
	@JsonIgnore
	private String role;
	private String profilePic;
}
