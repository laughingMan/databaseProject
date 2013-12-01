package customer;

import common.Item;

public class Customer implements Item {
	private String firstName;
	private String lastName;
	private int addressID;
	private String phoneNumber;
	private int customerID;
	private int accountID;
	private int memebershipID;

	public Customer(String firstName, String lastName, int addressID, String phoneNumber, int customerID, int accountID, int membershipID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressID = addressID;
		this.phoneNumber = phoneNumber;
		this.customerID = customerID;
		this.accountID = accountID;
		memebershipID = membershipID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getMemebershipID() {
		return memebershipID;
	}

	public void setMemebershipID(int memebershipID) {
		this.memebershipID = memebershipID;
	}

	@Override
	public String getName() {
		return getFirstName() + " " + getLastName();
	}

	@Override
	public String getID() {
		return Integer.toString(getAccountID());
	}
}