package order_book_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import for_all_entities_package.EntitiesMySQLAccess;

public class OrderBookCRUD {

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

			// SQL query to insert a new order book entry
			String sql = """
					INSERT INTO Software_Project_NewsCompany.order_book (customer_id, publication_id)
					VALUES (?, ?)
					""";

			// Prepare the statement
			preparedStatement = connection.prepareStatement(sql);

			// Set the customer_id and publication_id from the OrderBook object
			preparedStatement.setInt(1, oB.getCustomerId());
			preparedStatement.setInt(2, oB.getPublicationId());

			// Execute the insert query
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

			String query = """
										SELECT cp.customer_name,
					       cp.postcode,
					       p.publication_name
					FROM Software_Project_NewsCompany.order_book ob
					JOIN Software_Project_NewsCompany.customer_profile cp ON ob.customer_id = cp.id
					JOIN Software_Project_NewsCompany.publication p ON ob.publication_id = p.id;""";

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
	public ResultSet readOrderBookById(int id) {

		try {
			// Get the connection from MySQLAccess
			Connection connection = orderBookAccess.getConnection();

			String query = """
						   SELECT cp.customer_name,
					       cp.postcode,
					       p.publication_name
					FROM Software_Project_NewsCompany.order_book ob
					JOIN Software_Project_NewsCompany.customer_profile cp ON ob.customer_id = cp.id
					JOIN Software_Project_NewsCompany.publication p ON ob.publication_id = p.id
					WHERE ob.id = ?;
										""";

			// Create prepared statement
			preparedStatement = connection.prepareStatement(query);

			// Set the order_book ID parameter
			preparedStatement.setInt(1, id);

			// Execute the query
			resultSet = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}

		return resultSet;
	}

	// ---------------------------- Update ------------------------- //
	public boolean updateOrderBookById(int id, OrderBook updateOb) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = orderBookAccess.getConnection();

			String query = """
					UPDATE Software_Project_NewsCompany.order_book
					SET customer_id = ?, publication_id = ?
					WHERE id = ?
					""";

			// Create prepared statement
			preparedStatement = connection.prepareStatement(query);

			// Set parameters for track_number, order_date, and id
			preparedStatement.setInt(1, updateOb.getCustomerId());
			preparedStatement.setInt(2, updateOb.getPublicationId());

			preparedStatement.setInt(3, id);

			// Execute the update
			int rowsAffected = preparedStatement.executeUpdate();

			// Check if the update was successful
			if (rowsAffected == 0) {
				updateSuccessful = false;
			}

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
				// Delete Order Book by Id
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

}
