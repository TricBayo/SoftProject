package order_book_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class OrderBookCRUD {

	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess orderBookAccess;

	// Constructor
	public OrderBookCRUD() throws Exception {

		orderBookAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createOrderBook(OrderBook oB) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = orderBookAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.order_book VALUES (default, ?, ?, ?)");

			preparedStatement.setString(1, oB.getName());
			preparedStatement.setString(2, oB.getPostcode());
			preparedStatement.setString(3, oB.getPublicationName());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ------------------------------ Read ----------------------------- //
	public ResultSet readAllOrderBook() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = orderBookAccess.getConnection();

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.order_book");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;

		}

		return resultSet;
	}

	// ---------------------------- Update -------------------------- //
	public boolean updateOrderBookById(int id, OrderBook updateOb) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = orderBookAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.order_book SET name=?, postcode=?, publicationName=? WHERE id=?");

			preparedStatement.setString(1, updateOb.getName());
			preparedStatement.setString(2, updateOb.getPostcode());
			preparedStatement.setString(3, updateOb.getPublicationName());

			preparedStatement.setInt(4, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// ---------------------------- Delete ----------------------------- //
	public boolean deleteOrderBookById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = orderBookAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.order_book");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.order_book WHERE id = ?");
				preparedStatement.setInt(1, id);
			}

			preparedStatement.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			deleteSuccessful = false;

		}

		return deleteSuccessful;
	}

	// ------------------------------ Read by ID ----------------------------- //
	public ResultSet readOrderBookById(int id) {

    	try {
        // Get the connection from MySQLAccess
        	Connection connection = orderBookAccess.getConnection();

        // Create prepared statement to issue SQL query to the database for a specific id
        	preparedStatement = connection.prepareStatement("SELECT FROM Software_Project_NewsCompany.order_book WHERE id = ?");
        	preparedStatement.setInt(1, id);

        // Execute the query
        	resultSet = preparedStatement.executeQuery();

    	} catch (Exception e) {
        e.printStackTrace();
        resultSet = null;
    }

    return resultSet;
}


}
