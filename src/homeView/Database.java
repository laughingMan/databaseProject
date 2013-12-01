package homeView;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Database extends JFrame {
	private static final long serialVersionUID = 1L;

	public Database() {
		super("Movie Store");
		MainViewController mainViewController = new MainViewController();

		JFrame.setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(830, 600));
		setLocationRelativeTo(null);
		add(mainViewController.getView());

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
