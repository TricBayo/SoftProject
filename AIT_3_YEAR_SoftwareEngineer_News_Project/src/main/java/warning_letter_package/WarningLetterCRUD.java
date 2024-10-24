package warning_letter_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class WarningLetterCRUD {

	private Statement statement = null;
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
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.warning_letter VALUES (default, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, wL.getName());
			preparedStatement.setString(2, wL.getPostcode());
			preparedStatement.setDouble(3, wL.getAmountInDebt());
			preparedStatement.setInt(4, wL.getPaymentStatus());

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

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.warning_letter");

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
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.warning_letter SET name=?, postcode=?, amountInDebt=?, paymentStatus=? WHERE id=?");

			preparedStatement.setString(1, updateWl.getName());
			preparedStatement.setString(2, updateWl.getPostcode());
			preparedStatement.setDouble(3, updateWl.getAmountInDebt());
			preparedStatement.setInt(4, updateWl.getPaymentStatus());
			preparedStatement.setInt(5, id);

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
