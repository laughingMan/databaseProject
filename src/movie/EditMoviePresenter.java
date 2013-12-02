package movie;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;
import interfaces.ITableChooserListener;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;

import common.DatabaseConstants;
import common.OkCancelView;
import common.objects.Item;
import common.objects.Movie;

public class EditMoviePresenter implements IInnerPanelPresenter {

	private final SelectMovieView selectionView;
	private final MovieView editView;
	private Movie selectedMovie;

	String EDIT_MOVIE_ACTION_TITLE = "Edit Movie";
	String EDIT_MOVIE_OK_BUTTON = "Edit";
	String EDIT_MOVIE_CANCEL_BUTTON = "Back";
	private IHomeScreenViewListener homeViewListener;
	private OkCancelView currentView;

	public EditMoviePresenter() {
		selectionView = new SelectMovieView();
		editView = new MovieView();
		currentView = selectionView;

		selectionView.setActionTitleText(EDIT_MOVIE_ACTION_TITLE);
		editView.setActionTitleText(EDIT_MOVIE_ACTION_TITLE);
		editView.setOkButtonLabel(EDIT_MOVIE_OK_BUTTON);
		editView.setCancelButtonLabel(EDIT_MOVIE_CANCEL_BUTTON);

		addListeners();
	}

	private void addListeners() {
		selectionView.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				setMovie();
				currentView = editView;
				homeViewListener.resetInnerPanelView();
			}

			@Override
			public void cancelButtonPressed() {
				try {
					DatabaseConstants.updateMovie(editView.titleField.getText(), Integer.valueOf(editView.lengthField.getText()),
							Integer.valueOf(editView.yearField.getText()), editView.formatField.getText(), selectedMovie.getMovieId());
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				homeViewListener.returnToHome();
			}
		});

		editView.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				// update model
				// update database
				homeViewListener.returnToHome();
			}

			@Override
			public void cancelButtonPressed() {
				currentView = selectionView;
				homeViewListener.resetInnerPanelView();
			}
		});

		selectionView.setTableViewListener(new ITableChooserListener() {
			@Override
			public void listSelectionChanged(List<Item> selectedValue) {
				selectedMovie = (Movie) selectedValue.get(0);
				selectionView.enableOkButton(selectedValue.size() > 0);
			}
		});
	}

	private void setMovie() {
		String title = selectedMovie.getTitle();
		String length = Integer.toString(selectedMovie.getLength());
		String year = Integer.toString(selectedMovie.getYear());
		String format = selectedMovie.getFormat();

		editView.setMovie(title, length, year, format);
	}

	@Override
	public JPanel getView() {
		return currentView;
	}

	@Override
	public void addViewListener(IHomeScreenViewListener homeScreenViewListener) {
		this.homeViewListener = homeScreenViewListener;
	}
}
