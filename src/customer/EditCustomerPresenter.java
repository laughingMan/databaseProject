package customer;

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

public class EditCustomerPresenter implements IInnerPanelPresenter {

	private final CustomerModel model;
	private final AddCustomerView editView;
	private final SelectCustomerView selectionView;
	private OkCancelView currentView;
	private Customer selectedCustomer;

	private static final String RENT_MOVIE_ACTION_TITLE = "Edit Customer";
	private static final String EDIT_CUSTOMER_OK_BUTTON = "Edit";
	private static final String EDIT_CUSTOMER_CANCEL_BUTTON = "Back";
	private IHomeScreenViewListener homeViewListener;

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
		selectionView.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				setCustomer();
				currentView = editView;
				homeViewListener.resetInnerPanelView();
			}

			@Override
			public void cancelButtonPressed() {
				homeViewListener.returnToHome();
			}
		});

		editView.setViewListener(new IOkCancelButtonsListener() {
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
	public void addViewListener(IHomeScreenViewListener homeScreenViewListener) {
		this.homeViewListener = homeScreenViewListener;
	}
}
