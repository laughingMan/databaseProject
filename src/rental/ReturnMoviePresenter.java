package rental;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;
import interfaces.ITableChooserListener;

import java.util.List;

import javax.swing.JPanel;

import common.OkCancelView;
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

	private Address getAddressForID(int addressID) {
		// TODO: make database call
		return new Address("123 Address Ln.", "Testville", "OK", "12345");
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
