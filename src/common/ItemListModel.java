package common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import common.objects.Item;

public class ItemListModel extends AbstractListModel<Item> {
	private static final long serialVersionUID = 1L;
	private List<Item> items;

	public ItemListModel() {
		this(new ArrayList<Item>());
	}

	public ItemListModel(List<Item> items) {
		setData(items);
	}

	public void setData(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public Item getElementAt(int index) {
		return items.get(index);
	}
}