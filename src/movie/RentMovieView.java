package movie;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import common.OkCancelView;

public class RentMovieView extends OkCancelView {
	private static final long serialVersionUID = 1L;
	private ActionListener actionListener;
	private JLabel nameLabel;
	private JLabel phoneNumberLabel;
	private JLabel addressLabel;
	private JLabel customerIDLabel;
	private JLabel accountLabel;
	private JLabel membershipLabel;

	public void setCustomer(String name, String address, String phoneNumber, String customerID, String accountID, String membershipID) {

		this.nameLabel = new JLabel(name);
		add(this.nameLabel);

		this.addressLabel = new JLabel(address);
		add(this.addressLabel);

		this.phoneNumberLabel = new JLabel(phoneNumber);
		add(this.phoneNumberLabel);

		this.customerIDLabel = new JLabel(customerID);
		add(this.customerIDLabel);

		this.accountLabel = new JLabel(accountID);
		add(this.accountLabel);

		this.membershipLabel = new JLabel(membershipID);
		add(this.membershipLabel);
	}
}
