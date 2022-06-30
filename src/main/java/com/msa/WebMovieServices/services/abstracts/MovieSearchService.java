package com.msa.WebMovieServices.services.abstracts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.msa.WebMovieServices.core.result.DataResult;
import com.msa.WebMovieServices.core.result.Result;
import com.msa.WebMovieServices.entity.Movie;

public interface MovieSearchService {

	DataResult<List<Movie>> getMoviesSearchByName(String movieName) throws JsonMappingException, JsonProcessingException;

	Result getMoviesSearchById(String imdbId) throws JsonMappingException, JsonProcessingException, IOException;

	DataResult<Movie> getDetailByMovieId(String imdbId) throws FileNotFoundException, IOException;

	DataResult<List<Movie>> getAllMovieList() throws IOException;
}
