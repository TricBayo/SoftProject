package delivery_person_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class DeliveryPersonCRUD {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess deliveryPersonAccess;

	// Constructor
	public DeliveryPersonCRUD() throws Exception {

		deliveryPersonAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createDeliveryPerson(DeliveryPerson dP) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryPersonAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.delivery_person VALUES (default, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, dP.getName());
			preparedStatement.setString(2, dP.getPhoneNumber());
			preparedStatement.setString(3, dP.getAreaId());
			preparedStatement.setString(4, dP.getPostcode());
			preparedStatement.setString(5, dP.getPassword());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ------------------------------- Read ------------------------------- //
	public ResultSet readAllDeliveryPerson() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryPersonAccess.getConnection();

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.delivery_person");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;

		}

		return resultSet;
	}

	// ----------------------------- Update ----------------------------- //
	public boolean updateDeliveryPersonById(int id, DeliveryPerson updateDp) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryPersonAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.delivery_person SET dperson_name=?, phone_number=?, area_id=?, postcode=?, password=? WHERE id=?");

			preparedStatement.setString(1, updateDp.getName());
			preparedStatement.setString(2, updateDp.getPhoneNumber());
			preparedStatement.setString(3, updateDp.getAreaId());
			preparedStatement.setString(4, updateDp.getPostcode());
			preparedStatement.setString(5, updateDp.getPassword());
			preparedStatement.setInt(6, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// -------------------------------- Delete --------------------------------- //
	public boolean deleteDeliveryPersonById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = deliveryPersonAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.delivery_person");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.delivery_person WHERE id = ?");
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
