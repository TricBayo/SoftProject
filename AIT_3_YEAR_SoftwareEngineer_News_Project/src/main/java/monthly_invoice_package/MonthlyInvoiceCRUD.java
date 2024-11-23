package monthly_invoice_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import for_all_entities_package.EntitiesMySQLAccess;

public class MonthlyInvoiceCRUD {

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess monthlyInvoiceAccess;

	// Constructor
	public MonthlyInvoiceCRUD() throws Exception {

		monthlyInvoiceAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createMonthlyInvoice(MonthlyInvoice m) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = monthlyInvoiceAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.monthly_invoice VALUES (default, ?, ?, ?)");

			preparedStatement.setString(1, m.getAmountToPay());
			preparedStatement.setString(2, m.getPaymentDate());
			preparedStatement.setInt(3, m.getCustomerId());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ------------------------------ Read by ID ----------------------------- //
	public ResultSet readMonthlyInvoiceById(int id) {

		try {
			// Get the connection from MySQLAccess
			Connection connection = monthlyInvoiceAccess.getConnection();

			String query = """
					SELECT cp.customer_name,
					       cp.postcode,
					       mi.payment_date,
					       mi.amount_to_pay
					FROM Software_Project_NewsCompany.monthly_invoice mi
					JOIN Software_Project_NewsCompany.customer_profile cp
					ON mi.customer_id = cp.id
					WHERE mi.id = ?;
					""";

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

	// ------------------------------ Read All----------------------------- //
	public ResultSet readAllMonthlyInvoice() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = monthlyInvoiceAccess.getConnection();

			String query = """
					SELECT cp.customer_name,
					       cp.postcode,
					       mi.payment_date,
						   mi.amount_to_pay
					FROM Software_Project_NewsCompany.monthly_invoice mi JOIN Software_Project_NewsCompany.customer_profile cp ON mi.customer_id = cp.id """;

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

	// --------------------------- Update ------------------------ //
	public boolean updateMonthlyInvoiceById(int id, MonthlyInvoice updateM) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = monthlyInvoiceAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.monthly_invoice SET customer_id=?, payment_date=?, amount_to_pay=? WHERE id=?");

			preparedStatement.setInt(1, updateM.getCustomerId());
			preparedStatement.setString(2, updateM.getPaymentDate());
			preparedStatement.setString(3, updateM.getAmountToPay());

			preparedStatement.setInt(4, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// ---------------------------- Delete ----------------------------- //
	public boolean deleteMonthlyInvoiceById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = monthlyInvoiceAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.monthly_invoice");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.monthly_invoice WHERE id = ?");
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
