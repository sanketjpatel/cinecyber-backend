package com.vdoshi3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class User {
	private int uid;
	private String uname;
	private String email;
	private String userid;
	private String upassword;
	private String salt;
	private String role;
}
