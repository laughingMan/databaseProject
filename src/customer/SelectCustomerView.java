package customer;

import java.util.ArrayList;
import java.util.List;

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

		setCustomerList(createCustomers());
	}

	private List<Item> createCustomers() {
		List<Item> customers = new ArrayList<Item>();

		Customer customer = new Customer("isaac", "hatton", 231, "6187918662", 222, 433, 345);
		Customer customer2 = new Customer("joe", "blow", 234, "2134537890", 111, 245, 786);
		Customer customer3 = new Customer("john", "smith", 255, "1233435456", 344, 996, 432);

		customers.add(customer);
		customers.add(customer2);
		customers.add(customer3);

		return customers;
	}
}
