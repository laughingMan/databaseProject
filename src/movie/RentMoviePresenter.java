package movie;

import homeView.MainView;
import interfaces.IInnerPanelPresenter;
import interfaces.ITableChooserListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import common.OkCancelView;

import customer.Customer;
import customer.CustomerModel;
import customer.SelectCustomerView;

public class RentMoviePresenter implements IInnerPanelPresenter {

	private final SelectCustomerView selectCustomerView;
	private final RentMovieView rentMovieView;
	private final OkCancelView currentView;
	private final MainView rootView;
	private final CustomerModel model;
	private Customer selectedCustomer;

	public RentMoviePresenter(MainView innerPanelView) {
		this.rootView = innerPanelView;
		this.model = new CustomerModel();
		this.selectCustomerView = new SelectCustomerView();
		this.rentMovieView = new RentMovieView();
		this.currentView = selectCustomerView;

		addListeners();
		this.selectCustomerView.setViewListener(new ITableChooserListener() {

			@Override
			public void listSelectionChanged(List<Customer> selectedValue) {
				selectedCustomer = selectedValue.get(0);
				selectCustomerView.enableOkButton(selectedValue.size() > 0);
			}
		});

	}

	private String getAddressForID(int addressID) {
		// TODO: make database call
		return "123 Address Ln.";
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
		setCustomer();
		rootView.setInnerPanel(rentMovieView);
	}

	private void setCustomer() {
		String name = selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName();
		String address = getAddressForID(selectedCustomer.getAddressID());
		String customerID = Integer.toString(selectedCustomer.getCustomerID());
		String phoneNumber = selectedCustomer.getPhoneNumber();
		String accountID = Integer.toString(selectedCustomer.getAccountID());
		String memebershipID = Integer.toString(selectedCustomer.getMemebershipID());

		rentMovieView.setCustomer(name, address, phoneNumber, customerID, accountID, memebershipID);
	}

	private void switchToSelectCustomerView() {
		rootView.setInnerPanel(selectCustomerView);
	}

	@Override
	public JPanel getView() {
		return currentView;
	}
}
