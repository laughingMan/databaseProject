package customer;

import interfaces.HomeScreenViewListener;
import interfaces.IInnerPanelPresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class AddCustomerPresenter implements IInnerPanelPresenter {

	private final CustomerModel model;
	private final AddCustomerView view;
	private HomeScreenViewListener viewListener;

	public AddCustomerPresenter() {
		model = new CustomerModel();
		view = new AddCustomerView();

		view.setActionTitleText("Add New Customer");
		view.setOkButtonLabel("Add");
		view.setCancelButtonLabel("Cancel");

		setListeners();
	}

	private void setListeners() {
		view.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update model
				// update database
				viewListener.returnToHome();
			}
		});

		view.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if (!view.fieldsAreEmpty()) {
				// view.setCancelButtonLabel("Back");
				// view.clearFields();
				// } else {
				viewListener.returnToHome();
				// }
			}
		});
	}

	@Override
	public void addViewListener(HomeScreenViewListener viewListener) {
		this.viewListener = viewListener;
	}

	@Override
	public JPanel getView() {
		return view;
	}
}
