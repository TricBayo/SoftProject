package new_agent_package;

import java.sql.Connection;
import java.sql.PreparedStatement;

import for_all_entities_package.EntitiesMySQLAccess;

public class NewsagentCredentialsCRUD {

	private PreparedStatement preparedStatement = null;
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
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.newsagent_profile VALUES (default, ?, ?)");

			preparedStatement.setString(1, nC.getName());
			preparedStatement.setString(2, nC.getPassword());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ----------------------------- Update ----------------------------- //
	public boolean updateNewsagentCredentialsId(int id, NewsagentCredentials updateNc) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = newsagentAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.newsagent_profile SET name=?, password=? WHERE id=?");

			preparedStatement.setString(1, updateNc.getName());
			preparedStatement.setString(2, updateNc.getPassword());
			preparedStatement.setInt(3, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

}
