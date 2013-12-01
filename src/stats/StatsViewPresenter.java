package stats;

import interfaces.IInnerPanelPresenter;

import javax.swing.JPanel;

public class StatsViewPresenter implements IInnerPanelPresenter {

	private final StatsView view;

	public StatsViewPresenter() {
		view = new StatsView();

	}

	@Override
	public JPanel getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
