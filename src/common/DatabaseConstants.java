package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.objects.Customer;
import common.objects.Item;
import common.objects.Movie;

public class DatabaseConstants {

	protected Connection connection;
	protected Statement statement;
	protected ResultSet resultSet;

	public void makeDatabaseConnection() {
		String url = "jdbc:mysql://localhost:3306/movie_store";
		String user = "root";
		String password = "root";

		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT VERSION()");

			if (resultSet.next()) {
				System.out.println("Connected");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void addCustomer(String firstName, String lastName, String phoneNumber, String address, String city, String state, String zipCode)
			throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection.prepareStatement("INSERT INTO movie_store.account (balance) VALUES (0.0);").execute();
		dbConstants.connection.prepareStatement(" SET @account_var := (SELECT LAST_INSERT_ID()); ").execute();
		dbConstants.connection.prepareStatement(
				"INSERT INTO movie_store.address (address2, city, state, zip_code) VALUES (\"" + address + "\", \"" + city + "\", \"" + state + "\", \""
						+ zipCode + "\");").execute();
		dbConstants.connection.prepareStatement("SET @address_var := (SELECT LAST_INSERT_ID());").execute();
		dbConstants.connection.prepareStatement(
				"INSERT INTO movie_store.customer (account_id, address_id, phone, first_name, last_name) VALUES (@account_var, @address_var, \"" + phoneNumber
						+ "\", \"" + firstName + "\", \"" + lastName + "\");").execute();
	}

	public static List<Item> getAllCustomers() throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		ResultSet results = dbConstants.connection.prepareStatement(
				"SELECT first_name, last_name, address_id, phone, customer_id, account_id FROM movie_store.customer;").executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Customer customer = new Customer(results.getString(1), results.getString(2), results.getInt(3), results.getString(4), results.getInt(5),
					results.getInt(6));
			userArrayList.add(customer);
		}
		return userArrayList;
	}

	public static UserInfo getUserInfo(String firstName, String lastName) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		PreparedStatement prepareStatement = dbConstants.connection
				.prepareStatement("SELECT first_name, last_name, phone, address2, city, state, zip_code, balance, movie_store.customer.customer_id, movie_store.customer.address_id, movie_store.customer.account_id FROM movie_store.customer, movie_store.address, movie_store.account WHERE (movie_store.customer.first_name = ? AND movie_store.customer.last_name = ?) AND (movie_store.address.address_id = movie_store.customer.customer_id) AND(movie_store.account.account_id = movie_store.customer.account_id);");
		prepareStatement.setString(1, firstName);
		prepareStatement.setString(2, lastName);
		ResultSet results = prepareStatement.executeQuery();
		results.next();
		return new UserInfo(results.getString(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6),
				results.getString(7), results.getDouble(8), results.getInt(9), results.getInt(10), results.getInt(11));
	}

	public static void updateUserInfo(UserInfo userInfo) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection.prepareStatement(
				"UPDATE movie_store.customer SET first_name=\"" + userInfo.getFirstName() + "\", last_name=\"" + userInfo.getLastName() + "\", phone=\""
						+ userInfo.getPhoneNumber() + "\" WHERE movie_store.customer.customer_id = \"" + userInfo.getCustomerId() + "\";").executeUpdate();
		dbConstants.connection.prepareStatement(
				"UPDATE movie_store.address SET address2=\"" + userInfo.getAddress() + "\", city=\"" + userInfo.getCity() + "\", state=\""
						+ userInfo.getState() + "\", zip_code=\"" + userInfo.getZipCode() + "\" WHERE movie_store.address.address_id = \""
						+ userInfo.getAddressId() + "\";").executeUpdate();
	}

	public static void deleteUser(int customerId) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		PreparedStatement prepareStatement = dbConstants.connection
				.prepareStatement("DELETE FROM movie_store.customer WHERE movie_store.customer.customer_id = ?;");
		prepareStatement.setInt(1, customerId);
		prepareStatement.executeUpdate();

	}

	public static void addMovie(int length, String title, int year, String format, int inventory) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection.prepareStatement(
				"INSERT INTO movie_store.movie (length, title, year, format) VALUES (\"" + length + "\", \"" + title + "\", \"" + year + "\", \"" + format
						+ "\"); ").executeUpdate();
		dbConstants.connection.prepareStatement("SET @movie_var := (SELECT LAST_INSERT_ID());").executeUpdate();
		dbConstants.connection.prepareStatement(" INSERT INTO movie_store.inventory (movie_id, total_inventory) VALUES (@movie_var, \"" + inventory + "\");")
				.executeUpdate();
	}

	public static List<Item> getAllMovies() throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT title, length, year, format, movie_store.movie.movie_id FROM movie_store.movie, movie_store.inventory WHERE movie_store.movie.movie_id = movie_store.inventory.movie_id AND movie_store.inventory.total_inventory != 0;")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Movie movie = new Movie(results.getString(1), results.getInt(2), results.getInt(3), results.getString(4), results.getInt(5));
			userArrayList.add(movie);
		}
		return userArrayList;
	}

	public static Movie getMovieInfo(String title, String format) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		ResultSet results = dbConstants.connection.prepareStatement(
				"SELECT title, length, year, format, movie_id FROM movie_store.movie WHERE movie_store.movie.title = \"" + title
						+ "\" AND movie_store.movie.format = \"" + format + "\";").executeQuery();
		results.next();
		return new Movie(results.getString(1), results.getInt(2), results.getInt(3), results.getString(4), results.getInt(5));
	}

	public static void updateMovie(String title, int length, int year, String format, int movieId) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection.prepareStatement(
				"UPDATE movie_store.movie SET length=\"" + length + "\", title=\"" + title + "\", year=\"" + year + "\", format=\"" + format
						+ "\" WHERE movie_store.movie.movie_id = \"" + movieId + "\";").executeUpdate();
	}

	public static void deleteMovie(String title, String format) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection
				.prepareStatement(
						"UPDATE movie_store.inventory SET movie_store.inventory.total_inventory=0 WHERE movie_store.inventory.movie_id = (SELECT movie_store.movie.movie_id FROM movie_store.movie WHERE movie_store.movie.title = \""
								+ title + "\" AND movie_store.movie.format = \"" + format + "\");").executeUpdate();
	}

	public static List<Item> getPossibleRentalsForUser(int customerId) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection
				.prepareStatement(
						"CREATE TEMPORARY TABLE IF NOT EXISTS moviesRented AS (SELECT title, format, movie_id FROM movie_store.movie AS x NATURAL JOIN (SELECT movie_id FROM (SELECT * FROM movie_store.rental WHERE movie_store.rental.return_date IS NOT NULL) AS x NATURAL JOIN (SELECT movie_store.customer.account_id FROM movie_store.customer WHERE movie_store.customer.account_id = \""
								+ customerId + "\") AS y) AS y);").execute();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT title, length, year, format, movie_id FROM movie_store.movie WHERE movie_store.movie.movie_id NOT IN (SELECT movie_id FROM moviesRented);")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Movie movie = new Movie(results.getString(1), results.getInt(2), results.getInt(3), results.getString(4), results.getInt(5));
			userArrayList.add(movie);
		}
		dbConstants.connection.prepareStatement("DROP TABLE IF EXISTS moviesRented;").execute();
		return userArrayList;
	}

	public static void addRental(int movieId, int accountId) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection.prepareStatement(
				"INSERT INTO movie_store.rental(rental_type, movie_id, account_id, rental_date) VALUES (1, \"" + movieId + "\", \"" + accountId + "\", \""
						+ new Date().getTime() + "\");").execute();
		dbConstants.connection.prepareStatement(
				"UPDATE movie_store.inventory SET movie_store.inventory.total_inventory = movie_store.inventory.total_inventory - 1 WHERE movie_store.inventory.movie_id = \""
						+ movieId + "\";").execute();
	}

	public static List<Item> getAllCustomersForReturns() throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT first_name, last_name, address_id, phone, customer_id, account_id  FROM movie_store.customer WHERE movie_store.customer.account_id = (SELECT movie_store.rental.account_id FROM movie_store.rental WHERE movie_store.rental.return_date IS NOT NULL);")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Customer customer = new Customer(results.getString(1), results.getString(2), results.getInt(3), results.getString(4), results.getInt(5),
					results.getInt(6));
			userArrayList.add(customer);
		}
		return userArrayList;
	}

	public static List<Item> getCurrentRentalsForUser(int customerId) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection
				.prepareStatement(
						"CREATE TEMPORARY TABLE IF NOT EXISTS moviesRented AS (SELECT title, format, movie_id FROM movie_store.movie AS x NATURAL JOIN (SELECT movie_id FROM (SELECT * FROM movie_store.rental WHERE movie_store.rental.return_date IS NOT NULL) AS x NATURAL JOIN (SELECT movie_store.customer.account_id FROM movie_store.customer WHERE movie_store.customer.account_id = \\"
								+ customerId + "\") AS y) AS y);").execute();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT title, length, year, format, movie_id FROM movie_store.movie WHERE movie_store.movie.movie_id NOT IN (SELECT movie_id FROM moviesRented);")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Movie movie = new Movie(results.getString(1), results.getInt(2), results.getInt(3), results.getString(4), results.getInt(5));
			userArrayList.add(movie);
		}
		dbConstants.connection.prepareStatement("DROP TABLE IF EXISTS moviesRented;").execute();
		return userArrayList;
	}

	public static void returnRental(int accountId, int movieId) throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection.prepareStatement(
				"UPDATE movie_store.rental SET movie_store.rental.return_date = \\" + new Date().getTime() + "\" WHERE movie_store.rental.account_id = \\"
						+ accountId + "\";").execute();
		dbConstants.connection.prepareStatement(
				"UPDATE movie_store.inventory SET movie_store.inventory.total_inventory = movie_store.inventory.total_inventory + 1 WHERE movie_store.inventory.movie_id = \\"
						+ movieId + "\";").execute();
	}

	public static List<Item> getTopRentedMovies() throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT title, length, year, format, movie_store.movie.movie_id, count(title) as cnt FROM (movie_store.movie AS x NATURAL JOIN (SELECT movie_store.rental.movie_id FROM movie_store.rental) AS y) GROUP BY title ORDER BY cnt DESC LIMIT 5;")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Movie movie = new Movie(results.getString(1), results.getInt(2), results.getInt(3), results.getString(4), results.getInt(5));
			userArrayList.add(movie);
		}
		return userArrayList;
	}

	public static List<Item> getLeastRentedMovies() throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT title, length, year, format, movie_store.movie.movie_id, count(title) as cnt FROM (movie_store.movie AS x NATURAL JOIN (SELECT movie_store.rental.movie_id FROM movie_store.rental) AS y) GROUP BY title ORDER BY cnt ASC LIMIT 5;")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Movie movie = new Movie(results.getString(1), results.getInt(2), results.getInt(3), results.getString(4), results.getInt(5));
			userArrayList.add(movie);
		}
		return userArrayList;
	}

	public static List<Item> getTopMoviesCheckedOut() throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT title, length, year, format, movie_store.movie.movie_id, count(title) as cnt FROM (movie_store.movie AS x NATURAL JOIN (SELECT movie_store.rental.movie_id FROM movie_store.rental WHERE movie_store.rental.return_date IS NOT NULL) AS y) GROUP BY title ORDER BY cnt DESC LIMIT 5;")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Movie movie = new Movie(results.getString(1), results.getInt(2), results.getInt(3), results.getString(4), results.getInt(5));
			userArrayList.add(movie);
		}
		return userArrayList;
	}

	public static List<Item> getOutstandingRentalsForAllUsers() throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection
				.prepareStatement(
						"CREATE TEMPORARY TABLE IF NOT EXISTS moviesRented AS (SELECT title, account_id FROM movie_store.movie AS x NATURAL JOIN (SELECT movie_store.rental.account_id, movie_store.rental.movie_id FROM movie_store.rental WHERE movie_store.rental.return_date IS NOT NULL) AS y);")
				.execute();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT title, length, year, format, movie_id FROM FROM movie_store.customer, moviesRented WHERE movie_store.customer.account_id = moviesRented.account_id;")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Movie movie = new Movie(results.getString(1), results.getInt(2), results.getInt(3), results.getString(4), results.getInt(5));
			userArrayList.add(movie);
		}
		dbConstants.connection.prepareStatement("DROP TABLE IF EXISTS moviesRented;").execute();
		return userArrayList;
	}

	public static List<Item> getTopRenters() throws SQLException {
		DatabaseConstants dbConstants = new DatabaseConstants();
		dbConstants.makeDatabaseConnection();
		dbConstants.connection
				.prepareStatement(
						"CREATE TEMPORARY TABLE IF NOT EXISTS moviesRented AS (SELECT title, account_id FROM movie_store.movie AS x NATURAL JOIN (SELECT movie_store.rental.account_id, movie_store.rental.movie_id FROM movie_store.rental) AS y);")
				.execute();
		ResultSet results = dbConstants.connection
				.prepareStatement(
						"SELECT first_name, last_name, address_id, phone, customer_id, account_id, count(first_name) as cnt FROM movie_store.customer, moviesRented WHERE movie_store.customer.account_id = moviesRented.account_id GROUP BY first_name ORDER BY cnt DESC LIMIT 5;")
				.executeQuery();
		ArrayList<Item> userArrayList = new ArrayList<Item>();
		while (results.next()) {
			Customer customer = new Customer(results.getString(1), results.getString(2), results.getInt(3), results.getString(4), results.getInt(5),
					results.getInt(6));
			userArrayList.add(customer);
		}
		dbConstants.connection.prepareStatement("DROP TABLE IF EXISTS moviesRented;").execute();
		return userArrayList;
	}
}
