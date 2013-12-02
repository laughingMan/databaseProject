package movie;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import common.OkCancelView;

public class AddMovieView extends OkCancelView {
	private static final long serialVersionUID = 1L;

	protected final JTextField titleField;
	protected final JTextField lengthField;
	protected final JTextField yearField;
	protected final JTextField formatField;

	public AddMovieView() {
		JLabel lengthLabel = new JLabel("Length: ");
		lengthField = new JTextField(25);

		// ---------------------- //
		// ----- Movie Tile ----- //
		// ---------------------- //
		JLabel actionTitle = getActionTitle();
		titleField = new JTextField(25);
		layout.putConstraint(SpringLayout.WEST, titleField, 5, SpringLayout.EAST, lengthLabel);
		layout.putConstraint(SpringLayout.NORTH, titleField, 40, SpringLayout.SOUTH, actionTitle);
		add(titleField);

		JLabel titleLabel = new JLabel("Tile: ");
		layout.putConstraint(SpringLayout.EAST, titleLabel, -5, SpringLayout.WEST, titleField);
		layout.putConstraint(SpringLayout.NORTH, titleLabel, 40, SpringLayout.SOUTH, actionTitle);
		add(titleLabel);

		// ------------------- //
		// ----- Length ----- //
		// ------------------- //
		layout.putConstraint(SpringLayout.WEST, lengthLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lengthLabel, 5, SpringLayout.SOUTH, titleField);
		add(lengthLabel);

		layout.putConstraint(SpringLayout.WEST, lengthField, 5, SpringLayout.EAST, lengthLabel);
		layout.putConstraint(SpringLayout.NORTH, lengthField, 5, SpringLayout.SOUTH, titleField);
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

		formatField = new JTextField(20);
		layout.putConstraint(SpringLayout.WEST, formatField, 5, SpringLayout.EAST, formatLabel);
		layout.putConstraint(SpringLayout.NORTH, formatField, 5, SpringLayout.SOUTH, yearField);
		add(formatField);

		setMinimumSize(new Dimension(300, 200));
		setVisible(true);
	}

	public void setMovie(String title, String length, String year, String format) {
		titleField.setText(title);
		lengthField.setText(length);
		yearField.setText(year);
		formatField.setText(format);
	}

	public void clearFields() {
		setMovie("", "", "", "");
	}

	public boolean fieldsAreClear() {
		return titleField.getText().isEmpty() && lengthField.getText().isEmpty() && yearField.getText().isEmpty() && formatField.getText().isEmpty();
	}
}
