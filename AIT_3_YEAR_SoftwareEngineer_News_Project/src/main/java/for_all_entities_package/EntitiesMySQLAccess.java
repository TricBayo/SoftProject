package for_all_entities_package;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EntitiesMySQLAccess {

	private Connection connect = null;

	// ---------------------- Constants Database attributes ------------------- //
	final private String host = "localhost:3306";
	final private String user = "root";
	final private String password = "";

	// --------------------------- Database Connection -------------------------- //
	public EntitiesMySQLAccess() throws Exception {

		try {
			// Load MySQL Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Setup the connection with the DB (No password included)
			connect = DriverManager.getConnection("jdbc:mysql://" + host + "/Software_Project_NewsCompany?useSSL=false&serverTimezone=UTC", user, password);

		} catch (Exception e) {

			throw e;

		}
	}

	// Method to get the current connection
	public Connection getConnection() {

		return connect;

	}

	// Method to close the connection
	public void close() throws SQLException {

		if (connect != null && !connect.isClosed()) {
			connect.close();
		}

	}

}
