package movie;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;

import java.sql.SQLException;

import javax.swing.JPanel;

import common.DatabaseConstants;

public class AddMoviePresenter implements IInnerPanelPresenter {

	private final MovieView view;
	private final MovieModel model;
	private IHomeScreenViewListener homeViewListener;

	private static final String CREATE_CUSTOMER_TITLE = "Add A New Movie";
	private static final String CREATE_CUSTOMER_OK_BUTTON = "Add";
	private static final String CREATE_CUSTOMER_CANCEL_BUTTON = "Cancel";

	public AddMoviePresenter() {
		model = new MovieModel();
		view = new MovieView();

		view.setActionTitleText(CREATE_CUSTOMER_TITLE);
		view.setOkButtonLabel(CREATE_CUSTOMER_OK_BUTTON);
		view.setCancelButtonLabel(CREATE_CUSTOMER_CANCEL_BUTTON);
		setListeners();
	}

	private void setListeners() {
		view.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				try {
					DatabaseConstants.addMovie(Integer.valueOf(view.lengthField.getText()), view.titleField.getText(),
							Integer.valueOf(view.yearField.getText()), view.formatField.getText(), 100);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				homeViewListener.returnToHome();
			}

			@Override
			public void cancelButtonPressed() {
				// if (!view.fieldsAreClear()) {
				// view.clearFields();
				// view.setCancelButtonLabel("Back");
				// } else {
				homeViewListener.returnToHome();
				// }
			}
		});
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void addViewListener(IHomeScreenViewListener homeScreenViewListener) {
		this.homeViewListener = homeScreenViewListener;
	}
}
