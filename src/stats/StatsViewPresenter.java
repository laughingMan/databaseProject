package stats;

import interfaces.IHomeScreenViewListener;
import interfaces.IInnerPanelPresenter;
import interfaces.IOkCancelButtonsListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import common.DatabaseConstants;
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
		try {
			return DatabaseConstants.getTopRenters();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Item> getTopOutstandingRentals() {
		try {
			return DatabaseConstants.getOutstandingRentalsForAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Item> getTopRepeatOffenders() {
		try {
			return DatabaseConstants.getTopRenters();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Item> getTopUnreturned() {
		try {
			return DatabaseConstants.getTopMoviesCheckedOut();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Item> getLeastRented() {
		try {
			return DatabaseConstants.getLeastRentedMovies();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Item> getTopRentals() {
		try {
			return DatabaseConstants.getTopRentedMovies();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
