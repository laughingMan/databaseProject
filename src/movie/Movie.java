package movie;

import common.Item;

public class Movie implements Item {

	private String title;
	private int length;
	private int year;
	private String format;
	private int movieId;

	public Movie(String title, int length, int year, String format, int movieId) {
		this.title = title;
		this.length = length;
		this.year = year;
		this.format = format;
		this.movieId = movieId;
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
		return String.valueOf(movieId);
	}


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

}
