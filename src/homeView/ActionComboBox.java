package homeView;

import interfaces.ISelectActionViewListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class ActionComboBox extends JComboBox<String> {
	private static final long serialVersionUID = 1L;
	private ISelectActionViewListener viewListener;
	private final static String[] actionDropDownOptions = { "Create User", "Edit User", "Remove User", "Add Movie", "Edit Movie", "Remove Movie", "Rent Movie" };

	public ActionComboBox() {
		super(actionDropDownOptions);
		addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// viewListener.affinityChanged();
			}
		});
	}

	public void setViewListener(ISelectActionViewListener viewListener) {
		this.viewListener = viewListener;
	}
}
