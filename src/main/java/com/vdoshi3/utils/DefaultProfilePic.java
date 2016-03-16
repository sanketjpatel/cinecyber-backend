package com.vdoshi3.utils;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
@Component
public class DefaultProfilePic {
	static ArrayList<String> listOfPictures;
	
	static{
		listOfPictures = new ArrayList<String>();
		listOfPictures.add("http://localhost:8000/profile_pics/default/profilealienblue.jpg");
		listOfPictures.add("http://localhost:8000/profile_pics/default/profilealiengreen.jpg");
		listOfPictures.add("http://localhost:8000/profile_pics/default/profilealienpink.jpg");
		listOfPictures.add("http://localhost:8000/profile_pics/default/profilealienred.jpg");
		listOfPictures.add("http://localhost:8000/profile_pics/default/profilemonsterblue.jpg");
		listOfPictures.add("http://localhost:8000/profile_pics/default/profilemonstergreen.jpg");
		listOfPictures.add("http://localhost:8000/profile_pics/default/profilemonsterpink.jpg");
		listOfPictures.add("http://localhost:8000/profile_pics/default/profilemonsteryellow.jpg");
	}

	public String getRandomPicture(){
		return listOfPictures.get(randInt(listOfPictures.size()));
	}
	
	public int randInt(int max) {

	    int randomNum = (int) Math.floor((Math.random() * max));
	    return randomNum;
	}
}
