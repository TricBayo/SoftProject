package for_all_entities_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EntitiesMySQLAccess {

	// ---------------------- Constants Database attributes ------------------- //
	private final String host = "localhost:3306";
	private final String user = "root";
	private final String password = "";

	// --------------------------- Database Connection -------------------------- //
	public EntitiesMySQLAccess() throws Exception {
		try {
			// Load MySQL Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("MySQL Driver not found. " + e.getMessage());
		}
	}

	// Method to create a new connection
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" + host + "/Software_Project_NewsCompany?useSSL=false&serverTimezone=UTC", user, password);
	}
}
