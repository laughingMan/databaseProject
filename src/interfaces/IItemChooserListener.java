package interfaces;

import java.util.List;

import javax.swing.JList;

import common.objects.Item;

public interface IItemChooserListener {

	void okPressed();

	void cancelPressed();

	List<String> addItems(List<String> selectedValuesList, JList<String> destinationList);

	List<String> removeItems(List<String> selectedValuesList, JList<String> destinationList);

	void setSelectedItem(String selectedItem);

	void listSelectionChanged(List<String> sourceSelectedValues, List<String> destinationSelectedValues);

}