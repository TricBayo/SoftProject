package warning_letter_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import for_all_entities_package.EntitiesMySQLAccess;

public class WarningLetterCRUD {

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess warningLetterAccess;

	// Constructor
	public WarningLetterCRUD() throws Exception {

		warningLetterAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createWarningLetter(WarningLetter wL) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = warningLetterAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.warning_letter VALUES (default, ?, ?)");

			preparedStatement.setInt(1, wL.getCustomerId());
			preparedStatement.setInt(2, wL.getDeliveryAreaId());

			preparedStatement.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ------------------------------ Read ----------------------------- //
	public ResultSet readAllWarningLetter() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = warningLetterAccess.getConnection();

			String query = """
					SELECT cp.payment_status,
					       cp.customer_name,
					       cp.postcode,
						   da.area_name
					FROM Software_Project_NewsCompany.warning_letter wl JOIN Software_Project_NewsCompany.customer_profile cp ON wl.customer_id = cp.id JOIN Software_Project_NewsCompany.delivery_area da ON wl.darea_id = da.id""";

			// Create prepared statement
			preparedStatement = connection.prepareStatement(query);

			// Execute the query
			resultSet = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}

		return resultSet;
	}

	// ------------------------------ Read by ID ----------------------------- //
	public ResultSet readWarningLetterById(int id) {

		try {

			// Get the connection from MySQLAccess
			Connection connection = warningLetterAccess.getConnection();

			String query = """
					SELECT cp.payment_status,
						   cp.customer_name,
					       cp.postcode,
						   da.area_name
					FROM Software_Project_NewsCompany.warning_letter wl JOIN Software_Project_NewsCompany.customer_profile cp ON wl.customer_id = cp.id JOIN Software_Project_NewsCompany.delivery_area da ON wl.darea_id = da.id WHERE wl.id = ? """;

			// Create prepared statement
			preparedStatement = connection.prepareStatement(query);

			// Set the delivery_docket ID parameter
			preparedStatement.setInt(1, id);

			// Execute the query
			resultSet = preparedStatement.executeQuery();

		} catch (Exception e) {

			e.printStackTrace();
			resultSet = null;
		}

		return resultSet;
	}

	// ---------------------------- Update -------------------------- //
	public boolean updateWarningLetterById(int id, WarningLetter updateWl) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = warningLetterAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.warning_letter SET customer_id=?, darea_id=? WHERE id=?");

			preparedStatement.setInt(1, updateWl.getCustomerId());
			preparedStatement.setInt(2, updateWl.getDeliveryAreaId());

			preparedStatement.setInt(3, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// ---------------------------- Delete ----------------------------- //
	public boolean deleteWarningLetterById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = warningLetterAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.warning_letter");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.warning_letter WHERE id = ?");
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
