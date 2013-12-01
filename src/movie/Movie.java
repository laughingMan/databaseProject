package movie;

import common.Item;

public class Movie implements Item {

	private int genreID;
	private int length;
	private String title;
	private int year;
	private int movieID;
	private String format;

	public Movie(int genreID, int length, String title, int year, int movieID, String format) {
		this.genreID = genreID;
		this.length = length;
		this.year = year;
		this.movieID = movieID;
		this.format = format;
	}

	public int getGenreID() {
		return genreID;
	}

	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String getName() {
		return title;
	}

	@Override
	public String getID() {
		return Integer.toString(movieID);
	}
}
