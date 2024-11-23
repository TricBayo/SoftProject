package daily_summary_report_package;

import java.sql.ResultSet;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class DailySummaryReportCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Display Menu Options for Daily Summary Report
	private void dailySummaryReportFunctionalities() {

		System.out.println();
		System.out.println("======= DAILY SUMMARY REPORT ENTITY MENU =======");
		System.out.println("Please, choose ONE of the following options:   |");
		System.out.println("                                               |");
		System.out.println("1. Create Daily Summary Report                 |");
		System.out.println("2. View All Daily Summary Reports              |");
		System.out.println("3. View Daily Summary Reports By ID            |");
		System.out.println("4. Update a Daily Summary Report by ID         |");
		System.out.println("5. Delete a Daily Summary Report by ID         |");
		System.out.println("99. Return to Main Menu                        |");
		System.out.println("================================================");
		System.out.println();

	}

	// Print the Daily Summary Report Table
	private boolean printDailySummaryReportTable(ResultSet rs) throws Exception {

		System.out.println();
		System.out.println("       ------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                              Table: " + rs.getMetaData().getTableName(1) + " :");
		System.out.println();

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%20s", rs.getMetaData().getColumnName(i));
		}

		System.out.println();

		while (rs.next()) {

			String date = rs.getString("summary_date");
			String customerName = rs.getString("customer_name");
			String postcode = rs.getString("postcode");
			String publicationName = rs.getString("publication_name");
			String stockAmount = rs.getString("stock_amount");
			String trackNumber = rs.getString("track_number");

			System.out.printf("%20s", date);
			System.out.printf("%20s", customerName);
			System.out.printf("%20s", postcode);
			System.out.printf("%20s", publicationName);
			System.out.printf("%20s", stockAmount);
			System.out.printf("%20s", trackNumber);
			System.out.println();

		}

		System.out.println();
		System.out.println("       ------------------------------------------------------------------------------------------------------------------");

		return true;
	}

	@Override
	public void execute() {

		try {
			DailySummaryReportCRUD connect = new DailySummaryReportCRUD();
			String functionNumber;
			boolean keepAppOpen = true;

			while (keepAppOpen) {

				dailySummaryReportFunctionalities();
				functionNumber = scanner.next();

				switch (functionNumber) {
				case "1":
					// Create a New Daily Summary Report
					System.out.print("Enter Date (dd/MM/yyyy): ");
					String date = scanner.next();

					System.out.print("Enter Customer ID: ");
					int customerId = scanner.nextInt();

					System.out.print("Enter Publication ID: ");
					int publicationId = scanner.nextInt();

					System.out.print("Enter Delivery Docket ID: ");
					int deliveryDocketId = scanner.nextInt();

					DailySummaryReport report = new DailySummaryReport(date, customerId, publicationId, deliveryDocketId);
					boolean reportGenerated = connect.createDailySummaryReport(report);

					if (reportGenerated) {
						System.out.println("Daily Summary Report Created.");

					} else {
						System.out.println("ERROR: Report Creation Failed.");

					}
					break;

				case "2":
					// View All Daily Summary Reports
					ResultSet rSet = connect.readAllDailySummaryReport();

					if (rSet == null) {
						System.out.println("No Reports Found");

					} else {

						boolean tablePrinted = printDailySummaryReportTable(rSet);

						if (tablePrinted) {
							rSet.close();

						}
					}
					break;

				case "3":
					// Read Daily Summary Report by ID
					System.out.print("Enter Daily Summary Report ID to Read: ");
					int dailySummaryIdRd = scanner.nextInt();

					ResultSet readResultSet = connect.readDailySummaryReportById(dailySummaryIdRd);

					if (readResultSet != null && readResultSet.next()) {

						System.out.println("----------------------------------");

						System.out.println("Date: " + readResultSet.getString("summary_date"));
						System.out.println("Customer Name: " + readResultSet.getString("customer_name"));
						System.out.println("Postcode: " + readResultSet.getString("postcode"));
						System.out.println("Publication Name: " + readResultSet.getString("publication_name"));
						System.out.println("Stock Amount: " + readResultSet.getString("stock_amount"));
						System.out.println("Delivery Docket Track Number: " + readResultSet.getString("track_number"));

						System.out.println("----------------------------------");

					} else {
						System.out.println("Daily Summary Report with ID " + dailySummaryIdRd + " not found.");

					}

					break;

				case "4":
					// Update a Daily Summary Report by ID
					System.out.print("Enter Daily Summary Report ID to Update: ");
					int idUp = scanner.nextInt();

					System.out.print("Enter Date (dd/MM/yyyy): ");
					String dateUp = scanner.next();

					System.out.print("Enter Customer ID: ");
					int customerIdUp = scanner.nextInt();

					System.out.print("Enter Publication ID: ");
					int publicationIdUp = scanner.nextInt();

					System.out.print("Enter Delivery Docket ID: ");
					int deliveryDocketIdUp = scanner.nextInt();

					DailySummaryReport reportUp = new DailySummaryReport(dateUp, customerIdUp, publicationIdUp, deliveryDocketIdUp);
					boolean reportGeneratedUp = connect.updateDailySummaryReportById(idUp, reportUp);

					if (reportGeneratedUp) {
						System.out.println("Daily Summary Report Updated.");

					} else {
						System.out.println("ERROR: Report Update Failed.");

					}
					break;

				case "5":
					// Delete a Daily Summary Report by ID
					System.out.print("Enter Report ID to Delete or -99 to Return: ");
					String deleteReportId = scanner.next();

					if (deleteReportId.equals("-99")) {
						System.out.println("Returning to Main Menu...");
					} else {
						boolean reportDeleted = connect.deleteDailySummaryReportById(Integer.parseInt(deleteReportId));

						if (reportDeleted) {
							System.out.println("Daily Summary Report Deleted.");
						} else {
							System.out.println("ERROR: Report Not Deleted or Does Not Exist.");
						}
					}
					break;

				case "99":
					// Return to Main Menu
					keepAppOpen = false;
					System.out.println("Returning to Main Menu...");
					break;

				default:
					System.out.println("No Valid Function Selected");
					break;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE: " + e.getMessage());
		}
	}
}
