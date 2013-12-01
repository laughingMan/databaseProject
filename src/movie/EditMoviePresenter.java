package movie;

import interfaces.IInnerPanelPresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class EditMoviePresenter implements IInnerPanelPresenter {

	private final SelectMovieView selectionView;
	private final AddMovieView editView;

	String EDIT_MOVIE_ACTION_TITLE = "Edit Movie";
	String EDIT_MOVIE_OK_BUTTON = "Edit";
	String EDIT_MOVIE_CANCEL_BUTTON = "Back";

	public EditMoviePresenter() {
		selectionView = new SelectMovieView();
		editView = new AddMovieView();

		selectionView.setActionTitleText(EDIT_MOVIE_ACTION_TITLE);
		editView.setActionTitleText(EDIT_MOVIE_ACTION_TITLE);
		editView.setOkButtonLabel(EDIT_MOVIE_OK_BUTTON);
		editView.setCancelButtonLabel(EDIT_MOVIE_CANCEL_BUTTON);

		addListeners();
	}

	private void addListeners() {
		editView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update model
				// update database
				// change view to home screen
			}
		});

		editView.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// change view to home screen
			}
		});

		selectionView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// editView.setMovie();

			}
		});
	}

	@Override
	public JPanel getView() {
		return null;
	}

}
