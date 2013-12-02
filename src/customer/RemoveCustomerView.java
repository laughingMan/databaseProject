package customer;

import java.util.ArrayList;
import java.util.List;

import common.Item;
import common.SelectItemView;

public class RemoveCustomerView extends SelectItemView {
	private static final long serialVersionUID = 1L;

	private static final String REMOVE_CUSTOMER_TITLE = "Delete Customer";

	public RemoveCustomerView() {
		// Title
		setActionTitleText(REMOVE_CUSTOMER_TITLE);

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
