package movie;

import homeView.MainView;
import interfaces.IInnerPanelPresenter;
import interfaces.ITableChooserListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import common.Item;
import common.OkCancelView;
import common.SelectItemView;

import customer.Customer;
import customer.CustomerModel;
import customer.SelectCustomerView;

public class RentMoviePresenter implements IInnerPanelPresenter {

	private final SelectItemView selectionView;
	private final RentMovieView rentalView;
	private final OkCancelView currentView;
	private final MainView rootView;
	private final CustomerModel model;
	private Customer selectedCustomer;

	private static final String RENT_MOVIE_ACTION_TITLE = "Rent Movie";
	private static final String RENT_MOVIE_OK_BUTTON = "Rent";
	private static final String RENT_MOVIE_CANCEL_BUTTON = "Back";

	public RentMoviePresenter(MainView innerPanelView) {
		rootView = innerPanelView;
		model = new CustomerModel();
		selectionView = new SelectCustomerView();
		rentalView = new RentMovieView();
		currentView = selectionView;

		selectionView.setActionTitleText(RENT_MOVIE_ACTION_TITLE);
		rentalView.setActionTitleText(RENT_MOVIE_ACTION_TITLE);
		rentalView.setOkButtonLabel(RENT_MOVIE_OK_BUTTON);
		rentalView.setCancelButtonLabel(RENT_MOVIE_CANCEL_BUTTON);

		addListeners();
	}

	private String getAddressForID(int addressID) {
		// TODO: remove and make database call
		return "123 Address Ln.";
	}

	private void addListeners() {
		selectionView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToRentMovieView();
			}
		});

		selectionView.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// switch view to home screen
			}
		});

		rentalView.addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update model
				// update database
				// switch view to home screen
			}
		});

		rentalView.addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToSelectCustomerView();
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

	private void switchToSelectCustomerView() {
		rootView.setInnerPanel(selectionView);
	}

	private void switchToRentMovieView() {
		setCustomer();
		rootView.setInnerPanel(rentalView);
	}

	private void setCustomer() {
		String name = selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName();
		String address = getAddressForID(selectedCustomer.getAddressID());
		String phoneNumber = selectedCustomer.getPhoneNumber();
		String accountID = Integer.toString(selectedCustomer.getAccountID());
		String memebershipID = Integer.toString(selectedCustomer.getMemebershipID());

		rentalView.setCustomer(name, address, phoneNumber, accountID, memebershipID);
	}

	@Override
	public JPanel getView() {
		return currentView;
	}
}
