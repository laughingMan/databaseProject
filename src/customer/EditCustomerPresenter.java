package customer;

import interfaces.HomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.ITableChooserListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import common.Address;
import common.Item;
import common.OkCancelView;

public class EditCustomerPresenter implements IInnerPanelPresenter {

	private final CustomerModel model;
	private final AddCustomerView editView;
	private final SelectCustomerView selectionView;
	private OkCancelView currentView;
	private Customer selectedCustomer;

	private static final String RENT_MOVIE_ACTION_TITLE = "Edit Customer";
	private static final String EDIT_CUSTOMER_OK_BUTTON = "Edit";
	private static final String EDIT_CUSTOMER_CANCEL_BUTTON = "Back";
	private HomeScreenViewListener homeViewListener;

	public EditCustomerPresenter() {
		model = new CustomerModel();
		editView = new AddCustomerView();
		selectionView = new SelectCustomerView();
		currentView = selectionView;

		selectionView.setActionTitleText(RENT_MOVIE_ACTION_TITLE);
		editView.setActionTitleText(RENT_MOVIE_ACTION_TITLE);
		editView.setOkButtonLabel(EDIT_CUSTOMER_OK_BUTTON);
		editView.setCancelButtonLabel(EDIT_CUSTOMER_CANCEL_BUTTON);

		addListeners();
	}

	private void addListeners() {
		selectionView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCustomer();
				currentView = editView;
				homeViewListener.resetInnerPanelView();
			}
		});

		selectionView.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homeViewListener.returnToHome();
			}
		});

		editView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update model
				// update database
				homeViewListener.returnToHome();
			}
		});

		editView.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentView = selectionView;
				homeViewListener.resetInnerPanelView();
			}
		});

		selectionView.setViewListener(new ITableChooserListener() {
			@Override
			public void listSelectionChanged(List<Item> selectedValue) {
				selectedCustomer = (Customer) selectedValue.get(0);
				selectionView.enableOkButton(selectedValue.size() > 0);
			}
		});
	}

	private void setCustomer() {
		String firstName = selectedCustomer.getFirstName();
		String lastName = selectedCustomer.getLastName();
		Address customerAddress = getAddressForID(selectedCustomer.getAddressID());
		String address = customerAddress.getAddress();
		String city = customerAddress.getCity();
		String state = customerAddress.getState();
		String zip = customerAddress.getZip();
		String phoneNumber = selectedCustomer.getPhoneNumber();
		String accountID = Integer.toString(selectedCustomer.getAccountID());
		String memebershipID = Integer.toString(selectedCustomer.getMemebershipID());

		editView.setCustomer(firstName, lastName, address, city, state, zip, phoneNumber, accountID, memebershipID);
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
	public void addViewListener(HomeScreenViewListener homeScreenViewListener) {
		this.homeViewListener = homeScreenViewListener;
	}
}
