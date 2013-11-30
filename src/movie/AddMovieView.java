package movie;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import common.OkCancelView;

public class AddMovieView extends OkCancelView {
	private static final long serialVersionUID = 1L;

	private JTextField titleField;
	private JTextField genreField;
	private JTextField lengthField;
	private JTextField yearField;
	private JTextField formatField;
	private JButton addButton;
	private JButton cancelButton;

	public AddMovieView() {
		layoutPanel();
	}

	private void layoutPanel() {
		JLabel lengthLabel = new JLabel("Length: ");
		lengthField = new JTextField(25);

		// ----------------- //
		// ----- Title ----- //
		// ----------------- //
		setActionTitleText("Add A New Movie");
		JLabel actionTitle = getActionTitle();

		// ---------------------- //
		// ----- Movie Tile ----- //
		// ---------------------- //
		titleField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, titleField, 5, SpringLayout.EAST, lengthLabel);
		layout.putConstraint(SpringLayout.NORTH, titleField, 40, SpringLayout.SOUTH, actionTitle);
		add(titleField);

		JLabel titleLabel = new JLabel("Tile: ");
		layout.putConstraint(SpringLayout.EAST, titleLabel, -5, SpringLayout.WEST, titleField);
		layout.putConstraint(SpringLayout.NORTH, titleLabel, 40, SpringLayout.SOUTH, actionTitle);
		add(titleLabel);

		// ----------------- //
		// ----- Genre ----- //
		// ----------------- //
		genreField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, genreField, 5, SpringLayout.EAST, lengthLabel);
		layout.putConstraint(SpringLayout.NORTH, genreField, 5, SpringLayout.SOUTH, titleField);
		add(genreField);

		JLabel genreLabel = new JLabel("Genre: ");
		layout.putConstraint(SpringLayout.EAST, genreLabel, -5, SpringLayout.WEST, genreField);
		layout.putConstraint(SpringLayout.NORTH, genreLabel, 5, SpringLayout.SOUTH, titleField);
		add(genreLabel);

		// ------------------- //
		// ----- Length ----- //
		// ------------------- //
		layout.putConstraint(SpringLayout.WEST, lengthLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lengthLabel, 5, SpringLayout.SOUTH, genreField);
		add(lengthLabel);

		layout.putConstraint(SpringLayout.WEST, lengthField, 5, SpringLayout.EAST, lengthLabel);
		layout.putConstraint(SpringLayout.NORTH, lengthField, 5, SpringLayout.SOUTH, genreField);
		add(lengthField);

		// ---------------- //
		// ----- Year ----- //
		// ---------------- //
		yearField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, yearField, 5, SpringLayout.EAST, lengthLabel);
		layout.putConstraint(SpringLayout.NORTH, yearField, 5, SpringLayout.SOUTH, lengthField);
		add(yearField);

		JLabel yearLabel = new JLabel("Year: ");
		layout.putConstraint(SpringLayout.EAST, yearLabel, -5, SpringLayout.WEST, yearField);
		layout.putConstraint(SpringLayout.NORTH, yearLabel, 5, SpringLayout.SOUTH, lengthField);
		add(yearLabel);

		// ----------------- //
		// ----- Format ----- //
		// ----------------- //
		JLabel formatLabel = new JLabel("Format: ");
		layout.putConstraint(SpringLayout.WEST, formatLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, formatLabel, 5, SpringLayout.SOUTH, yearField);
		add(formatLabel);

		formatField = new JTextField(2);
		layout.putConstraint(SpringLayout.WEST, formatField, 5, SpringLayout.EAST, formatLabel);
		layout.putConstraint(SpringLayout.NORTH, formatField, 5, SpringLayout.SOUTH, yearField);
		add(formatField);

		// ------------------- //
		// ----- Buttons ----- //
		// ------------------- //
		setOkButtonLabel("Add");
		setCancelButtonLabel("Cancel");

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
