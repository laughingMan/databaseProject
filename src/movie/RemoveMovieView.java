package movie;

import common.SelectItemView;

public class RemoveMovieView extends SelectItemView {
	private static final long serialVersionUID = 1L;

	private final SelectItemView view;
	private final MovieModel model;

	private static final String REMOVE_MOVIE_TITLE = "Delete Movie";

	public RemoveMovieView() {
		view = new SelectItemView();
		model = new MovieModel();

		view.setActionTitleText(REMOVE_MOVIE_TITLE);
	}

}
