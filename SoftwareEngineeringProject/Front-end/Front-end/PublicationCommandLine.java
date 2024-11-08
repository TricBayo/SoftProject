package command;

import java.sql.ResultSet;
import java.util.Scanner;

public class PublicationCommandLine {

	// Present Customer with Functionalities
	private static void publicationFuctionalities() {

		System.out.println();
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Publication");
		System.out.println("2. Read ALL Publication Records");
		System.out.println("3. Update ALL Publication Records");
		System.out.println("4. Delete Publication Record by ID");
		System.out.println("99. Close the NewsAgent Application");
		System.out.println("=============================================");
		System.out.println();

		// The option chosen by user will be read by scanner inside the Main method

	}

	// Print The Contents of the Full Customer Table to News Agent Persona
	private static boolean printPublicationTable(ResultSet rs) throws Exception {

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("Table: " + rs.getMetaData().getTableName(1));

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%30s", rs.getMetaData().getColumnName(i));

		}

		System.out.println();
		while (rs.next()) {

			int id = rs.getInt("id");
			String editionname = rs.getString("edition name");
			String editiondate = rs.getString("edition date");
			String stockbalance = rs.getString("stock balance");
			

			System.out.printf("%30s", id);
			System.out.printf("%30s", editionname);
			System.out.printf("%30s", editiondate);
			System.out.printf("%30s", stockbalance);
			

			System.out.println();

		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	// Main
	public static void main(String[] args) {

		// Create the Database Object
		try {

			PublicationCRUD connect = new PublicationCRUD();

			// Configure System for Running
			Scanner sc = new Scanner(System.in);

			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen == true) {

				// Present list of functionality and get selection
				publicationFuctionalities();
				functionNumber = sc.next();

				switch (functionNumber) {

				// ---------------------------------------------------- //
				case "1":

					// Create New Customer Profile

					// Get Customer Details from the User
					System.out.printf("Enter Edition Name: \n");
					String editionname = sc.next();

					System.out.printf("Enter Edition Name: \n");
					String editiondate = sc.next();

					System.out.printf("Enter Stock Balance: \n");
					String stockbalance = sc.next();

				

					// Creating new Customer Object using the attributes above collected from user
					Publication newPublication = new Publication(editionname, editiondate, stockbalance);

					// Insert Customer Details into the database
					boolean insertResult = connect.createPublicationDetailsAccount(newPublication);

					if (insertResult == true) {
						System.out.println("Publication Details Saved");

					} else {
						System.out.println("ERROR: Publication Details NOT Saved");

					}
					break;

				// ---------------------------------------------------- //
				case "2":

					// Read ALL Customer Profiles
					ResultSet rSet = connect.readAllPublicationAccounts();

					if (rSet == null) {
						System.out.println("No Records Found");
						break;

					} else {
						boolean tablePrinted = printPublicationTable(rSet);

						if (tablePrinted == true)
							rSet.close();

					}
					break;

				// ---------------------------------------------------- //
				case "3":

					// Update Customer Profile by Id

					// Get New Customer Details from User
					System.out.printf("Enter Publication Id related to the Existing Publication you wish to be Updated: \n");
					int id = sc.nextInt();

					System.out.printf("Enter Edition Name: \n");
					String newEditionName = sc.next();

					System.out.printf("Enter Edition Date: \n");
					String newEditionDate = sc.next();

					System.out.printf("Enter Stock balance: \n");
					String newStockBalance = sc.next();

				

					// Creating new Customer Object using the attributes above collected from user
					Publication updatePublication = new Publication(newEditionName, newEditionDate, newStockBalance);

					// Insert Customer Details into the database
					boolean updateResult = connect.updatePublicationById(id, updatePublication);

					if (updateResult == true) {
						System.out.println("Publication Details Updated");

					} else {
						System.out.println("ERROR: Publication Details NOT Updated");

					}
					break;

				// ---------------------------------------------------- //
				case "4":

					// Delete Customer Profile by ID
					System.out.println("Enter Publication Id to be deleted or -99 to Clear all Rows");
					String deletePublication = sc.next();

					boolean PublicationDeleted = connect.deletePublicationById(Integer.parseInt(deletePublication));

					if ((PublicationDeleted == true) && (deletePublication.equals("-99"))) {
						System.out.println("Publication Table Emptied");

					} else if (PublicationDeleted == true)
						System.out.println("Publication Deleted");

					else
						System.out.println("ERROR: Publication Details NOT Deleted or Do Not Exist");

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