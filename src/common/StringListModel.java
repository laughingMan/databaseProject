package common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import common.objects.Item;

public class StringListModel extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;
	private List<String> items;

	public StringListModel() {
		this(new ArrayList<String>());
	}

	public StringListModel(List<String> items) {
		setData(items);
	}

	public void setData(List<String> items) {
		this.items = items;
	}

	public List<String> getItems() {
		return items;
	}

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public String getElementAt(int index) {
		return items.get(index);
	}
}