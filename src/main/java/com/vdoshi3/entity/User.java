package com.vdoshi3.entity;

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
}
