package customer;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import common.OkCancelView;

public class AddCustomerView extends OkCancelView {
	private static final long serialVersionUID = 1L;

	private final JTextField firstNameField;
	private final JTextField lastNameField;
	private final JTextField addressField;
	private final JTextField cityField;
	private final JTextField stateField;
	private final JTextField zipCodeField;
	private final JTextField phoneField;

	public AddCustomerView() {

		// ---------------------- //
		// ----- First Name ----- //
		// ---------------------- //
		JLabel actionTitle = getActionTitle();
		JLabel firstNameLabel = new JLabel("First Name: ");
		layout.putConstraint(SpringLayout.WEST, firstNameLabel, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, firstNameLabel, 45, SpringLayout.SOUTH, actionTitle);
		add(firstNameLabel);

		firstNameField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, firstNameField, 5, SpringLayout.EAST, firstNameLabel);
		layout.putConstraint(SpringLayout.NORTH, firstNameField, 40, SpringLayout.SOUTH, actionTitle);
		add(firstNameField);

		// --------------------- //
		// ----- Last Name ----- //
		// --------------------- //
		JLabel lastNameLabel = new JLabel("Last Name: ");
		layout.putConstraint(SpringLayout.WEST, lastNameLabel, 5, SpringLayout.EAST, firstNameField);
		layout.putConstraint(SpringLayout.NORTH, lastNameLabel, 5, SpringLayout.NORTH, firstNameField);
		add(lastNameLabel);

		lastNameField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, lastNameField, 3, SpringLayout.EAST, lastNameLabel);
		layout.putConstraint(SpringLayout.NORTH, lastNameField, 0, SpringLayout.NORTH, firstNameField);
		add(lastNameField);

		// ------------------- //
		// ----- Address ----- //
		// ------------------- //
		addressField = new JTextField(58);
		layout.putConstraint(SpringLayout.WEST, addressField, 5, SpringLayout.EAST, firstNameLabel);
		layout.putConstraint(SpringLayout.NORTH, addressField, 5, SpringLayout.SOUTH, firstNameField);
		add(addressField);

		JLabel addressLabel = new JLabel("Address: ");
		layout.putConstraint(SpringLayout.EAST, addressLabel, -5, SpringLayout.WEST, addressField);
		layout.putConstraint(SpringLayout.NORTH, addressLabel, 5, SpringLayout.NORTH, addressField);
		add(addressLabel);

		// ---------------- //
		// ----- City ----- //
		// ---------------- //
		cityField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, cityField, 5, SpringLayout.EAST, firstNameLabel);
		layout.putConstraint(SpringLayout.NORTH, cityField, 5, SpringLayout.SOUTH, addressField);
		add(cityField);

		JLabel cityLabel = new JLabel("City: ");
		layout.putConstraint(SpringLayout.EAST, cityLabel, -5, SpringLayout.WEST, cityField);
		layout.putConstraint(SpringLayout.NORTH, cityLabel, 5, SpringLayout.NORTH, cityField);
		add(cityLabel);

		// ----------------- //
		// ----- State ----- //
		// ----------------- //
		JLabel stateLabel = new JLabel("State: ");
		layout.putConstraint(SpringLayout.WEST, stateLabel, 11, SpringLayout.EAST, cityField);
		layout.putConstraint(SpringLayout.NORTH, stateLabel, 5, SpringLayout.NORTH, cityField);
		add(stateLabel);

		stateField = new JTextField(2);
		layout.putConstraint(SpringLayout.WEST, stateField, 5, SpringLayout.EAST, stateLabel);
		layout.putConstraint(SpringLayout.NORTH, stateField, 5, SpringLayout.SOUTH, addressField);
		add(stateField);

		// -------------------- //
		// ----- Zip Code ----- //
		// -------------------- //
		JLabel zipCodeLabel = new JLabel("Zip Code: ");
		layout.putConstraint(SpringLayout.WEST, zipCodeLabel, 11, SpringLayout.EAST, stateField);
		layout.putConstraint(SpringLayout.NORTH, zipCodeLabel, 5, SpringLayout.NORTH, stateField);
		add(zipCodeLabel);

		zipCodeField = new JTextField(5);
		layout.putConstraint(SpringLayout.WEST, zipCodeField, 5, SpringLayout.EAST, zipCodeLabel);
		layout.putConstraint(SpringLayout.NORTH, zipCodeField, 5, SpringLayout.SOUTH, addressField);
		add(zipCodeField);

		// ------------------------ //
		// ----- Phone Number ----- //
		// ------------------------ //
		phoneField = new JTextField(10);
		layout.putConstraint(SpringLayout.WEST, phoneField, 5, SpringLayout.EAST, firstNameLabel);
		layout.putConstraint(SpringLayout.NORTH, phoneField, 5, SpringLayout.SOUTH, cityField);
		add(phoneField);

		JLabel phoneLabel = new JLabel("Phone #: ");
		layout.putConstraint(SpringLayout.EAST, phoneLabel, -5, SpringLayout.WEST, addressField);
		layout.putConstraint(SpringLayout.NORTH, phoneLabel, 5, SpringLayout.NORTH, phoneField);
		add(phoneLabel);

		setMinimumSize(new Dimension(300, 200));
		setVisible(true);
	}

	public void setCustomer(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String accountID,
			String memebershipID) {
		firstNameField.setText(firstName);
		lastNameField.setText(lastName);
		addressField.setText(address);
		cityField.setText(city);
		stateField.setText(state);
		zipCodeField.setText(zip);
		phoneField.setText(phoneNumber);
	}

	public void clearFields() {
		setCustomer("", "", "", "", "", "", "", "", "");
	}

	public boolean fieldsAreEmpty() {
		return firstNameField.getText().isEmpty() && lastNameField.getText().isEmpty() && addressField.getText().isEmpty() && cityField.getText().isEmpty()
				&& stateField.getText().isEmpty() && zipCodeField.getText().isEmpty() && phoneField.getText().isEmpty();
	}
}
