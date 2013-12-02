package stats;

import interfaces.HomeScreenViewListener;
import interfaces.IInnerPanelPresenter;

import javax.swing.JPanel;

public class StatsViewPresenter implements IInnerPanelPresenter {

	private final StatsView view;
	private HomeScreenViewListener homeScreenViewListener;

	public StatsViewPresenter() {
		view = new StatsView();

		view.setActionTitleText("Statistics");
	}

	@Override
	public JPanel getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addViewListener(HomeScreenViewListener homeScreenViewListener) {
		this.homeScreenViewListener = homeScreenViewListener;
	}

}
