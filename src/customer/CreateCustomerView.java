package customer;

import interfaces.IButtonsPressed;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class CreateCustomerView extends JPanel implements IButtonsPressed {
	private static final long serialVersionUID = 1L;

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField zipCodeField;

	private JButton addButton;

	private JButton cancelButton;

	public CreateCustomerView() {
		layoutPanel();
	}

	private void layoutPanel() {
		setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// ----------------- //
		// ----- Title ----- //
		// ----------------- //
		JLabel tileLabel = new JLabel("Add A New Customer");
		tileLabel.setFont(new Font(this.getFont().getFamily(), Font.PLAIN, 30));
		layout.putConstraint(SpringLayout.WEST, tileLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tileLabel, 20, SpringLayout.NORTH, this);
		add(tileLabel);

		// ---------------------- //
		// ----- First Name ----- //
		// ---------------------- //
		JLabel firstNameLabel = new JLabel("First Name: ");
		layout.putConstraint(SpringLayout.WEST, firstNameLabel, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, firstNameLabel, 40, SpringLayout.SOUTH, tileLabel);
		add(firstNameLabel);

		firstNameField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, firstNameField, 5, SpringLayout.EAST, firstNameLabel);
		layout.putConstraint(SpringLayout.NORTH, firstNameField, 40, SpringLayout.SOUTH, tileLabel);
		add(firstNameField);

		// --------------------- //
		// ----- Last Name ----- //
		// --------------------- //
		JLabel lastNameLabel = new JLabel("Last Name: ");
		layout.putConstraint(SpringLayout.WEST, lastNameLabel, 5, SpringLayout.EAST, firstNameField);
		layout.putConstraint(SpringLayout.NORTH, lastNameLabel, 40, SpringLayout.SOUTH, tileLabel);
		add(lastNameLabel);

		lastNameField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, lastNameField, 3, SpringLayout.EAST, lastNameLabel);
		layout.putConstraint(SpringLayout.NORTH, lastNameField, 40, SpringLayout.SOUTH, tileLabel);
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
		layout.putConstraint(SpringLayout.NORTH, addressLabel, 5, SpringLayout.SOUTH, firstNameField);
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
		layout.putConstraint(SpringLayout.NORTH, cityLabel, 5, SpringLayout.SOUTH, addressField);
		add(cityLabel);

		// ----------------- //
		// ----- State ----- //
		// ----------------- //
		JLabel stateLabel = new JLabel("State: ");
		layout.putConstraint(SpringLayout.WEST, stateLabel, 11, SpringLayout.EAST, cityField);
		layout.putConstraint(SpringLayout.NORTH, stateLabel, 5, SpringLayout.SOUTH, addressField);
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
		layout.putConstraint(SpringLayout.NORTH, zipCodeLabel, 5, SpringLayout.SOUTH, addressField);
		add(zipCodeLabel);

		zipCodeField = new JTextField(5);
		layout.putConstraint(SpringLayout.WEST, zipCodeField, 5, SpringLayout.EAST, zipCodeLabel);
		layout.putConstraint(SpringLayout.NORTH, zipCodeField, 5, SpringLayout.SOUTH, addressField);
		add(zipCodeField);

		// ------------------- //
		// ----- Buttons ----- //
		// ------------------- //
		addButton = new JButton("Add");
		layout.putConstraint(SpringLayout.EAST, addButton, 5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, addButton, 5, SpringLayout.SOUTH, this);
		add(addButton);

		cancelButton = new JButton("Cancel");
		layout.putConstraint(SpringLayout.EAST, cancelButton, 5, SpringLayout.WEST, addButton);
		layout.putConstraint(SpringLayout.SOUTH, cancelButton, 5, SpringLayout.SOUTH, this);
		add(cancelButton);

		setMinimumSize(new Dimension(300, 200));
		setVisible(true);
	}

	@Override
	public void addOkButtonPressedListener(ActionListener listener) {
		addButton.addActionListener(listener);
	}

	@Override
	public void addCancelButtonPressedListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}
}
