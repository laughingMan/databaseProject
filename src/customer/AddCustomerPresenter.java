package customer;

import interfaces.IInnerPanelPresenter;

import javax.swing.JPanel;

public class AddCustomerPresenter implements IInnerPanelPresenter {

	private final CustomerModel model;
	private final CreateCustomerView view;

	public AddCustomerPresenter() {
		model = new CustomerModel();
		view = new CreateCustomerView();

	}

	@Override
	public JPanel getView() {
		return view;
	}
}
