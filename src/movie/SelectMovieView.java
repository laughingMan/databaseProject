package movie;

import java.util.ArrayList;
import java.util.List;

import common.Item;
import common.SelectItemView;

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

		setCustomerList(createMovies());
	}

	private List<Item> createMovies() {
		List<Item> movies = new ArrayList<Item>();

		Movie movie1 = new Movie(123, 123, "Ghostbusters", 1980, 123, "comedy");
		Movie movie2 = new Movie(234, 234, "Ghostbusters II", 1982, 234, "comedy");
		Movie movie3 = new Movie(345, 345, "Ghostbusters III", 1983, 345, "comedy");

		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);

		return movies;
	}

}
