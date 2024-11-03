package monthly_invoice_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import for_all_entities_package.EntitiesMySQLAccess;

public class MonthlyInvoiceCRUD {

	private Statement statement = null;
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
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.monthly_invoice VALUES (default, ?, ?, ?, ?)");

			preparedStatement.setString(1, m.getName());
			preparedStatement.setString(2, m.getPostcode());
			preparedStatement.setString(3, m.getPaymentDate());
			preparedStatement.setDouble(4, m.getAmountToPay());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ------------------------------ Read ----------------------------- //
	public ResultSet readAllMonthlyInvoice() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = monthlyInvoiceAccess.getConnection();

			// Create a statement to issue the SQL query to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM Software_Project_NewsCompany.monthly_invoice");

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
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.monthly_invoice SET name=?, postcode=?, paymentDate=?, amountToPay=? WHERE id=?");

			preparedStatement.setString(1, updateM.getName());
			preparedStatement.setString(2, updateM.getPostcode());
			preparedStatement.setString(3, updateM.getPaymentDate());
			preparedStatement.setDouble(4, updateM.getAmountToPay());

			preparedStatement.setInt(5, id);

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
