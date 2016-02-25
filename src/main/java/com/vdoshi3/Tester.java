package com.vdoshi3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdoshi3.controller.UserControllerImp;
import com.vdoshi3.entity.Movie;
import com.vdoshi3.entity.User;
import com.vdoshi3.utils.JsonReader;

public class Tester {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
//		User u1 = new User();
//		u1.setUid(1);
//		u1.setUserid("rocky101");
//		u1.setUname("Rocky");
//		u1.setUpassword("Balboa");
//		u1.setEmail("rocky101@boxing.com");
//		User u2 = new User();
//		u2.setUid(1);
//		u2.setUserid("shocky101");
//		u2.setUname("Shocky");
//		u2.setUpassword("Shreiff");
//		u2.setEmail("shocky101@boxing.com");
//		System.out.println(u1.toString());
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		UserController uc = context.getBean(UserController.class);
//		System.out.println(uc.create(u1).toString());
//		System.out.println(uc.create(u2).toString());
//		System.out.println(uc.findAll().toString());
//		System.out.println(uc.find("rocky101"));
//		context.close();
		JsonNode node = JsonReader.parseFile("src/main/resources/testjson.json");
		System.out.println(node.toString());
		List<Movie> movies = new ArrayList<Movie>();
		for(JsonNode childNode :node){
			Movie movie = new Movie();
			movie.setTitle(childNode.get("Title").textValue());
			movies.add(movie);
			
		}
		System.out.println("Movies:"+movies.toString());
		
	}

}
