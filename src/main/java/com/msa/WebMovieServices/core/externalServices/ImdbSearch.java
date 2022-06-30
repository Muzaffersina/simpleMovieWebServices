package com.msa.WebMovieServices.core.externalServices;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;



/*
 *  Searching on imdb    (with imdb api == https://collectapi.com/api/imdb/imdb-api )
 *  
 *  	and
 *  
 *  return type is "String"
 */

@Component
public class ImdbSearch {

	private RestTemplate restTemplate;
	
	/*
	 * Reading values in application.properties
	 */
	
	@Value(value = "${imdb.contentType}")
	private String contentType;	
	@Value(value = "${imdb.authorization}")
	private String authorization;
	@Value(value = "${imdb.getMovieByNameUrl}")
	private String getMovieByNameUrl;
	@Value(value = "${imdb.getMovieByIdUrl}")
	private String getMovieByIdUrl;

	@Autowired
	public ImdbSearch(RestTemplate restTemplate) {

		this.restTemplate = restTemplate;
	}

	public String imdbSearchByName(String movieName) throws JsonMappingException, JsonProcessingException {

		HttpHeaders headers = new HttpHeaders();
		// Set header
		headers.set("content-type", contentType);		
		headers.set("authorization", authorization);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		// Response Type  is string
		ResponseEntity<String> response = restTemplate.exchange(getMovieByNameUrl + movieName, HttpMethod.GET, 
				entity,String.class);		
		String body = response.getBody();
	
		return body;

	}

	public String imdbSearchById(String movieId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("content-type", contentType);
		headers.set("authorization", authorization);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Response Type  is string
		ResponseEntity<String> response = restTemplate.exchange(getMovieByIdUrl + movieId, HttpMethod.GET, 
				entity, String.class);
		String body = response.getBody();
		
		return body;
	}

}
