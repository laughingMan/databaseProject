package customer;

import interfaces.IButtonsPressed;
import interfaces.IInnerPanelModel;
import interfaces.ITableChooserListener;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.CustomerTableModel;

public class SelectCustomerView extends JPanel implements IButtonsPressed {
	private static final long serialVersionUID = 1L;
	private JList<Customer> sourceList;
	private JScrollPane sourceScrollPane;
	private JButton addButton;
	private JButton cancelButton;
	private JTable table;
	private CustomerTableModel customerTableModel;
	private ITableChooserListener viewListener;
	private final IInnerPanelModel customerModel;

	public SelectCustomerView(IInnerPanelModel innerPanelModel) {
		this.customerModel = innerPanelModel;
		layoutPanel();
	}

	private void layoutPanel() {
		setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// Title
		JLabel tileLabel = new JLabel("Select A Customer");
		tileLabel.setFont(new Font(this.getFont().getFamily(), Font.PLAIN, 30));
		layout.putConstraint(SpringLayout.WEST, tileLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tileLabel, 20, SpringLayout.NORTH, this);
		add(tileLabel);

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
				// if (!e.getValueIsAdjusting() && sourceList.getSelectedIndex() != -1) {
				// viewListener.listSelectionChanged(customerTableModel.getCustomerForIndices(table.getSelectedRows()));
				// }
			}
		});
		table.setSelectionModel(selectionModel);

		// Customer table view
		sourceScrollPane = new JScrollPane(table);
		sourceScrollPane.setPreferredSize(new Dimension(500, 300));
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, sourceScrollPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, sourceScrollPane, 40, SpringLayout.SOUTH, tileLabel);
		add(sourceScrollPane);

		// add button
		addButton = new JButton("Select");
		layout.putConstraint(SpringLayout.EAST, addButton, 5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, addButton, 5, SpringLayout.SOUTH, this);
		add(addButton);

		// cancel button
		cancelButton = new JButton("Cancel");
		layout.putConstraint(SpringLayout.EAST, cancelButton, 5, SpringLayout.WEST, addButton);
		layout.putConstraint(SpringLayout.SOUTH, cancelButton, 5, SpringLayout.SOUTH, this);
		add(cancelButton);

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

	@Override
	public void addOkButtonPressedListener(ActionListener listener) {
		addButton.addActionListener(listener);
	}

	@Override
	public void addCancelButtonPressedListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

	public void setCustomerList(List<Customer> customers) {
		customerTableModel.setCustomers(customers);
	}

	public void setViewListener(ITableChooserListener viewListener) {
		this.viewListener = viewListener;
	}
}
