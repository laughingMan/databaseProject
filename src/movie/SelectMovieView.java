package movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DatabaseConstants;
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

		try {
			setCustomerList(DatabaseConstants.getAllMovies());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
