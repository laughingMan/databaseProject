package movie;

public class Movie {

	private final int genreID;
	private final int length;
	private String title;
	private final int year;
	private final int movieID;
	private final String format;

	public Movie(int genreID, int length, String title, int year, int movieID, String format) {
		this.genreID = genreID;
		this.length = length;
		this.year = year;
		this.movieID = movieID;
		this.format = format;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGenreID() {
		return genreID;
	}

	public int getLength() {
		return length;
	}

	public int getYear() {
		return year;
	}

	public int getMovieID() {
		return movieID;
	}

	public String getFormat() {
		return format;
	}
}
