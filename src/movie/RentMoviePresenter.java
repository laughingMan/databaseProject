package movie;

import homeView.MainView;
import interfaces.IInnerPanelPresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import common.OkCancelView;

import customer.CustomerModel;
import customer.SelectCustomerView;

public class RentMoviePresenter implements IInnerPanelPresenter {

	private final SelectCustomerView selectCustomerView;
	private final RentMovieView rentMovieView;
	private final OkCancelView currentView;
	private final MainView rootView;
	private final CustomerModel model;

	public RentMoviePresenter(MainView innerPanelView) {
		this.rootView = innerPanelView;
		this.model = new CustomerModel();
		this.selectCustomerView = new SelectCustomerView();
		this.rentMovieView = new RentMovieView();
		this.currentView = selectCustomerView;

		addListeners();
	}

	private void addListeners() {
		this.selectCustomerView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToRentMovieView();
			}
		});

		this.rentMovieView.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToSelectCustomerView();
			}
		});
	}

	private void switchToRentMovieView() {
		rootView.setInnerPanel(rentMovieView);
	}

	private void switchToSelectCustomerView() {
		rootView.setInnerPanel(selectCustomerView);
	}

	@Override
	public JPanel getView() {
		return currentView;
	}
}
