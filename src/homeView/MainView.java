package homeView;

import interfaces.ISelectActionViewListener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import common.ActionComboBox;

public class MainView extends JPanel {
	private static final long serialVersionUID = 1L;
	private final ActionComboBox actionComboBox = new ActionComboBox();
	private ISelectActionViewListener viewListener;
	private JPanel innerPanel = new JPanel();

	public MainView() {
		layoutPanel();
	}

	private void layoutPanel() {
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		setLayout(new BorderLayout());
		setBackground(this.getBackground().darker());

		Border empty = new EmptyBorder(0, 0, 5, 0);
		actionComboBox.setBorder(empty);

		actionComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				viewListener.setSelectedType((String) actionComboBox.getSelectedItem());
			}
		});

		add(actionComboBox, BorderLayout.NORTH);
		add(innerPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	public void setViewListener(ISelectActionViewListener viewListener) {
		this.viewListener = viewListener;
	}

	public void setInnerPanel(JPanel innerPanelView) {
		remove(innerPanel);
		innerPanel = innerPanelView;
		add(innerPanel);

		revalidate();
		repaint();
	}

	public void setComboBox(int index) {
		actionComboBox.setSelectedIndex(index);

		revalidate();
		repaint();
	}
}
