package homeView;

import interfaces.HomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.ISelectActionViewListener;
import movie.AddMoviePresenter;
import movie.EditMoviePresenter;
import movie.RemoveMoviePresenter;
import rental.RentMoviePresenter;
import rental.ReturnMoviePresenter;
import stats.StatsViewPresenter;
import customer.AddCustomerPresenter;
import customer.EditCustomerPresenter;
import customer.RemoveCustomerPresenter;

public class MainViewController {
	private final MainView view;
	private IInnerPanelPresenter innerPanelPresenter;

	public MainViewController() {
		this.view = new MainView();
		view.setViewListener(new ISelectActionViewListener() {

			@Override
			public void setSelectedType(String selectedItem) {
				if (selectedItem.equals("Create Customer")) {
					innerPanelPresenter = new AddCustomerPresenter();
				} else if (selectedItem.equals("Edit Customer")) {
					innerPanelPresenter = new EditCustomerPresenter();
				} else if (selectedItem.equals("Remove Customer")) {
					innerPanelPresenter = new RemoveCustomerPresenter();
				} else if (selectedItem.equals("Add Movie")) {
					innerPanelPresenter = new AddMoviePresenter();
				} else if (selectedItem.equals("Edit Movie")) {
					innerPanelPresenter = new EditMoviePresenter();
				} else if (selectedItem.equals("Remove Movie")) {
					innerPanelPresenter = new RemoveMoviePresenter();
				} else if (selectedItem.equals("Return Movie")) {
					innerPanelPresenter = new ReturnMoviePresenter();
				} else if (selectedItem.equals("Statics")) {
					innerPanelPresenter = new StatsViewPresenter();
				} else {
					innerPanelPresenter = new RentMoviePresenter();
				}

				addViewListener();
				refreshView();
			}
		});

		innerPanelPresenter = new RentMoviePresenter();
		addViewListener();
		refreshView();
	}

	private void addViewListener() {
		innerPanelPresenter.addViewListener(new HomeScreenViewListener() {
			@Override
			public void returnToHome() {
				innerPanelPresenter = new RentMoviePresenter();
				addViewListener();
				view.setComboBox(0);
				refreshView();
			}

			@Override
			public void resetInnerPanelView() {
				refreshView();
			}
		});
	}

	private void refreshView() {
		view.setInnerPanel(innerPanelPresenter.getView());
	}

	public MainView getView() {
		return view;
	}

}
