package daily_summary_report_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import for_all_entities_package.EntitiesMySQLAccess;

public class DailySummaryReportCRUD {

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private EntitiesMySQLAccess dailySummaryReportAccess;

	// Constructor
	public DailySummaryReportCRUD() throws Exception {

		dailySummaryReportAccess = new EntitiesMySQLAccess();

	}

	// -------------------------------- Create --------------------------------- //
	public boolean createDailySummaryReport(DailySummaryReport dS) {

		boolean insertSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = dailySummaryReportAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("INSERT INTO Software_Project_NewsCompany.daily_summary_report VALUES (default, ?, ?, ?, ?)");

			preparedStatement.setString(1, dS.getDate());
			preparedStatement.setInt(2, dS.getCustomerId());
			preparedStatement.setInt(3, dS.getPublicationId());
			preparedStatement.setInt(4, dS.getDeliveryDocketId());

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSuccessful = false;

		}

		return insertSuccessful;
	}

	// ----------------------------- Read ------------------------------ //
	public ResultSet readAllDailySummaryReport() {

		try {
			// Get the connection from MySQLAccess
			Connection connection = dailySummaryReportAccess.getConnection();

			String query = """
					SELECT dsr.summary_date,
					       cp.customer_name,
					       cp.postcode,
					       p.publication_name,
					       p.stock_amount,
					       dd.track_number
					FROM Software_Project_NewsCompany.daily_summary_report dsr
					JOIN Software_Project_NewsCompany.customer_profile cp ON dsr.customer_id = cp.id
					JOIN Software_Project_NewsCompany.publication p ON dsr.publication_id = p.id
					JOIN Software_Project_NewsCompany.delivery_docket dd ON dsr.delivery_docket_id = dd.id
					""";

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
	public ResultSet readDailySummaryReportById(int id) {

		try {
			// Get the connection from MySQLAccess
			Connection connection = dailySummaryReportAccess.getConnection();

			String query = """
					SELECT dsr.summary_date,
					       cp.customer_name,
					       cp.postcode,
					       p.publication_name,
					       p.stock_amount,
					       dd.track_number
					FROM Software_Project_NewsCompany.daily_summary_report dsr
					JOIN Software_Project_NewsCompany.customer_profile cp ON dsr.customer_id = cp.id
					JOIN Software_Project_NewsCompany.publication p ON dsr.publication_id = p.id
					JOIN Software_Project_NewsCompany.delivery_docket dd ON dsr.delivery_docket_id = dd.id
					WHERE dsr.id = ?
					""";

			// Create prepared statement
			preparedStatement = connection.prepareStatement(query);

			// Set the daily_summary_report ID parameter
			preparedStatement.setInt(1, id);

			// Execute the query
			resultSet = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}

		return resultSet;
	}

	// ---------------------------- Update --------------------------- //
	public boolean updateDailySummaryReportById(int id, DailySummaryReport updateDs) {

		boolean updateSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = dailySummaryReportAccess.getConnection();

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connection.prepareStatement("UPDATE Software_Project_NewsCompany.daily_summary_report SET summary_date=?, customer_id=?, publication_id=?, delivery_docket_id=? WHERE id=?");

			preparedStatement.setString(1, updateDs.getDate());
			preparedStatement.setInt(2, updateDs.getCustomerId());
			preparedStatement.setInt(3, updateDs.getPublicationId());
			preparedStatement.setInt(4, updateDs.getDeliveryDocketId());

			preparedStatement.setInt(5, id);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSuccessful = false;

		}

		return updateSuccessful;
	}

	// ---------------------------- Delete ----------------------------- //
	public boolean deleteDailySummaryReportById(int id) {

		boolean deleteSuccessful = true;

		try {
			// Get the connection from MySQLAccess
			Connection connection = dailySummaryReportAccess.getConnection();

			if (id == -99) {
				// Delete all entries in the table
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.daily_summary_report");
			} else {
				// Delete a particular customer
				preparedStatement = connection.prepareStatement("DELETE FROM Software_Project_NewsCompany.daily_summary_report WHERE id = ?");
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
