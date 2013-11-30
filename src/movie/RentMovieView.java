package movie;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class RentMovieView extends JPanel {
	private static final long serialVersionUID = 1L;
	private ActionListener actionListener;

	public void addCancelButtonPressedListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

}
