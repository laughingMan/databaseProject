package common;

import javax.swing.JComboBox;

public class ActionComboBox extends JComboBox<String> {
	private static final long serialVersionUID = 1L;
	private final static String[] actionDropDownOptions = { "Rent Movie", "Create Customer", "Edit Customer", "Remove Customer", "Add Movie", "Edit Movie",
			"Remove Movie", "Customer Statistics", "Movie Statistics" };

	public ActionComboBox() {
		super(actionDropDownOptions);
		setMaximumRowCount(actionDropDownOptions.length);
	}
}
