package common.objects;


public class Movie implements Item {

	private String title;
	private int length;
	private int year;
	private int movieID;
	private int genreID;
	private String format;

	public Movie(String title, int length, int year, String format, int movieID, int genreID) {
		this.title = title;
		this.length = length;
		this.year = year;
		this.format = format;
		this.movieID = movieID;
		this.genreID = genreID;
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
