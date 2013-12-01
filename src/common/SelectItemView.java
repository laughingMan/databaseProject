package common;

import interfaces.ITableChooserListener;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectItemView extends OkCancelView {
	private static final long serialVersionUID = 1L;
	private JList<Item> sourceList;
	private final JScrollPane sourceScrollPane;
	private final JTable table;
	private final ItemTableModel itemTableModel;
	private ITableChooserListener viewListener;
	private final JLabel tableLabel;

	public SelectItemView() {
		// Item table
		itemTableModel = new ItemTableModel(new ArrayList<Item>());
		table = new JTable(itemTableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowSelectionAllowed(true);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					viewListener.listSelectionChanged(itemTableModel.getItemForIndices(table.getSelectedRows()));
				}
			}
		});
		table.setSelectionModel(selectionModel);

		// Customer table view
		sourceScrollPane = new JScrollPane(table);
		sourceScrollPane.setPreferredSize(new Dimension(500, 300));
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, sourceScrollPane, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, sourceScrollPane, 50, SpringLayout.SOUTH, getActionTitle());
		add(sourceScrollPane);

		tableLabel = new JLabel();
		tableLabel.setFont(new Font(this.getFont().getFamily(), Font.PLAIN, 20));
		layout.putConstraint(SpringLayout.WEST, tableLabel, 0, SpringLayout.WEST, sourceScrollPane);
		layout.putConstraint(SpringLayout.SOUTH, tableLabel, -5, SpringLayout.NORTH, sourceScrollPane);
		add(tableLabel);

		enableOkButton(false);
		hideCancelButton(true);

		setVisible(true);
	}

	public void setTableLabel(String label) {
		tableLabel.setText(label);
	}

	public void setCustomerList(List<Item> items) {
		itemTableModel.setItems(items);
		table.setModel(itemTableModel);
	}

	public void setViewListener(ITableChooserListener viewListener) {
		this.viewListener = viewListener;
	}
}
