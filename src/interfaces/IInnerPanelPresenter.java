package interfaces;

import javax.swing.JPanel;

public interface IInnerPanelPresenter {

	JPanel getView();

	void addViewListener(HomeScreenViewListener homeScreenViewListener);

}
