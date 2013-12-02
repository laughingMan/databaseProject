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
import customer.CustomerModel;
import customer.SelectCustomerView;

public class RentMoviePresenter implements IInnerPanelPresenter {

	private final SelectItemView selectionView;
	private final MovieRentalView rentalView;
	private OkCancelView currentView;
	private final CustomerModel model;
	private Customer selectedCustomer;
	private IHomeScreenViewListener homeViewListener;
	private UserInfo userInfo;

	private static final String RENT_MOVIE_ACTION_TITLE = "Rent Movie";
	private static final String RENT_MOVIE_OK_BUTTON = "Rent";
	private static final String RENT_MOVIE_CANCEL_BUTTON = "Back";

	public RentMoviePresenter() {
		model = new CustomerModel();
		selectionView = new SelectCustomerView();
		rentalView = new MovieRentalView();
		currentView = selectionView;

		selectionView.setActionTitleText(RENT_MOVIE_ACTION_TITLE);
		rentalView.setActionTitleText(RENT_MOVIE_ACTION_TITLE);
		rentalView.setOkButtonLabel(RENT_MOVIE_OK_BUTTON);
		rentalView.setCancelButtonLabel(RENT_MOVIE_CANCEL_BUTTON);

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
				ListModel<String> moviesToBeRented = rentalView.destList.getModel();
				for (int i = 0; i < moviesToBeRented.getSize(); i++) {
					try {
						Movie movieInfo = DatabaseConstants.getMovieInfo(moviesToBeRented.getElementAt(i));
						DatabaseConstants.addRental(movieInfo.getMovieId(), selectedCustomer.getAccountID());
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

	private void setAvailableRentals() {
		List<String> availableRentals = new ArrayList<String>();
		try {
			availableRentals = DatabaseConstants.getPossibleRentalsForUser(selectedCustomer.getCustomerID());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		rentalView.setAvailableRentals(availableRentals);

	}

	private void setCustomerRentals() {
		List<String> customerRentals = new ArrayList<String>();
//		try {
//			customerRentals = DatabaseConstants.getCurrentRentalsForUser(selectedCustomer.getCustomerID());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		rentalView.setCustomerRentals(customerRentals);
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
	public void addViewListener(IHomeScreenViewListener viewListener) {
		this.homeViewListener = viewListener;
	}
}
