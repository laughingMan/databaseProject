package customer;

import interfaces.HomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;
import interfaces.ITableChooserListener;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.DatabaseConstants;
import common.Item;
import common.SelectItemView;

public class RemoveCustomerPresenter implements IInnerPanelPresenter {

	protected SelectItemView view;
	private final CustomerModel model;
	private static final String REMOVE_CUSTOMER_TABLE_LABEL = "Select A Customer";
	private static final String REMOVE_CUSTOMER_TITLE = "Delete Customer";
	private static final String REMOVE_CUSTOMER_OK_BUTTON = "Delete";
	private static final String REMOVE_CUSTOMER_CANCEL_BUTTON = "Cancel";
	private static final String REMOVE_CUSTOMER_CONFIRMATION_LABEL = "Are you sure you would like to delete this customer?";
	private static final String REMOVE_CUSTOMER_CONFIRMATION_TITLE = "Remove Customer";
	private static final String DELETE_CUSTOMER_CONFIRMATION_LABEL = "Customer has been deleted";
	private static final String DELETE_CUSTOMER_CONFIRMATION_TITLE = "Customer Deleted";
	private HomeScreenViewListener homeScreenViewListener;
	private Customer selectedCustomer;

	public RemoveCustomerPresenter() {
		view = new SelectCustomerView();
		model = new CustomerModel();

		view.setActionTitleText(REMOVE_CUSTOMER_TITLE);
		view.setTableLabel(REMOVE_CUSTOMER_TABLE_LABEL);
		view.setOkButtonLabel(REMOVE_CUSTOMER_OK_BUTTON);
		view.setCancelButtonLabel(REMOVE_CUSTOMER_CANCEL_BUTTON);

		view.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				int dialogResult = JOptionPane.showConfirmDialog(view, REMOVE_CUSTOMER_CONFIRMATION_LABEL, REMOVE_CUSTOMER_CONFIRMATION_TITLE,
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(view, DELETE_CUSTOMER_CONFIRMATION_LABEL, DELETE_CUSTOMER_CONFIRMATION_TITLE, JOptionPane.OK_OPTION);
					try {
						DatabaseConstants.deleteUser(selectedCustomer.getCustomerID());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					homeScreenViewListener.returnToHome();
				}
			}

			@Override
			public void cancelButtonPressed() {
				homeScreenViewListener.returnToHome();
			}
		});

		view.setViewListener(new ITableChooserListener() {
			@Override
			public void listSelectionChanged(List<Item> selectedValue) {
				selectedCustomer = (Customer) selectedValue.get(0);
				view.enableOkButton(selectedValue.size() > 0);
			}
		});
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void addViewListener(HomeScreenViewListener homeScreenViewListener) {
		this.homeScreenViewListener = homeScreenViewListener;
	}
}
