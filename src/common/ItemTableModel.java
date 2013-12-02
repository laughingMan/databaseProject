package common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import common.objects.Item;

public class ItemTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private Object[][] data;
	private final Object[] columnNames = { "ID", "Name" };
	private List<Item> items;

	public ItemTableModel() {
		this(new ArrayList<Item>());
	}

	public ItemTableModel(List<Item> items) {
		this.items = items;
		setItems(items);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	@Override
	public String getColumnName(int col) {
		return (String) columnNames[col];
	}

	public void setItems(List<Item> items) {
		this.items = items;
		data = new Object[items.size()][];
		int index = 0;
		for (Item item : items) {
			ArrayList<Object> test = new ArrayList<Object>();
			test.add(item.getID());
			test.add(item.getName());
			Object[] array = test.toArray();
			data[index++] = array;
		}
		fireTableDataChanged();
	}

	public List<Item> getItems() {
		return items;
	}

	public List<Item> getItemForIndices(int[] indexes) {
		List<Item> itemsList = new ArrayList<>();
		for (int selectedRowIndex : indexes) {
			Item item = items.get(selectedRowIndex);
			itemsList.add(item);
		}
		return itemsList;
	}
}