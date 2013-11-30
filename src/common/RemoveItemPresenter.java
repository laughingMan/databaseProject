package common;

import interfaces.IInnerPanelPresenter;

import javax.swing.JPanel;

import customer.CustomerModel;

public class RemoveItemPresenter implements IInnerPanelPresenter {

	private final RemoveItemView view;
	private final CustomerModel model;

	public RemoveItemPresenter() {
		view = new RemoveItemView();
		model = new CustomerModel();
	}

	@Override
	public JPanel getView() {
		return view;
	}

}
