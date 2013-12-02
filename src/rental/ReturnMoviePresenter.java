package rental;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;
import interfaces.ITableChooserListener;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;

import common.DatabaseConstants;
import common.OkCancelView;
import common.UserInfo;
import common.objects.Address;
import common.objects.Customer;
import common.objects.Item;

import customer.SelectCustomerView;

public class ReturnMoviePresenter implements IInnerPanelPresenter {

	private IHomeScreenViewListener homeViewListener;
	private final SelectCustomerView selectionView;
	private final MovieRentalView rentalView;
	private OkCancelView currentView;
	private Customer selectedCustomer;
	private UserInfo userInfo;

	public ReturnMoviePresenter() {
		selectionView = new SelectCustomerView();
		rentalView = new MovieRentalView();
		currentView = selectionView;

		rentalView.setActionTitleText("Return Rentals");
		rentalView.setOkButtonLabel("Return");
		rentalView.setCancelButtonLabel("Cancel");

		addListeners();
	}

	private void addListeners() {
		selectionView.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				setCustomer();
				setCustomerRentals();
				setAvailableRentals();
				currentView = rentalView;
				homeViewListener.resetInnerPanelView();
			}

			@Override
			public void cancelButtonPressed() {
				homeViewListener.returnToHome();
			}
		});

		rentalView.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				// update model
				// update database
				homeViewListener.returnToHome();
			}

			@Override
			public void cancelButtonPressed() {
				currentView = selectionView;
				homeViewListener.resetInnerPanelView();
			}
		});

		selectionView.setTableViewListener(new ITableChooserListener() {

			@Override
			public void listSelectionChanged(List<Item> selectedValue) {
				selectedCustomer = (Customer) selectedValue.get(0);
				selectionView.enableOkButton(selectedValue.size() > 0);
			}
		});
	}

	private void setCustomer() {
		String name = selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName();
		Address address = getAddressForID(selectedCustomer.getAddressID());
		String address1 = address.getAddress();
		String address2 = address.getCity() + ", " + address.getState() + "  " + address.getZip();
		String phoneNumber = selectedCustomer.getPhoneNumber();
		String accountID = Integer.toString(selectedCustomer.getAccountID());

		rentalView.setCustomer(name, address1, address2, phoneNumber, accountID);
	}

	private void setAvailableRentals() {
		// TODO: perform database call here
		List<String> availableRentals = null;
		rentalView.setAvailableRentals(availableRentals);

	}

	private void setCustomerRentals() {
		// TODO: perform database call here
		List<String> customerRentals = null;
		rentalView.setCustomerRentals(customerRentals);
	}

	private Address getAddressForID(int addressID) {
		try {
			userInfo = DatabaseConstants.getUserInfo(selectedCustomer.getFirstName(), selectedCustomer.getLastName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Address(userInfo.getAddress(), userInfo.getCity(), userInfo.getState(), userInfo.getZipCode());
	}

	@Override
	public JPanel getView() {
		return currentView;
	}

	@Override
	public void addViewListener(IHomeScreenViewListener actionListener) {
		this.homeViewListener = actionListener;
	}
}
