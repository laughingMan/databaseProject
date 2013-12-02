package customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DatabaseConstants;
import common.Item;
import common.SelectItemView;

public class RemoveCustomerView extends SelectItemView {
	private static final long serialVersionUID = 1L;

	private static final String REMOVE_CUSTOMER_TITLE = "Delete Customer";

	public RemoveCustomerView() {
		// Title
		setActionTitleText(REMOVE_CUSTOMER_TITLE);

		try {
			setCustomerList(DatabaseConstants.getAllCustomers());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
