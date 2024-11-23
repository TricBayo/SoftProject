package customer_profile_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class CustomerProfileCRUD {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess customerAccess;

	// Constructor
	public CustomerProfileCRUD() throws Exception {

		customerAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createCustomerDetailsAccount(CustomerProfile c) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = customerAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.customer_profile VALUES (default, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getPostcode());
			preparedStatement.setString(3, c.getPhoneNumber());
			preparedStatement.setString(4, c.getEmail());
			preparedStatement.setInt(5, c.getPaymentStatus());

			preparedStatement.executeUpdate();

		} catch (Exception e) {

			insertSuccessful = false;
			e.printStackTrace();

		}

		return insertSuccessful;
	}

	// ------------------------------ Read ----------------------------- //
	public ResultSet readAllCustomerAccounts() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = customerAccess.getConnection();

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.customer_profile");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;

		}

		return resultSet;
	}

	// ---------------------------- Update -------------------------- //
	public boolean updateCustomerById(int id, CustomerProfile updateC) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = customerAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.customer_profile SET customer_name=?, postcode=?, phone_number=?, email=?, payment_status=? WHERE id=?");

			preparedStatement.setString(1, updateC.getName());
			preparedStatement.setString(2, updateC.getPostcode());
			preparedStatement.setString(3, updateC.getPhoneNumber());
			preparedStatement.setString(4, updateC.getEmail());
			preparedStatement.setInt(5, updateC.getPaymentStatus());
			preparedStatement.setInt(6, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// ---------------------------- Delete ----------------------------- //
	public boolean deleteCustomerById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = customerAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.customer_profile");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.customer_profile WHERE id = ?");
				preparedStatement.setInt(1, id);
			}

			preparedStatement.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			deleteSuccessful = false;

		}

		return deleteSuccessful;
	}

}
