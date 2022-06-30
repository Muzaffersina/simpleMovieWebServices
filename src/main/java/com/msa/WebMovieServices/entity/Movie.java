package com.msa.WebMovieServices.entity;

import lombok.Data;

@Data // auto generate getter - setter with lombok
public class Movie {

	private String imdbId;
	private String title;
	private String year;
	private String type;
	private String poster;

	public Movie(String imdbId, String title, String year, String type, String poster) {
		this.imdbId = imdbId;
		this.title = title;
		this.year = year;
		this.type = type;
		this.poster = poster;
	}

}
