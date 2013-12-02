package rental;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.ItemListModel;
import common.OkCancelView;
import common.StringListModel;
import common.objects.Item;

public class MovieRentalView extends OkCancelView {
	private static final long serialVersionUID = 1L;
	private final JLabel nameLabel;
	private final JLabel phoneNumberLabel;
	private final JLabel addressLabel1;
	private final JLabel addressLabel2;
	private final JLabel accountLabel;
	private final JLabel membershipLabel;
	private final JLabel sourceLabel;
	protected JList<String> sourceList;
	private final JScrollPane sourceScrollPane;
	private final JLabel destLabel;
	protected JList<String> destList;
	private final JScrollPane destScrollPane;
	private final JButton addButton;
	private final JButton removeButton;
	private MovieViewListener viewListener;

	private static final String ADD_BUTTON_LABEL = "Add >>";
	private static final String REMOVE_BUTTON_LABEL = "<< Remove";
	private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available";
	private static final String DEFAULT_DEST_CHOICE_LABEL = "Chosen";
	private static final String CUSTOMER_INFO_TITLE = "Customer Info";

	public MovieRentalView() {
		JLabel actionTitle = getActionTitle();
		layout.putConstraint(SpringLayout.WEST, actionTitle, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, actionTitle, 5, SpringLayout.NORTH, this);

		JLabel customerInfoTitle = new JLabel(CUSTOMER_INFO_TITLE);
		customerInfoTitle.setFont(new Font(this.getFont().getFamily(), Font.PLAIN, 16));
		layout.putConstraint(SpringLayout.WEST, customerInfoTitle, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, customerInfoTitle, 75, SpringLayout.SOUTH, actionTitle);
		add(customerInfoTitle);

		nameLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, nameLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.SOUTH, customerInfoTitle);
		add(nameLabel);

		addressLabel1 = new JLabel();
		layout.putConstraint(SpringLayout.WEST, addressLabel1, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, addressLabel1, 5, SpringLayout.SOUTH, nameLabel);
		add(addressLabel1);

		addressLabel2 = new JLabel();
		layout.putConstraint(SpringLayout.WEST, addressLabel2, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, addressLabel2, 5, SpringLayout.SOUTH, addressLabel1);
		add(addressLabel2);

		phoneNumberLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, phoneNumberLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, phoneNumberLabel, 5, SpringLayout.SOUTH, addressLabel2);
		add(phoneNumberLabel);

		accountLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, accountLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, accountLabel, 5, SpringLayout.SOUTH, phoneNumberLabel);
		add(accountLabel);

		membershipLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, membershipLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, membershipLabel, 5, SpringLayout.SOUTH, accountLabel);
		add(membershipLabel);

		destList = new JList<String>();
		sourceList = new JList<String>();
		viewListener = new MovieViewListener();
		sourceList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && sourceList.getSelectedIndex() != -1) {
					viewListener.listSelectionChanged(sourceList.getSelectedValuesList(), destList.getSelectedValuesList());
				}
			}
		});

		removeButton = new JButton(REMOVE_BUTTON_LABEL);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, removeButton, 400, SpringLayout.EAST, customerInfoTitle);
		layout.putConstraint(SpringLayout.NORTH, removeButton, 250, SpringLayout.NORTH, actionTitle);
		add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				setAvailableRentals(viewListener.addItems(destList.getSelectedValuesList(), sourceList));
				setCustomerRentals(viewListener.removeItems(destList.getSelectedValuesList(), destList));
			}
		});

		addButton = new JButton(ADD_BUTTON_LABEL);
		layout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.WEST, removeButton);
		layout.putConstraint(SpringLayout.SOUTH, addButton, -10, SpringLayout.NORTH, removeButton);
		add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				setCustomerRentals(viewListener.addItems(sourceList.getSelectedValuesList(), destList));
				setAvailableRentals(viewListener.removeItems(sourceList.getSelectedValuesList(), sourceList));
			}
		});

		sourceScrollPane = new JScrollPane(sourceList);
		sourceScrollPane.setPreferredSize(new Dimension(200, 300));
		layout.putConstraint(SpringLayout.EAST, sourceScrollPane, -5, SpringLayout.WEST, removeButton);
		layout.putConstraint(SpringLayout.NORTH, sourceScrollPane, -150, SpringLayout.NORTH, removeButton);
		add(sourceScrollPane);

		destScrollPane = new JScrollPane(destList);
		destScrollPane.setPreferredSize(new Dimension(200, 300));
		layout.putConstraint(SpringLayout.WEST, destScrollPane, 5, SpringLayout.EAST, removeButton);
		layout.putConstraint(SpringLayout.NORTH, destScrollPane, -150, SpringLayout.NORTH, removeButton);
		add(destScrollPane);

		sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
		layout.putConstraint(SpringLayout.WEST, sourceLabel, 0, SpringLayout.WEST, sourceScrollPane);
		layout.putConstraint(SpringLayout.SOUTH, sourceLabel, -5, SpringLayout.NORTH, sourceScrollPane);
		add(sourceLabel);

		destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
		layout.putConstraint(SpringLayout.WEST, destLabel, 0, SpringLayout.WEST, destScrollPane);
		layout.putConstraint(SpringLayout.SOUTH, destLabel, -5, SpringLayout.NORTH, destScrollPane);
		destLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(destLabel);
	}

	public void setCustomer(String name, String address1, String address2, String phoneNumber, String accountID) {
		nameLabel.setText(name);
		addressLabel1.setText(address1);
		addressLabel2.setText(address2);
		phoneNumberLabel.setText(phoneNumber);
		accountLabel.setText("Acct #: " + accountID);
	}

	public void setCustomerRentals(List<String> rentals) {
		destList.setModel(new StringListModel(rentals));
	}

	public void setAvailableRentals(List<String> availableRentals) {
		sourceList.setModel(new StringListModel(availableRentals));
	}

	public void addViewListener(MovieViewListener viewListener) {
		this.viewListener = viewListener;
	}
}
