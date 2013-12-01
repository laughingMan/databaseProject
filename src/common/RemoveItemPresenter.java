package common;

import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import customer.CustomerModel;

public class RemoveItemPresenter implements IInnerPanelPresenter {

	private final RemoveItemView view;
	private final CustomerModel model;
	private static final String REMOVE_ITEM_CONFIRMATION_LABEL = "Are you sure you would like to delete this item?";
	private static final String REMOVE_ITEM_CONFIRMATION_TITLE = "Remove Item";
	private static final String DELETE_ITEM_CONFIRMATION_LABEL = "Item has been deleted";
	private static final String DELETE_ITEM_CONFIRMATION_TITLE = "Item Deleted";

	public RemoveItemPresenter() {
		view = new RemoveItemView();
		model = new CustomerModel();

		view.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				int dialogResult = JOptionPane.showConfirmDialog(view, REMOVE_ITEM_CONFIRMATION_LABEL, REMOVE_ITEM_CONFIRMATION_TITLE,
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(view, DELETE_ITEM_CONFIRMATION_LABEL, DELETE_ITEM_CONFIRMATION_TITLE, JOptionPane.OK_OPTION);
				}
			}

			@Override
			public void cancelButtonPressed() {

			}
		});
	}

	@Override
	public JPanel getView() {
		return view;
	}

}
