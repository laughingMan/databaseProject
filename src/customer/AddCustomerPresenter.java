package customer;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;

import javax.swing.JPanel;

public class AddCustomerPresenter implements IInnerPanelPresenter {

	private final CustomerModel model;
	private final AddCustomerView view;
	private IHomeScreenViewListener viewListener;

	public AddCustomerPresenter() {
		model = new CustomerModel();
		view = new AddCustomerView();

		view.setActionTitleText("Add New Customer");
		view.setOkButtonLabel("Add");
		view.setCancelButtonLabel("Cancel");

		setListeners();
	}

	private void setListeners() {
		view.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				// update model
				// update database
				viewListener.returnToHome();
			}

			@Override
			public void cancelButtonPressed() {
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
	public void addViewListener(IHomeScreenViewListener viewListener) {
		this.viewListener = viewListener;
	}

	@Override
	public JPanel getView() {
		return view;
	}
}
