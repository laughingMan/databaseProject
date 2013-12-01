package movie;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.Item;
import common.OkCancelView;

public class RentMovieView extends OkCancelView {
	private static final long serialVersionUID = 1L;
	private ActionListener actionListener;
	private final JLabel nameLabel;
	private final JLabel phoneNumberLabel;
	private final JLabel addressLabel;
	private final JLabel accountLabel;
	private final JLabel membershipLabel;
	private final JLabel sourceLabel;
	private final JList<Item> sourceList;
	private final JScrollPane sourceScrollPane;
	private final JLabel destLabel;
	private final JList<Item> destList;
	private final JScrollPane destScrollPane;
	private final JButton addButton;
	private final JButton removeButton;
	private RentMovieViewListener viewListener;

	private static final String ADD_BUTTON_LABEL = "Add >>";
	private static final String REMOVE_BUTTON_LABEL = "<< Remove";
	private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available";
	private static final String DEFAULT_DEST_CHOICE_LABEL = "Chosen";
	private static final String CUSTOMER_INFO_TITLE = "Customer Info";
	private static final String RENT_MOVIE_OK_BUTTON = "Rent";
	private static final String RENT_MOVIE_CANCEL_BUTTON = "Back";
	private static final String RENT_MOVIE_TITLE = "Rent Movie";

	public RentMovieView() {

		setActionTitleText(RENT_MOVIE_TITLE);
		JLabel actionTitle = getActionTitle();
		layout.putConstraint(SpringLayout.WEST, actionTitle, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, actionTitle, 5, SpringLayout.NORTH, this);

		JLabel customerInfoTitle = new JLabel(CUSTOMER_INFO_TITLE);
		customerInfoTitle.setFont(new Font(this.getFont().getFamily(), Font.PLAIN, 16));
		layout.putConstraint(SpringLayout.WEST, customerInfoTitle, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, customerInfoTitle, 20, SpringLayout.SOUTH, actionTitle);
		add(customerInfoTitle);

		nameLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, nameLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.SOUTH, customerInfoTitle);
		add(nameLabel);

		addressLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, addressLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, addressLabel, 5, SpringLayout.SOUTH, nameLabel);
		add(addressLabel);

		phoneNumberLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, phoneNumberLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, phoneNumberLabel, 5, SpringLayout.SOUTH, addressLabel);
		add(phoneNumberLabel);

		accountLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, accountLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, accountLabel, 5, SpringLayout.SOUTH, phoneNumberLabel);
		add(accountLabel);

		membershipLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, membershipLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, membershipLabel, 5, SpringLayout.SOUTH, accountLabel);
		add(membershipLabel);

		destList = new JList<Item>();
		sourceList = new JList<Item>();
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
		layout.putConstraint(SpringLayout.NORTH, removeButton, 225, SpringLayout.NORTH, this);
		add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				viewListener.removeItems(destList.getSelectedValuesList());
			}
		});

		addButton = new JButton(ADD_BUTTON_LABEL);
		layout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.WEST, removeButton);
		layout.putConstraint(SpringLayout.SOUTH, addButton, -10, SpringLayout.NORTH, removeButton);
		add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				viewListener.addItems(sourceList.getSelectedValuesList());
			}
		});

		sourceScrollPane = new JScrollPane(sourceList);
		sourceScrollPane.setPreferredSize(new Dimension(200, 300));
		layout.putConstraint(SpringLayout.EAST, sourceScrollPane, -5, SpringLayout.WEST, removeButton);
		layout.putConstraint(SpringLayout.NORTH, sourceScrollPane, -115, SpringLayout.NORTH, removeButton);
		add(sourceScrollPane);

		destScrollPane = new JScrollPane(destList);
		destScrollPane.setPreferredSize(new Dimension(200, 300));
		layout.putConstraint(SpringLayout.WEST, destScrollPane, 5, SpringLayout.EAST, removeButton);
		layout.putConstraint(SpringLayout.NORTH, destScrollPane, -115, SpringLayout.NORTH, removeButton);
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

		setOkButtonLabel(RENT_MOVIE_OK_BUTTON);
		addOkButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewListener.okPressed();
			}
		});

		setCancelButtonLabel(RENT_MOVIE_CANCEL_BUTTON);
		addCancelButtonPressedListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewListener.cancelPressed();
			}
		});
	}

	public void setCustomer(String name, String address, String phoneNumber, String accountID, String membershipID) {
		nameLabel.setText(name);
		addressLabel.setText(address);
		phoneNumberLabel.setText(phoneNumber);
		accountLabel.setText("Acct #: " + accountID);
		membershipLabel.setText("Acct Type: " + membershipID);
	}

	public void addViewListener(RentMovieViewListener viewListener) {
		this.viewListener = viewListener;
	}
}
