package stats;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import common.objects.Customer;
import common.objects.Item;
import common.objects.Movie;

public class StatsViewPresenter implements IInnerPanelPresenter {

	private IHomeScreenViewListener homeScreenViewListener;
	private final StatsView view;

	public StatsViewPresenter(int viewIndex) {
		this.view = new StatsView();
		view.hideCancelButton(true);
		view.setOkButtonLabel("Home");
		view.setViewListener(new IOkCancelButtonsListener() {
			@Override
			public void okButtonPressed() {
				homeScreenViewListener.returnToHome();
			}

			@Override
			public void cancelButtonPressed() {
			}
		});

		if (viewIndex == 0) {
			view.setActionTitleText("Customer Statistics");
			view.setTableOneLabel("Top Renters");
			view.setTableTwoLabel("Top Outstanding Rentals");
			view.setTableThreeLabel("Top Repeat Offenders");

			view.setTableOne(getTopRenters());
			view.setTableTwo(getTopOutstandingRentals());
			view.setTableThree(getTopRepeatOffenders());

		} else {
			view.setActionTitleText("Movie Statistics");
			view.setTableOneLabel("Top Rentals");
			view.setTableTwoLabel("Least Rented");
			view.setTableThreeLabel("Top Unreturned");

			view.setTableOne(getTopRentals());
			view.setTableTwo(getLeastRented());
			view.setTableThree(getTopUnreturned());
		}
	}

	private List<Item> getTopRenters() {
		// TODO Auto-generated method stub
		return createCustomers();
	}

	private List<Item> getTopOutstandingRentals() {
		// TODO Auto-generated method stub
		return createCustomers();
	}

	private List<Item> getTopRepeatOffenders() {
		// TODO Auto-generated method stub
		return createCustomers();
	}

	private List<Item> getTopUnreturned() {
		// TODO Auto-generated method stub
		return createMovies();

	}

	private List<Item> getLeastRented() {
		// TODO Auto-generated method stub
		return createMovies();
	}

	private List<Item> getTopRentals() {
		// TODO Auto-generated method stub
		return createMovies();
	}

	// TODO: REMOVE
	private List<Item> createMovies() {
		List<Item> movies = new ArrayList<Item>();

		Movie movie1 = new Movie("Ghostbusters", 123, 1980, "vhs", 1);
		Movie movie2 = new Movie("Ghostbusters II", 234, 1982, "dvd", 1);
		Movie movie3 = new Movie("Ghostbusters III", 345, 1983, "bluray", 1);
		Movie movie4 = new Movie("Ghostbusters IV", 3435, 1984, "bluray", 1);
		Movie movie5 = new Movie("Ghostbusters V", 3475, 1985, "bluray", 1);

		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);
		movies.add(movie4);
		movies.add(movie5);

		return movies;
	}

	// TODO: REMOVE
	private List<Item> createCustomers() {
		List<Item> customers = new ArrayList<Item>();

		Customer customer1 = new Customer("isaac", "hatton", 231, "6187918662", 222, 345);
		Customer customer2 = new Customer("joe", "blow", 234, "2134537890", 111, 786);
		Customer customer3 = new Customer("john", "smith", 2545, "3730138745", 344, 432);
		Customer customer4 = new Customer("steven", "tars", 2565, "4729384677", 534, 4322);
		Customer customer5 = new Customer("josh", "none", 2255, "4563456123", 3444, 4332);

		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
		customers.add(customer4);
		customers.add(customer5);

		return customers;
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void addViewListener(IHomeScreenViewListener homeScreenViewListener) {
		this.homeScreenViewListener = homeScreenViewListener;
	}
}
