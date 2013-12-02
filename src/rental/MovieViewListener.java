package rental;

import interfaces.IItemChooserListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class MovieViewListener implements IItemChooserListener {

	@Override
	public void okPressed() {
		
	}

	@Override
	public List<String> addItems(List<String> selectedValuesList, JList<String> destList) {
		ListModel<String> model = destList.getModel();
		ArrayList<String> arrayList = new ArrayList<String>();
		for(int i = 0; i < model.getSize(); i++){
			arrayList.add(model.getElementAt(i));
		}
		for (String string : selectedValuesList) {
			arrayList.add(string);
		}
		return new ArrayList<String>(arrayList);
	}

	@Override
	public List<String> removeItems(List<String> selectedValuesList, JList<String> destList) {
		ListModel<String> model = destList.getModel();
		Vector<String> vector = new Vector<String>();
		for(int i = 0; i < model.getSize(); i++){
			vector.add(model.getElementAt(i));
		}
		for (String string : selectedValuesList) {
			vector.remove(string);
		}
		return new ArrayList<String>(vector);
	}

	@Override
	public void setSelectedItem(String selectedItem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listSelectionChanged(List<String> sourceSelectedValues, List<String> destinationSelectedValues) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelPressed() {

	}

}
