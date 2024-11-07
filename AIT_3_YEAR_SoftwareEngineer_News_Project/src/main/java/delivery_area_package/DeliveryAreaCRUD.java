package delivery_area_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class DeliveryAreaCRUD {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess deliveryAreaAccess;

	// Constructor
	public DeliveryAreaCRUD() throws Exception {

		deliveryAreaAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createDeliveryArea(DeliveryArea dA) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryAreaAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.delivery_area VALUES (default, ?, ?)");

			preparedStatement.setString(1, dA.getName());
			preparedStatement.setInt(2, dA.getPostcode());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ----------------------------- Read ----------------------------- //
	public ResultSet readAllDeliveryArea() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryAreaAccess.getConnection();

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.delivery_area");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;

		}

		return resultSet;
	}

	// ---------------------------- Update ------------------------- //
	public boolean updateDeliveryAreaById(int id, DeliveryArea updateDa) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryAreaAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.delivery_area SET name=?, postcode=? WHERE id=?");

			preparedStatement.setString(1, updateDa.getName());
			preparedStatement.setInt(2, updateDa.getPostcode());
			preparedStatement.setInt(3, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// ---------------------------- Delete -------------------------- //
	public boolean deleteDeliveryAreaById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryAreaAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.delivery_area");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.delivery_area WHERE id = ?");
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
