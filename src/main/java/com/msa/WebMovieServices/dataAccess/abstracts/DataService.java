package com.msa.WebMovieServices.dataAccess.abstracts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.msa.WebMovieServices.entity.Movie;

public interface DataService {

	Boolean save(Movie movie) throws IOException;

	Movie getByImdbId(String movieId) throws FileNotFoundException, IOException;

	List<Movie> getAll() throws IOException;
}
