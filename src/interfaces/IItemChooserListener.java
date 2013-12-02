package interfaces;

import java.util.List;

import common.objects.Item;

public interface IItemChooserListener {

	void okPressed();

	void cancelPressed();

	void addItems(List<String> selectedValuesList);

	void removeItems(List<String> selectedValuesList);

	void setSelectedItem(String selectedItem);

	void listSelectionChanged(List<String> sourceSelectedValues, List<String> destinationSelectedValues);

}