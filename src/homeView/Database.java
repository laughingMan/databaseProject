package homeView;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Database extends JFrame {
	private static final long serialVersionUID = 1L;

	public Database() {
		super("Movie Store");
		MainView mainView = new MainView();
		DatabaseModel model = new DatabaseModel();
		new MainController(model, mainView);

		JFrame.setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(830, 600));
		setLocationRelativeTo(null);
		add(mainView);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		new Database();
	}
}
