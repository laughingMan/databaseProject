package customer;

import java.sql.SQLException;

import rental.SelectItemView;

import common.DatabaseConstants;

public class RemoveCustomerView extends SelectItemView {
	private static final long serialVersionUID = 1L;

	private static final String REMOVE_CUSTOMER_TITLE = "Delete Customer";

	public RemoveCustomerView() {
		// Title
		setActionTitleText(REMOVE_CUSTOMER_TITLE);

		try {
			setItemList(DatabaseConstants.getAllCustomers());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
