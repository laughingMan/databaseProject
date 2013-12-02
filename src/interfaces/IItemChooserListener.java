package interfaces;

import java.util.List;

import common.objects.Item;

public interface IItemChooserListener {

	void okPressed();

	void cancelPressed();

	void addItems(List<Item> selectedValuesList);

	void removeItems(List<Item> selectedValuesList);

	void setSelectedItem(String selectedItem);

	void listSelectionChanged(List<Item> sourceSelectedValues, List<Item> destinationSelectedValues);

}