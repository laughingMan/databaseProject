package customer;

import java.sql.SQLException;

import rental.SelectItemView;

import common.DatabaseConstants;

public class SelectCustomerView extends SelectItemView {
	private static final long serialVersionUID = 1L;

	private static final String SELECT_CUSTOMER_TITLE = "Select A Customer";
	private static final String SELECT_CUSTOMER_OK_BUTTON = "Select";
	private static final String SELECT_CUSTOMER_CANCEL_BUTTON = "Cancel";

	public SelectCustomerView() {
		// Title
		setTableLabel(SELECT_CUSTOMER_TITLE);

		// buttons
		setOkButtonLabel(SELECT_CUSTOMER_OK_BUTTON);
		setCancelButtonLabel(SELECT_CUSTOMER_CANCEL_BUTTON);

		try {
			setItemList(DatabaseConstants.getAllCustomers());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
