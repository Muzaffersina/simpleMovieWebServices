package com.msa.WebMovieServices.api.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.msa.WebMovieServices.core.result.DataResult;
import com.msa.WebMovieServices.core.result.Result;
import com.msa.WebMovieServices.entity.Movie;
import com.msa.WebMovieServices.services.abstracts.MovieSearchService;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

	private MovieSearchService movieSearchService;

	@Autowired
	public MoviesController(MovieSearchService movieSearchService) {
		this.movieSearchService = movieSearchService;
	}

	@GetMapping("/search")
	public DataResult<List<Movie>> search(@RequestParam("movie_name") String movieName)
			throws JsonMappingException, JsonProcessingException {
		return this.movieSearchService.getMoviesSearchByName(movieName);
	}

	@PostMapping("/saveToList/{imdbId}")
	public Result addToList(@PathVariable(name = "imdbId") String imdbId) throws IOException {
		return this.movieSearchService.getMoviesSearchById(imdbId);
	}

	@PostMapping("/detail/{imdbId}")
	public DataResult<Movie> detail(@PathVariable(name = "imdbId") String imdbId)
			throws FileNotFoundException, IOException {
		return this.movieSearchService.getDetailByMovieId(imdbId);
	}

	@GetMapping("/getAllMovieList") // Bonus
	public DataResult<List<Movie>> getAll() throws IOException {
		return this.movieSearchService.getAllMovieList();
	}

}
