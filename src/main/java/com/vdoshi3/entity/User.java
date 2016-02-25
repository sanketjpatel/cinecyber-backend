package com.vdoshi3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table
public class User {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator( name = "uuid", strategy ="uuid2")
	private int uid;
	private String uname;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String userid;
	private String upassword;
	private String salt;
	private String role;
}
