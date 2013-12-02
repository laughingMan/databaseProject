package stats;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import common.ItemTableModel;
import common.OkCancelView;
import common.objects.Item;

public class StatsView extends OkCancelView {
	private static final long serialVersionUID = 1L;
	private final JLabel tableOneLabel;
	private final JTable tableOne;
	private final JLabel tableTwoLabel;
	private final JTable tableTwo;
	private final JLabel tableThreeLabel;
	private final JTable tableThree;
	private final ItemTableModel tableOneModel = new ItemTableModel();
	private final ItemTableModel tableTwoModel = new ItemTableModel();
	private final ItemTableModel tableThreeModel = new ItemTableModel();

	public StatsView() {
		tableOne = new JTable();
		tableOne.setPreferredSize(new Dimension(500, 80));
		tableOne.setRowSelectionAllowed(false);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tableOne, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, tableOne, 60, SpringLayout.SOUTH, getActionTitle());
		add(tableOne);

		tableOneLabel = new JLabel();
		JLabel actionTitle = getActionTitle();
		layout.putConstraint(SpringLayout.WEST, tableOneLabel, 0, SpringLayout.WEST, tableOne);
		layout.putConstraint(SpringLayout.SOUTH, tableOneLabel, -5, SpringLayout.NORTH, tableOne);
		add(tableOneLabel);

		tableTwoLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, tableTwoLabel, 0, SpringLayout.WEST, tableOneLabel);
		layout.putConstraint(SpringLayout.NORTH, tableTwoLabel, 20, SpringLayout.SOUTH, tableOne);
		add(tableTwoLabel);

		tableTwo = new JTable();
		tableTwo.setPreferredSize(new Dimension(500, 80));
		tableTwo.setRowSelectionAllowed(false);
		layout.putConstraint(SpringLayout.WEST, tableTwo, 0, SpringLayout.WEST, tableOneLabel);
		layout.putConstraint(SpringLayout.NORTH, tableTwo, 5, SpringLayout.SOUTH, tableTwoLabel);
		add(tableTwo);

		tableThreeLabel = new JLabel();
		layout.putConstraint(SpringLayout.WEST, tableThreeLabel, 0, SpringLayout.WEST, tableOneLabel);
		layout.putConstraint(SpringLayout.NORTH, tableThreeLabel, 20, SpringLayout.SOUTH, tableTwo);
		add(tableThreeLabel);

		tableThree = new JTable();
		tableThree.setPreferredSize(new Dimension(500, 80));
		tableThree.setRowSelectionAllowed(false);
		layout.putConstraint(SpringLayout.WEST, tableThree, 0, SpringLayout.WEST, tableOneLabel);
		layout.putConstraint(SpringLayout.NORTH, tableThree, 5, SpringLayout.SOUTH, tableThreeLabel);
		add(tableThree);
	}

	public void setTableOneLabel(String label) {
		tableOneLabel.setText(label);
	}

	public void setTableTwoLabel(String label) {
		tableTwoLabel.setText(label);
	}

	public void setTableThreeLabel(String label) {
		tableThreeLabel.setText(label);
	}

	public void setTableOne(List<Item> topRentals) {
		tableOneModel.setItems(topRentals);
		tableOne.setModel(tableTwoModel);
	}

	public void setTableTwo(List<Item> leastRented) {
		tableTwoModel.setItems(leastRented);
		tableTwo.setModel(tableTwoModel);
	}

	public void setTableThree(List<Item> topUnreturned) {
		tableThreeModel.setItems(topUnreturned);
		tableThree.setModel(tableTwoModel);
	}
}
