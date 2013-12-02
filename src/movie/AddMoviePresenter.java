package movie;

import interfaces.HomeScreenViewListener;
import interfaces.IInnerPanelPresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class AddMoviePresenter implements IInnerPanelPresenter {

	private final AddMovieView view;
	private final MovieModel model;
	private HomeScreenViewListener homeViewListener;

	private static final String CREATE_CUSTOMER_TITLE = "Add A New Movie";
	private static final String CREATE_CUSTOMER_OK_BUTTON = "Add";
	private static final String CREATE_CUSTOMER_CANCEL_BUTTON = "Cancel";

	public AddMoviePresenter() {
		model = new MovieModel();
		view = new AddMovieView();

		view.setActionTitleText(CREATE_CUSTOMER_TITLE);
		view.setOkButtonLabel(CREATE_CUSTOMER_OK_BUTTON);
		view.setCancelButtonLabel(CREATE_CUSTOMER_CANCEL_BUTTON);
		setListeners();
	}

	private void setListeners() {
		view.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update model
				// update database
				homeViewListener.returnToHome();
			}
		});

		view.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
	public void addViewListener(HomeScreenViewListener homeScreenViewListener) {
		this.homeViewListener = homeScreenViewListener;
	}
}
