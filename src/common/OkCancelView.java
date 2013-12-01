package common;

import interfaces.IOkCancelButtonsListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class OkCancelView extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JLabel tile;
	private final JButton okButton;
	private final JButton cancelButton;
	protected SpringLayout layout;
	protected IOkCancelButtonsListener viewListener;

	public OkCancelView() {
		setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		layout = new SpringLayout();
		setLayout(layout);

		// title
		tile = new JLabel();
		tile.setFont(new Font(this.getFont().getFamily(), Font.PLAIN, 30));
		layout.putConstraint(SpringLayout.WEST, tile, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tile, 10, SpringLayout.NORTH, this);
		add(tile);

		// add button
		okButton = new JButton("Select");
		layout.putConstraint(SpringLayout.EAST, okButton, 5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, okButton, 5, SpringLayout.SOUTH, this);
		add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewListener.okButtonPressed();
			}
		});

		// cancel button
		cancelButton = new JButton();
		layout.putConstraint(SpringLayout.EAST, cancelButton, 5, SpringLayout.WEST, okButton);
		layout.putConstraint(SpringLayout.SOUTH, cancelButton, 5, SpringLayout.SOUTH, this);
		add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewListener.cancelButtonPressed();
			}
		});
	}

	public void setActionTitleText(String text) {
		tile.setText(text);
	}

	public JLabel getActionTitle() {
		return tile;
	}

	public void setOkButtonLabel(String label) {
		okButton.setText(label);
	}

	public void setCancelButtonLabel(String label) {
		cancelButton.setText(label);
	}

	public void addOkButtonPressedListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}

	public void addCancelButtonPressedListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

	public void enableOkButton(boolean isEnabled) {
		okButton.setEnabled(isEnabled);
	}

	public void setViewListener(IOkCancelButtonsListener viewListener) {
		this.viewListener = viewListener;
	}
}
