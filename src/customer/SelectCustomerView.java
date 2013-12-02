package customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DatabaseConstants;
import common.Item;
import common.SelectItemView;

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
			setCustomerList(DatabaseConstants.getAllCustomers());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
