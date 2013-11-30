package homeView;

import interfaces.IInnerPanelModel;
import interfaces.IInnerPanelPresenter;
import interfaces.ISelectActionViewListener;

import javax.swing.JPanel;

import movie.AddMovieView;
import movie.MovieModel;
import movie.RentMovieView;
import customer.CreateCustomerView;
import customer.CustomerModel;
import customer.CustomerPresenter;
import customer.SelectCustomerView;

public class MainController {
	private final MainView view;
	private JPanel innerPanelView;
	private IInnerPanelPresenter innerPanelPresenter;
	private IInnerPanelModel innerPanelModel;

	public MainController(final DatabaseModel model, final MainView view) {

		this.view = view;
		view.setViewListener(new ISelectActionViewListener() {

			@Override
			public void setViewListener(ISelectActionViewListener viewListener) {

			}

			@Override
			public void setSelectedType(String selectedItem) {
				if (selectedItem.equals("Create User")) {
					innerPanelView = new CreateCustomerView();
					innerPanelModel = new CustomerModel();
					innerPanelPresenter = new CustomerPresenter(innerPanelView, innerPanelModel);
				} else if (selectedItem.equals("Edit User")) {
					innerPanelView = new CreateCustomerView();
					innerPanelModel = new CustomerModel();
					innerPanelPresenter = new CustomerPresenter(innerPanelView, innerPanelModel);
				} else if (selectedItem.equals("Remove User")) {
					// innerPanelView = new RemoveItemView();
					// innerPanelModel = new CustomerModel();
					// innerPanelPresenter = new RemoveItemPresenter(innerPanelView, innerPanelModel);
				} else if (selectedItem.equals("Add Movie")) {
					innerPanelView = new AddMovieView();
					innerPanelModel = new MovieModel();
					innerPanelPresenter = new CustomerPresenter(innerPanelView, innerPanelModel);
				} else if (selectedItem.equals("Edit Movie")) {
					// innerPanelView = new AddMovieView();
					// innerPanelModel = new MovieModel();
					// innerPanelPresenter = new CustomerPresenter(innerPanelView, innerPanelModel);
				} else if (selectedItem.equals("Remove Movie")) {
					// innerPanelView = new RemoveItemView();
					// innerPanelModel = new MovieModel();
					// innerPanelPresenter = new RemoveItemPresenter(innerPanelView, innerPanelModel);
				} else if (selectedItem.equals("Rent Movie")) {
					innerPanelModel = new CustomerModel();
					innerPanelView = new SelectCustomerView(innerPanelModel);
					innerPanelPresenter = new RentMoviePresenter(view, innerPanelView, new RentMovieView(), innerPanelModel);
				} else if (selectedItem.equals("Return Movie")) {
					// innerPanelModel = new CustomerModel();
					// innerPanelView = new SelectCustomerView(innerPanelModel);
					// innerPanelPresenter = new RentMoviePresenter(view, innerPanelView, new RentMovieView(),
					// innerPanelModel);
				} else {
					// innerPanelView = new HomeView();
					// innerPanelModel = new HomeViewModel();
					// innerPanelPresenter = new HomeViewPresenter(innerPanelView, innerPanelModel);
				}
				refreshView();
			}
		});

		innerPanelModel = new CustomerModel();
		innerPanelView = new SelectCustomerView(innerPanelModel);
		innerPanelPresenter = new RentMoviePresenter(view, innerPanelView, new RentMovieView(), innerPanelModel);
		refreshView();
	}

	private void refreshView() {
		view.setInnerPanel(innerPanelView);
	}

}
