package warning_letter_package;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class WarningLetterCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Display Menu Options for Warning Letter
	private void warningLetterFunctionalities() {
		System.out.println();
		System.out.println("========= Warning Letter Entity Menu =========");
		System.out.println("Please, choose one of the following options:  |");
		System.out.println("                                              |");
		System.out.println("1. Create New Warning Letter                  |");
		System.out.println("2. Read All Warning Letters                   |");
		System.out.println("3. Read Warning Letter by ID                  |");
		System.out.println("4. Update Warning Letter by ID                |");
		System.out.println("5. Delete Warning Letter by ID                |");
		System.out.println("99. Return to Main Menu                       |");
		System.out.println("==============================================");
		System.out.println();
	}

	// Print the Warning Letter Table
	private boolean printWarningLetterTable(ResultSet rs) throws Exception {

		System.out.println();
		System.out.println("     -----------------------------------------------------------------------------");
		System.out.println("                              Table: " + rs.getMetaData().getTableName(1) + " :");
		System.out.println();

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%20s", rs.getMetaData().getColumnName(i));

		}

		ArrayList<String> customersArrayList = new ArrayList<>();

		System.out.println();

		while (rs.next()) {

			int debtAlert = rs.getInt("payment_status");
			String customerName = rs.getString("customer_name");
			String postcode = rs.getString("postcode");
			String areaName = rs.getString("area_name");

			System.out.printf("%20s", debtAlert);
			System.out.printf("%20s", customerName);
			System.out.printf("%20s", postcode);
			System.out.printf("%20s", areaName);

			System.out.println();

			if (debtAlert == 1) {
				customersArrayList.add(customerName);

			}

		}

		System.out.println();
		System.out.println("     -----------------------------------------------------------------------------");

		System.out.println("                                Warning Letter Sent:");
		System.out.println();

		for (String customer : customersArrayList) {

			System.out.printf("      Warning Letter sent to customer: " + customer);

		}

		System.out.println();
		System.out.println("     -----------------------------------------------------------------------------");
		System.out.println();

		return true;
	}

	@Override
	public void execute() {
		try {
			WarningLetterCRUD connect = new WarningLetterCRUD();
			String functionNumber;
			boolean keepAppOpen = true;

			while (keepAppOpen) {
				warningLetterFunctionalities();
				functionNumber = scanner.next();

				switch (functionNumber) {

				case "1":

					// Create New Warning Letter
					System.out.print("Enter Customer ID: ");
					int customerId = scanner.nextInt();

					System.out.print("Enter Delivery Area ID: ");
					int deliveryAreaId = scanner.nextInt();

					WarningLetter newWarningLetter = new WarningLetter(customerId, deliveryAreaId);

					boolean warningLetterCreated = connect.createWarningLetter(newWarningLetter);

					if (warningLetterCreated) {
						System.out.println("New Warning Letter Created Successfully.");

					} else {
						System.out.println("ERROR: Failed to Create Warning Letter.");

					}
					break;

				case "2":

					// Read All Warning Letters
					ResultSet resultSet = connect.readAllWarningLetter();

					if (resultSet == null) {
						System.out.println("No Warning Letters Found");

					} else {
						boolean tablePrinted = printWarningLetterTable(resultSet);

						if (tablePrinted) {
							resultSet.close();

						}
					}

					break;

				case "3":

					// Read Warning Letter by ID
					System.out.print("Enter Warning Letter ID to Read: ");
					int warningLetterId = scanner.nextInt();

					ResultSet rSetId = connect.readWarningLetterById(warningLetterId);

					if (rSetId == null) {

						System.out.println("No Delivery Dockets Found");

					} else {

						boolean tablePrinted = printWarningLetterTable(rSetId);

						if (tablePrinted) {
							rSetId.close();
						}
					}
					break;

				case "4":

					// Update Warning Letter by ID
					System.out.print("Enter Warning Letter ID to Update: ");
					int warningLetterIdUP = scanner.nextInt();

					System.out.print("Enter New Customer ID: ");
					int customerIdUp = scanner.nextInt();

					System.out.print("Enter New Delivery Area ID: ");
					int deliveryAreaIdUp = scanner.nextInt();

					WarningLetter updatedWarningLetter = new WarningLetter(customerIdUp, deliveryAreaIdUp);

					boolean warningLetterUpdated = connect.updateWarningLetterById(warningLetterIdUP, updatedWarningLetter);

					if (warningLetterUpdated) {
						System.out.println("Warning Letter Updated Successfully.");
					} else {
						System.out.println("ERROR: Failed to Update Warning Letter.");
					}
					break;

				case "5":

					// Delete Warning Letter by ID
					System.out.print("Enter Warning Letter ID to Delete: ");
					int deleteWarningLetterId = scanner.nextInt();

					boolean warningLetterDeleted = connect.deleteWarningLetterById(deleteWarningLetterId);

					if (warningLetterDeleted) {
						System.out.println("Warning Letter Deleted Successfully.");

					} else {
						System.out.println("ERROR: Failed to Delete Warning Letter.");

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
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE: " + e.getMessage());

		}
	}
}
