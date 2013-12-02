package movie;

import java.util.ArrayList;
import java.util.List;

import rental.SelectItemView;

import common.objects.Item;
import common.objects.Movie;

public class SelectMovieView extends SelectItemView {
	private static final long serialVersionUID = 1L;

	private static final String SELECT_MOVIE_TABLE_LABEL = "Select A Movie";
	private static final String SELECT_MOVIE_OK_BUTTON = "Select";
	private static final String SELECT_MOVIE_CANCEL_BUTTON = "Cancel";

	public SelectMovieView() {
		// Titles
		setTableLabel(SELECT_MOVIE_TABLE_LABEL);

		// buttons
		setOkButtonLabel(SELECT_MOVIE_OK_BUTTON);
		setCancelButtonLabel(SELECT_MOVIE_CANCEL_BUTTON);

		setItemList(createMovies());
	}

	private List<Item> createMovies() {
		List<Item> movies = new ArrayList<Item>();

		Movie movie1 = new Movie("Ghostbusters", 123, 1980, "vhs", 123, 1);
		Movie movie2 = new Movie("Ghostbusters II", 234, 1982, "dvd", 234, 1);
		Movie movie3 = new Movie("Ghostbusters III", 345, 1983, "bluray", 345, 1);

		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);

		return movies;
	}
}
