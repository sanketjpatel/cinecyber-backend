package com.vdoshi3.utils;

import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	public static JsonNode parseFile(String filepath){
		JsonNode node = null;
		try{
	        ObjectMapper mapper = new ObjectMapper();
        	node = mapper.readValue(new FileReader(filepath), JsonNode.class);

		}catch(IOException ie){
			System.out.println(ie);
		}
		return node;
	}

}
