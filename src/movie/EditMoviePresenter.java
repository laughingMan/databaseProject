package movie;

import interfaces.HomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.ITableChooserListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import common.Item;
import common.OkCancelView;

public class EditMoviePresenter implements IInnerPanelPresenter {

	private final SelectMovieView selectionView;
	private final AddMovieView editView;
	private Movie selectedMovie;

	String EDIT_MOVIE_ACTION_TITLE = "Edit Movie";
	String EDIT_MOVIE_OK_BUTTON = "Edit";
	String EDIT_MOVIE_CANCEL_BUTTON = "Back";
	private HomeScreenViewListener homeViewListener;
	private OkCancelView currentView;

	public EditMoviePresenter() {
		selectionView = new SelectMovieView();
		editView = new AddMovieView();
		currentView = selectionView;

		selectionView.setActionTitleText(EDIT_MOVIE_ACTION_TITLE);
		editView.setActionTitleText(EDIT_MOVIE_ACTION_TITLE);
		editView.setOkButtonLabel(EDIT_MOVIE_OK_BUTTON);
		editView.setCancelButtonLabel(EDIT_MOVIE_CANCEL_BUTTON);

		addListeners();
	}

	private void addListeners() {
		selectionView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMovie();
				currentView = editView;
				homeViewListener.resetInnerPanelView();
			}
		});

		editView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update model
				// update database
				homeViewListener.returnToHome();
			}
		});

		editView.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!editView.fieldsAreClear()) {
					currentView = selectionView;
					homeViewListener.resetInnerPanelView();
				}
			}
		});

		selectionView.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homeViewListener.returnToHome();
			}
		});

		selectionView.setViewListener(new ITableChooserListener() {
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
		String genreID = Integer.toString(selectedMovie.getGenreID());

		editView.setMovie(title, length, year, format, genreID);
	}

	@Override
	public JPanel getView() {
		return currentView;
	}

	@Override
	public void addViewListener(HomeScreenViewListener homeScreenViewListener) {
		this.homeViewListener = homeScreenViewListener;
	}
}
