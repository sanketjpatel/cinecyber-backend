package com.vdoshi3.database;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.vdoshi3.entity.User;

import lombok.Getter;

public class UserDatabase {
	@Getter
	private static Map<String,User> users = new ConcurrentHashMap<String,User>();
	
}
