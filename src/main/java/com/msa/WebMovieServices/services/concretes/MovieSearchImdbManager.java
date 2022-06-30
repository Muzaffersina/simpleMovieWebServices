package com.msa.WebMovieServices.services.concretes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.msa.WebMovieServices.core.externalServices.ImdbSearch;
import com.msa.WebMovieServices.core.mapper.MovieMapper;
import com.msa.WebMovieServices.core.result.DataResult;
import com.msa.WebMovieServices.core.result.ErrorResult;
import com.msa.WebMovieServices.core.result.Result;
import com.msa.WebMovieServices.core.result.SuccessDataResult;
import com.msa.WebMovieServices.core.result.SuccessResult;
import com.msa.WebMovieServices.dataAccess.abstracts.DataService;
import com.msa.WebMovieServices.entity.Movie;
import com.msa.WebMovieServices.services.abstracts.MovieSearchService;

@Service
public class MovieSearchImdbManager implements MovieSearchService {

	private ImdbSearch imbdSearch;
	private DataService dataService;
	private MovieMapper movieMapper;

	@Autowired
	public MovieSearchImdbManager(ImdbSearch imbdSearch, DataService dataService, MovieMapper movieMapper) {
		this.imbdSearch = imbdSearch;
		this.dataService = dataService;
		this.movieMapper = movieMapper;
	}

	@Override
	public DataResult<List<Movie>> getMoviesSearchByName(String movieName)
			throws JsonMappingException, JsonProcessingException {

		String body = this.imbdSearch.imdbSearchByName(movieName);
		// Manuel Map and return List<Movie>
		List<Movie> movies = this.movieMapper.manuelMappingForMovieNames(body);
		return new SuccessDataResult<List<Movie>>(movies, "Listed Movies");

	}

	@Override
	public Result getMoviesSearchById(String imdbId) throws IOException {

		String body = this.imbdSearch.imdbSearchById(imdbId);

		// Manuel Map and file write
		if (this.dataService.save((this.movieMapper.manuelMappingForMovieId(body)))) {
			return new SuccessResult("Added to movieList!");
		}
		return new ErrorResult("This movie id already exists in the file!");
	}

	@Override
	public DataResult<Movie> getDetailByMovieId(String imdbId) throws FileNotFoundException, IOException {
		// check if this movie is in file
		if (this.dataService.getByImdbId(imdbId) != null) {
			Movie movie = this.dataService.getByImdbId(imdbId);
			return new SuccessDataResult<Movie>(movie, "Listed Movie From Your Favorite List");
		}
		// if this movie is not in the file, it will search in imdb api
		String body = this.imbdSearch.imdbSearchById(imdbId);
		// and String body will be mapped to Movie.class
		Movie movie = this.movieMapper.manuelMappingForMovieId(body);
		return new SuccessDataResult<Movie>(movie, "Listed Movie From IMDB API");
	}

	@Override
	public DataResult<List<Movie>> getAllMovieList() throws IOException {
		// Bonus
		// get all the movies from the file
		List<Movie> movies = this.dataService.getAll();
		return new SuccessDataResult<List<Movie>>(movies, "Listed All Movies From Your Favorite List");
	}
}
