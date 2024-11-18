package delivery_docket_for_delivery_person_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class DeliveryDocketForDeliveryPersonRU {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess deliveryDocketAccess;

	// Constructor
	public DeliveryDocketForDeliveryPersonRU() throws Exception {

		deliveryDocketAccess = new EntitiesMySQLAccess();

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

}
