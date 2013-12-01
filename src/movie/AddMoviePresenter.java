package movie;

import interfaces.IInnerPanelPresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class AddMoviePresenter implements IInnerPanelPresenter {

	private final AddMovieView view;
	private final MovieModel model;

	private static final String CREATE_CUSTOMER_TITLE = "Add A New Customer";
	private static final String CREATE_CUSTOMER_OK_BUTTON = "Add";
	private static final String CREATE_CUSTOMER_CANCEL_BUTTON = "Clear";

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
				// return user to Home screen
			}
		});

		view.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// clear fields
				// return user to home screen
			}
		});
	}

	@Override
	public JPanel getView() {
		return view;
	}
}
