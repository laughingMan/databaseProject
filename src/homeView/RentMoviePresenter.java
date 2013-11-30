package homeView;

import interfaces.IInnerPanelModel;
import interfaces.IInnerPanelPresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import movie.RentMovieView;
import customer.SelectCustomerView;

public class RentMoviePresenter implements IInnerPanelPresenter {

	private final SelectCustomerView selectCustomerView;
	private final RentMovieView rentMovieView;
	private final IInnerPanelModel customerModel;
	private final MainView innerPanelView;

	public RentMoviePresenter(MainView innerPanelView, JPanel selectCustomerView, RentMovieView rentMovieView, IInnerPanelModel customerModel) {
		this.innerPanelView = innerPanelView;
		this.selectCustomerView = (SelectCustomerView) selectCustomerView;
		this.rentMovieView = rentMovieView;
		this.customerModel = customerModel;

		this.selectCustomerView.addOkButtonPressedListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToRentMovieView();
			}
		});

		this.rentMovieView.addCancelButtonPressedListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToSelectCustomerView();
			}
		});
	}

	private void switchToRentMovieView() {
		innerPanelView.setInnerPanel(rentMovieView);
	}

	private void switchToSelectCustomerView() {
		innerPanelView.setInnerPanel(selectCustomerView);
	}
}
