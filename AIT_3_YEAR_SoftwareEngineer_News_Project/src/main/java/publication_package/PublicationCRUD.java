package publication_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class PublicationCRUD {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess publicationAccess;

	// Constructor
	public PublicationCRUD() throws Exception {

		publicationAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createPublication(Publication p) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = publicationAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.publications VALUES (default, ?, ?, ?, ?)");

			preparedStatement.setString(1, p.getName());
			preparedStatement.setString(2, p.getDate());
			preparedStatement.setInt(3, p.getStock());
			preparedStatement.setDouble(4, p.getPrice());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ------------------------------ Read ----------------------------- //
	public ResultSet readAllPublication() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = publicationAccess.getConnection();

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.publications");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;

		}

		return resultSet;
	}

	// --------------------------- Update ------------------------ //
	public boolean updatePublicationById(int id, Publication updateP) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = publicationAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.publications SET name=?, date=?, stock=?, price=? WHERE id=?");

			preparedStatement.setString(1, updateP.getName());
			preparedStatement.setString(2, updateP.getDate());
			preparedStatement.setInt(3, updateP.getStock());
			preparedStatement.setDouble(4, updateP.getPrice());

			preparedStatement.setInt(5, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// ---------------------------- Delete ----------------------------- //
	public boolean deletePublicationById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = publicationAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.publications");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.publications WHERE id = ?");
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
