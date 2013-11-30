package common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import customer.Customer;

public class CustomerTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private Object[][] data;
	private final Object[] columnNames = { "Account ID", "First Name", "Last Name" };
	private List<Customer> customers;

	public CustomerTableModel(List<Customer> customers) {
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	@Override
	public String getColumnName(int col) {
		return (String) columnNames[col];
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
		data = new Object[customers.size()][];
		int index = 0;
		for (Customer customer : customers) {
			ArrayList<Object> test = new ArrayList<Object>();
			test.add(customer.getAccountID());
			test.add(customer.getFirstName());
			test.add(customer.getLastName());
			Object[] array = test.toArray();
			data[index++] = array;
		}
		fireTableDataChanged();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Customer> getCustomerForIndices(int[] indexes) {
		List<Customer> customerList = new ArrayList<>();
		for (int selectedRowIndex : indexes) {
			Customer customer = customers.get(selectedRowIndex);
			customerList.add(customer);
		}
		return customerList;
	}
}