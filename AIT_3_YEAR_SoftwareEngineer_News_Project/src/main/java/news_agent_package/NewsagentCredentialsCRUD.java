package news_agent_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class NewsagentCredentialsCRUD {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess newsagentAccess;

	// Constructor
	public NewsagentCredentialsCRUD() throws Exception {

		newsagentAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createNewsagentCredentials(NewsagentCredentials nC) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = newsagentAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.newsagent_credentials VALUES (default, ?, ?)");

			preparedStatement.setString(1, nC.getNewsagentName());
			preparedStatement.setString(2, nC.getPassword());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ------------------------------- Read ------------------------------- //
	public ResultSet readNewsagentCredentials() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = newsagentAccess.getConnection();

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.newsagent_credentials");

		} catch (Exception e) {

			e.printStackTrace();
			resultSet = null;

		}

		return resultSet;
	}

	// ----------------------------- Update ----------------------------- //
	public boolean updateNewsagentCredentialsId(int id, NewsagentCredentials updateNc) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = newsagentAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.newsagent_credentials SET newsagent_name=?, password=? WHERE id=?");

			preparedStatement.setString(1, updateNc.getNewsagentName());
			preparedStatement.setString(2, updateNc.getPassword());
			preparedStatement.setInt(3, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// -------------------------------- Delete --------------------------------- //
	public boolean deleteNewsagentById(int id) {

		boolean deleteSuccessful = true;

		try {

			// Get the connection from MySQLAccess
			Connection connection = newsagentAccess.getConnection();

			if (id == -99) {

				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.newsagent_credentials");

			} else {

				// Delete newsagent by ID
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.newsagent_credentials WHERE id = ?");
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
