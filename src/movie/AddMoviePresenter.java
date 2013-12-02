package movie;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;

import javax.swing.JPanel;

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
				// update model
				// update database
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
