package rental;

import interfaces.HomeScreenViewListener;
import interfaces.IInnerPanelPresenter;

import javax.swing.JPanel;

public class ReturnMoviePresenter implements IInnerPanelPresenter {

	private HomeScreenViewListener homeViewListener;

	public ReturnMoviePresenter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addViewListener(HomeScreenViewListener actionListener) {
		this.homeViewListener = actionListener;
	}

}
