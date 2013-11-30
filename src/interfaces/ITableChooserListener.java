package interfaces;

import java.util.List;

import customer.Customer;

public interface ITableChooserListener {

	void listSelectionChanged(List<Customer> selectedValue);

}
