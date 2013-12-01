package homeView;

import interfaces.IInnerPanelPresenter;
import interfaces.ISelectActionViewListener;
import movie.AddMovePresenter;
import movie.EditMovePresenter;
import movie.RentMoviePresenter;
import movie.ReturnMoviePresenter;

import common.RemoveItemPresenter;

import customer.AddCustomerPresenter;
import customer.EditCustomerPresenter;

public class MainViewController {
	private final MainView view;
	private IInnerPanelPresenter innerPanelPresenter;

	public MainViewController() {
		this.view = new MainView();
		view.setViewListener(new ISelectActionViewListener() {
			@Override
			public void setViewListener(ISelectActionViewListener viewListener) {

			}

			@Override
			public void setSelectedType(String selectedItem) {
				if (selectedItem.equals("Create User")) {
					innerPanelPresenter = new AddCustomerPresenter();
				} else if (selectedItem.equals("Edit User")) {
					innerPanelPresenter = new EditCustomerPresenter();
				} else if (selectedItem.equals("Remove User")) {
					innerPanelPresenter = new RemoveItemPresenter();
				} else if (selectedItem.equals("Add Movie")) {
					innerPanelPresenter = new AddMovePresenter();
				} else if (selectedItem.equals("Edit Movie")) {
					innerPanelPresenter = new EditMovePresenter();
				} else if (selectedItem.equals("Remove Movie")) {
					innerPanelPresenter = new RemoveItemPresenter();
				} else if (selectedItem.equals("Rent Movie")) {
					innerPanelPresenter = new RentMoviePresenter(view);
				} else if (selectedItem.equals("Return Movie")) {
					innerPanelPresenter = new ReturnMoviePresenter(view);
				} else {
					innerPanelPresenter = new RentMoviePresenter(view);
				}
				refreshView();
			}
		});

		innerPanelPresenter = new RentMoviePresenter(view);
		refreshView();
	}

	private void refreshView() {
		view.setInnerPanel(innerPanelPresenter.getView());
	}

	public MainView getView() {
		return view;
	}

}
