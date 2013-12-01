package homeView;

import interfaces.IInnerPanelPresenter;
import interfaces.ISelectActionViewListener;
import movie.RentMoviePresenter;

import common.RemoveItemPresenter;

import customer.CustomerPresenter;

public class MainViewController {
	private final MainView view;
	private IInnerPanelPresenter innerPanelPresenter;

	public MainViewController(final DatabaseModel model, final MainView view) {

		this.view = view;
		view.setViewListener(new ISelectActionViewListener() {

			@Override
			public void setViewListener(ISelectActionViewListener viewListener) {

			}

			@Override
			public void setSelectedType(String selectedItem) {
				if (selectedItem.equals("Create User")) {
					innerPanelPresenter = new CustomerPresenter();
				} else if (selectedItem.equals("Edit User")) {
					innerPanelPresenter = new CustomerPresenter();
				} else if (selectedItem.equals("Remove User")) {
					innerPanelPresenter = new RemoveItemPresenter();
				} else if (selectedItem.equals("Add Movie")) {
					innerPanelPresenter = new CustomerPresenter();
				} else if (selectedItem.equals("Edit Movie")) {
					innerPanelPresenter = new CustomerPresenter();
				} else if (selectedItem.equals("Remove Movie")) {
					innerPanelPresenter = new RemoveItemPresenter();
				} else if (selectedItem.equals("Rent Movie")) {
					innerPanelPresenter = new RentMoviePresenter(view);
				} else if (selectedItem.equals("Return Movie")) {
					innerPanelPresenter = new RentMoviePresenter(view);
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

}
