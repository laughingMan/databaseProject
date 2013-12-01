package common;

import java.util.List;

public interface IItemChooserListener {

	void okPressed();

	void cancelPressed();

	void addItems(List<Item> selectedValuesList);

	void removeItems(List<Item> selectedValuesList);

	void setSelectedItem(String selectedItem);

	void listSelectionChanged(List<Item> sourceSelectedValues, List<Item> destinationSelectedValues);

}