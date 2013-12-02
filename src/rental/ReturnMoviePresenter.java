package rental;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;
import interfaces.ITableChooserListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.ListModel;

import common.DatabaseConstants;
import common.OkCancelView;
import common.UserInfo;
import common.objects.Address;
import common.objects.Customer;
import common.objects.Item;
import common.objects.Movie;
import customer.SelectCustomerView;

public class ReturnMoviePresenter implements IInnerPanelPresenter {

	private IHomeScreenViewListener homeViewListener;
	private final SelectCustomerView selectionView;
	private final MovieRentalView returnView;
	private OkCancelView currentView;
	private Customer selectedCustomer;
	private UserInfo userInfo;

	public ReturnMoviePresenter() {
		selectionView = new SelectCustomerView();
		try {
			selectionView.setItemList(DatabaseConstants.getAllCustomersForReturns());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		returnView = new MovieRentalView();
		currentView = selectionView;

		selectionView.setActionTitleText("Return Rentals");
		returnView.setActionTitleText("Return Rentals");
		returnView.setOkButtonLabel("Return");
		returnView.setCancelButtonLabel("Cancel");

		addListeners();
	}

	private void addListeners() {
		selectionView.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				setCustomer();
				setCustomerRentals();
				setAvailableRentals();
				currentView = returnView;
				homeViewListener.resetInnerPanelView();
			}

			@Override
			public void cancelButtonPressed() {
				homeViewListener.returnToHome();
			}
		});

		returnView.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				ListModel<String> moviesToBeReturned = returnView.destList.getModel();
				for (int i = 0; i < moviesToBeReturned.getSize(); i++) {
					try {
						Movie movieInfo = DatabaseConstants.getMovieInfo(moviesToBeReturned.getElementAt(i));
						DatabaseConstants.returnRental(movieInfo.getMovieId(), selectedCustomer.getAccountID());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
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

		returnView.setCustomer(name, address1, address2, phoneNumber, accountID);
	}

	private void setAvailableRentals() {
		List<String> availableRentals = new ArrayList<>();
		try {
			availableRentals = DatabaseConstants.getCurrentRentalsForUser(selectedCustomer.getCustomerID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		returnView.setAvailableRentals(availableRentals);

	}

	private void setCustomerRentals() {
		List<String> customerRentals = new ArrayList<>();
		returnView.setCustomerRentals(customerRentals);
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
