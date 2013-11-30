package homeView;

import interfaces.IInnerPanelPresenter;

import javax.swing.JPanel;

public class HomeViewPresenter implements IInnerPanelPresenter {

	private final HomeViewModel model;
	private final HomeView view;

	public HomeViewPresenter() {
		view = new HomeView();
		model = new HomeViewModel();
	}

	@Override
	public JPanel getView() {
		return null;
	}

}
