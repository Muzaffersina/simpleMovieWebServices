package com.msa.WebMovieServices.dataAccess.concretes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.msa.WebMovieServices.dataAccess.abstracts.DataService;
import com.msa.WebMovieServices.entity.Movie;

@Component
public class InFile implements DataService {

	private File file = new File("movieList.txt");

	public InFile() {
		this.init();
	}

	// check if the file exists when starter
	public void init() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Save movie
	@Override
	public Boolean save(Movie movie) throws IOException {

		if (checkIfMovieExists(movie.getImdbId()) == null) {
			FileWriter fWritter = new FileWriter(file, true);
			BufferedWriter bWriter = new BufferedWriter(fWritter);
			try {
				bWriter.write(movie.toString().substring(5) + "\n");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				bWriter.close();
			}
		}
		return false;
	}

	@Override
	public Movie getByImdbId(String movieId) throws IOException {
		if (checkIfMovieExists('"' + movieId + '"') != null) {
			return stringParsingForMovie(checkIfMovieExists('"' + movieId + '"'));
		}
		return null;
	}

	// bonus
	@Override
	public List<Movie> getAll() throws IOException {

		FileReader freader = new FileReader(file);
		String line = null;
		BufferedReader bReader = new BufferedReader(freader);
		List<String> movieList = new ArrayList<>();

		try {
			while ((line = bReader.readLine()) != null) {
				movieList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bReader.close();
		}
		return listStringParsingForMovieList(movieList);
	}

	// Does this movie exist in the movieList.txt file?
	private String checkIfMovieExists(String movieId) throws IOException {

		FileReader freader = new FileReader(file);
		String line = null;
		BufferedReader bReader = new BufferedReader(freader);

		try {
			while ((line = bReader.readLine()) != null) {
				if (line.contains("imdbId=" + movieId)) {
					// movie found with imdbId
					return line;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bReader.close();
		}
		return line = null;
	}

	// for file text --> return Movie.class
	private Movie stringParsingForMovie(String text) {

		List<String> movieItem = new ArrayList<>();

		for (String parsedText : text.split(",")) {
			parsedText.trim();
			parsedText = parsedText.substring(parsedText.indexOf("=") + 1).replace('"', ' ').trim();
			movieItem.add(parsedText);
		}
		return new Movie(movieItem.get(0), movieItem.get(1), movieItem.get(2), movieItem.get(3), movieItem.get(4));
	}

	// for file text --> movie.class --> return List<Movie>
	private List<Movie> listStringParsingForMovieList(List<String> text) {

		List<Movie> movieList = new ArrayList<>();

		for (String listItem : text) {

			movieList.add(stringParsingForMovie(listItem));
		}

		return movieList;
	}

}
