package movie;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;
import interfaces.ITableChooserListener;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rental.SelectItemView;

import common.DatabaseConstants;
import common.objects.Item;
import common.objects.Movie;

public class RemoveMoviePresenter implements IInnerPanelPresenter {

	protected SelectItemView view;
	private final MovieModel model;
	private static final String REMOVE_MOVIE_TITLE = "Delete Movie";
	private static final String REMOVE_MOVIE_TABLE_TITLE = "Select A Movie";
	private static final String REMOVE_MOVIE_OK_BUTTON = "Delete";
	private static final String REMOVE_MOVIE_CANCEL_BUTTON = "Cancel";
	private static final String REMOVE_MOVIE_CONFIRMATION_LABEL = "Are you sure you would like to delete this movie?";
	private static final String REMOVE_MOVIE_CONFIRMATION_TITLE = "Remove Item";
	private static final String DELETE_MOVIE_CONFIRMATION_LABEL = "Movie has been deleted";
	private static final String DELETE_MOVIE_CONFIRMATION_TITLE = "Movie Deleted";
	private IHomeScreenViewListener homeScreenViewListener;
	private Movie selectedMovie;

	public RemoveMoviePresenter() {
		view = new SelectMovieView();
		model = new MovieModel();

		view.setActionTitleText(REMOVE_MOVIE_TITLE);
		view.setTableLabel(REMOVE_MOVIE_TABLE_TITLE);
		view.setOkButtonLabel(REMOVE_MOVIE_OK_BUTTON);
		view.setCancelButtonLabel(REMOVE_MOVIE_CANCEL_BUTTON);

		view.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				int dialogResult = JOptionPane.showConfirmDialog(view, REMOVE_MOVIE_CONFIRMATION_LABEL, REMOVE_MOVIE_CONFIRMATION_TITLE,
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(view, DELETE_MOVIE_CONFIRMATION_LABEL, DELETE_MOVIE_CONFIRMATION_TITLE, JOptionPane.OK_OPTION);
					try {
						DatabaseConstants.deleteMovie(selectedMovie.getTitle(), selectedMovie.getFormat());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					homeScreenViewListener.returnToHome();
				}
			}

			@Override
			public void cancelButtonPressed() {
				homeScreenViewListener.returnToHome();
			}
		});

		view.setTableViewListener(new ITableChooserListener() {
			@Override
			public void listSelectionChanged(List<Item> selectedValue) {
				selectedMovie = (Movie) selectedValue.get(0);
				view.enableOkButton(selectedValue.size() > 0);
			}
		});
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void addViewListener(IHomeScreenViewListener homeScreenViewListener) {
		this.homeScreenViewListener = homeScreenViewListener;
	}
}
