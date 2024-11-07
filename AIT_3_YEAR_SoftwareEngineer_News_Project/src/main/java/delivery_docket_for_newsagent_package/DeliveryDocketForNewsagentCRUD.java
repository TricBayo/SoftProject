package delivery_docket_for_newsagent_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class DeliveryDocketForNewsagentCRUD {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess deliveryDocketAccess;

	// Constructor
	public DeliveryDocketForNewsagentCRUD() throws Exception {

		deliveryDocketAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createDeliveryDocket(DeliveryDocket dD) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryDocketAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.delivery_docket VALUES (default, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, dD.getCustomerName());
			preparedStatement.setString(2, dD.getDeliveryPersonName());
			preparedStatement.setString(3, dD.getOrderDate());
			preparedStatement.setString(4, dD.getPostcode());
			preparedStatement.setInt(5, dD.getTrackNumber());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ----------------------------- Read --------------------------- //
	public ResultSet readAllDeliveryDocket() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryDocketAccess.getConnection();

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.delivery_docket");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;

		}

		return resultSet;
	}

	// ---------------------------- Update ------------------------- //
	public boolean updateDeliveryDocketById(int id, DeliveryDocket updateDd) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryDocketAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.delivery_docket SET customerName=?, deliveryPersonName=?, orderDate=?, postcode=?, trackNumber=? WHERE id=?");

			preparedStatement.setString(1, updateDd.getCustomerName());
			preparedStatement.setString(2, updateDd.getDeliveryPersonName());
			preparedStatement.setString(3, updateDd.getOrderDate());
			preparedStatement.setString(4, updateDd.getPostcode());
			preparedStatement.setInt(5, updateDd.getTrackNumber());

			preparedStatement.setInt(6, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// ---------------------------- Delete --------------------------- //
	public boolean deleteDeliveryDocketById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryDocketAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.delivery_docket");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.delivery_docket WHERE id = ?");
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
