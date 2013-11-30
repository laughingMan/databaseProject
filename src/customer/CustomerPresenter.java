package customer;

import interfaces.IInnerPanelPresenter;

import javax.swing.JPanel;

import movie.MovieModel;

public class CustomerPresenter implements IInnerPanelPresenter {

	private final MovieModel model;
	private final CreateCustomerView view;

	public CustomerPresenter() {
		model = new MovieModel();
		view = new CreateCustomerView();

	}

	@Override
	public JPanel getView() {
		return view;
	}
}
