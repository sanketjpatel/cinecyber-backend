package com.vdoshi3.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdoshi3.dao.MovieDao;

import com.vdoshi3.entity.Movie;

@Service
@Transactional
public class DataLoader {

	@Autowired
	private MovieDao repo;

	@Autowired
	private ServletContext servletContext;
	
	public List<Movie> loadMovieData() throws ParseException, JsonParseException, JsonMappingException, IOException {

		List<Movie> movies = new ArrayList<Movie>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readValue(JsonString(), JsonNode.class);
		for (JsonNode movieNode : node) {
			Movie movie = new Movie();
			movie.setTitle(movieNode.get("Title").asText());
			movie.setMyear(movieNode.get("Year").asInt());
			movie.setRated(movieNode.get("Rated").asText());
			movie.setReleased(formatter.parse((movieNode.get("Released").asText())));
			movie.setRuntime(movieNode.get("Runtime").asText());
			movie.setGenre(movieNode.get("Genre").asText());
			movie.setDirector(movieNode.get("Director").asText());
			movie.setWriter(movieNode.get("Writer").asText());
			movie.setActors(movieNode.get("Actors").asText());
			movie.setPlot(movieNode.get("Plot").asText());
			movie.setLang(movieNode.get("Language").asText());
			movie.setCountry(movieNode.get("Country").asText());
			movie.setAwards(movieNode.get("Awards").asText());
			movie.setPoster(movieNode.get("Poster").asText());
			movie.setMetascore(movieNode.get("Metascore").asInt());
			movie.setImdbRating(movieNode.get("imdbRating").asDouble());
			movie.setImdbVotes(NumberFormat.getNumberInstance(java.util.Locale.US).parse(movieNode.get("imdbVotes").asText()).intValue());
			movie.setImdbID(movieNode.get("imdbID").asText());
			movie.setMtype(movieNode.get("Type").asText());
			
			System.out.println(movie.toString());
			movies.add(repo.create(movie));
		}
		return movies;
	}

	public String JsonString() throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = servletContext.getResourceAsStream("/WEB-INF/classes/testjson.json");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String str = bufferedReader.readLine();
			StringBuilder result = new StringBuilder();
			while(str!=null){
				result.append(str);
				str = bufferedReader.readLine();
			}
			String resultingString = result.toString();
			System.out.println("String read from file"+resultingString);
			return resultingString;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
}
