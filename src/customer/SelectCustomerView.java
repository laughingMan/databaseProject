package customer;

import interfaces.ITableChooserListener;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.CustomerTableModel;
import common.OkCancelView;

public class SelectCustomerView extends OkCancelView {
	private static final long serialVersionUID = 1L;
	private JList<Customer> sourceList;
	private final JScrollPane sourceScrollPane;
	private final JTable table;
	private final CustomerTableModel customerTableModel;
	private ITableChooserListener viewListener;

	public SelectCustomerView() {

		// Title
		setActionTitleText("Select A Customer");

		// Customer table
		List<Customer> customers = createCustomers();
		customerTableModel = new CustomerTableModel(customers);
		table = new JTable(customerTableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowSelectionAllowed(true);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// if (!e.getValueIsAdjusting() && sourceList.getSelectedIndex()
				// != -1) {
				// viewListener.listSelectionChanged(customerTableModel.getCustomerForIndices(table.getSelectedRows()));
				// }
			}
		});
		table.setSelectionModel(selectionModel);

		// Customer table view
		sourceScrollPane = new JScrollPane(table);
		sourceScrollPane.setPreferredSize(new Dimension(500, 300));
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, sourceScrollPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, sourceScrollPane, 40, SpringLayout.SOUTH, getActionTitle());
		add(sourceScrollPane);

		// buttons
		setOkButtonLabel("Select");
		setCancelButtonLabel("Cancel");

		setVisible(true);
	}

	private List<Customer> createCustomers() {
		List<Customer> customers = new ArrayList<Customer>();

		Customer customer = new Customer("isaac", "hatton", 231, "6187918662", 222, 433, 345);
		Customer customer2 = new Customer("joe", "blow", 234, "2134537890", 111, 245, 786);
		Customer customer3 = new Customer("john", "smith", 255, "1233435456", 344, 996, 432);

		customers.add(customer);
		customers.add(customer2);
		customers.add(customer3);

		return customers;
	}

	public void setCustomerList(List<Customer> customers) {
		customerTableModel.setCustomers(customers);
	}

	public void setViewListener(ITableChooserListener viewListener) {
		this.viewListener = viewListener;
	}
}
