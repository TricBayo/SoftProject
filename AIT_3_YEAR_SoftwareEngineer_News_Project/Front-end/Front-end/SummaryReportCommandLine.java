package command;




import java.sql.ResultSet;
import java.util.Scanner;

public class SummaryReportCommandLine {

	// Present Customer with Functionalities
	private static void summaryReportFuctionalities() {

		System.out.println();
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Summary Report");
		System.out.println("2. Read ALL Summary Report Records");
		System.out.println("3. Update ALL Summary Report Records");
		System.out.println("4. Delete Summary Report Record by ID");
		System.out.println("99. Close the NewsAgent Application");
		System.out.println("=============================================");
		System.out.println();

		// The option chosen by user will be read by scanner inside the Main method

	}

	// Print The Contents of the Full Customer Table to News Agent Persona
	private static boolean printSummaryReportTable(ResultSet rs) throws Exception {

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("Table: " + rs.getMetaData().getTableName(1));

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%30s", rs.getMetaData().getColumnName(i));

		}

		System.out.println();
		while (rs.next()) {

			int id = rs.getInt("id");
			String Samount = rs.getString("Stock Amount");
			String Did = rs.getString("Driver id");
			String Pid = rs.getString("Publication id");
			String Rdate = rs.getString("Report Date");
			

			System.out.printf("%30s", id);
			System.out.printf("%30s", Samount);
			System.out.printf("%30s", Did);
			System.out.printf("%30s", Pid);
			System.out.printf("%30s", Rdate);
		
			System.out.println();

		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	// Main
	public static void main(String[] args) {

		// Create the Database Object
		try {

			SummaryReportCRUD connect = new SummaryReportCRUD();

			// Configure System for Running
			Scanner sc = new Scanner(System.in);

			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen == true) {

				// Present list of functionality and get selection
				summaryReportFuctionalities();
				functionNumber = sc.next();

				switch (functionNumber) {

				// ---------------------------------------------------- //
				case "1":

					// Create New Customer Profile

					// Get Customer Details from the User
					System.out.printf("Enter Stock Amount: \n");
					String Samount = sc.next();

					System.out.printf("Enter Driver id: \n");
					String Did = sc.next();

					System.out.printf("Enter Publication id: \n");
					String Pid = sc.next();

					System.out.printf("Enter Report Date: \n");
					String Rdate = sc.next();

					

					// Creating new Customer Object using the attributes above collected from user
					SummaryReport newSummaryReport = new SummaryReport(Samount, Did, Pid, Rdate);

					// Insert Customer Details into the database
					boolean insertResult = connect.createSummaryReportDetailsAccount(newSummaryReport);

					if (insertResult == true) {
						System.out.println("SummaryReport Details Saved");

					} else {
						System.out.println("ERROR: SummaryReport Details NOT Saved");

					}
					break;

				// ---------------------------------------------------- //
				case "2":

					// Read ALL Customer Profiles
					ResultSet rSet = connect.readAllSummaryReportAccounts();

					if (rSet == null) {
						System.out.println("No Records Found");
						break;

					} else {
						boolean tablePrinted = printSummaryReportTable(rSet);

						if (tablePrinted == true)
							rSet.close();

					}
					break;

				// ---------------------------------------------------- //
				case "3":

					// Update Customer Profile by Id

					// Get New Customer Details from User
					System.out.printf("Enter Summary Report Id related to the Existing Summary Report you wish to be Updated: \n");
					int id = sc.nextInt();

					System.out.printf("Enter Stock Amount: \n");
					String newSamount = sc.next();

					System.out.printf("Enter Driver id: \n");
					String newDid = sc.next();

					System.out.printf("Enter Publication id: \n");
					String newPid = sc.next();

					System.out.printf("Enter Report Date: \n");
					String newRdate = sc.next();

					

					// Creating new Customer Object using the attributes above collected from user
					SummaryReport updateSummaryReport = new SummaryReport(newSamount, newDid, newPid, newRdate);

					// Insert Customer Details into the database
					boolean updateResult = connect.updateSummaryReportById(id, updateSummaryReport);

					if (updateResult == true) {
						System.out.println("Summary Report Details Updates");

					} else {
						System.out.println("ERROR: Summary Report Details NOT Updated");

					}
					break;

				// ---------------------------------------------------- //
				case "4":

					// Delete Customer Profile by ID
					System.out.println("Enter Summary Report Id to be deleted or -99 to Clear all Rows");
					String deleteSummaryReport = sc.next();

					boolean summaryReportDeleted = connect.deleteSummaryReportById(Integer.parseInt(deleteSummaryReport));

					if ((summaryReportDeleted == true) && (deleteSummaryReport.equals("-99"))) {
						System.out.println("Summary Report Table Emptied");

					} else if (summaryReportDeleted == true)
						System.out.println("Summary Report Deleted");

					else
						System.out.println("ERROR: Summary Report Details NOT Deleted or Do Not Exist");

					break;

				case "99":

					// Close Application
					keepAppOpen = false;
					System.out.println("Closing the Application");
					break;

				default:

					System.out.println("No Valid Function Selected");
					break;

				}

			}

			// ---------------------------------------------------- //
			sc.close();

		}

		catch (Exception e) {

			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());

		}

	}

}