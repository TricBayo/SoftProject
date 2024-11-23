package delivery_docket_for_newsagent_package;

import java.sql.ResultSet;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class DeliveryDocketForNewsagentCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Display Menu Options for Delivery Docket
	private void deliveryDocketFunctionalities() {
		System.out.println();
		System.out.println("=============================================");
		System.out.println("Please, choose ONE of the following options: |");
		System.out.println("1. View All Delivery Dockets                 |");
		System.out.println("2. Create a New Delivery Docket              |");
		System.out.println("3. Read a Delivery Docket By ID              |");
		System.out.println("4. Update a Delivery Docket by ID            |");
		System.out.println("5. Delete a Delivery Docket by ID            |");
		System.out.println("99. Return to Main Menu                      |");
		System.out.println("=============================================");
		System.out.println();
	}

	// Print the Delivery Docket Table
	private boolean printDeliveryDocketTable(ResultSet rs) throws Exception {

		System.out.println();
		System.out.println("         -----------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    Table: " + rs.getMetaData().getTableName(1) + " :");
		System.out.println();

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%20s", rs.getMetaData().getColumnName(i));

		}

		System.out.println(rs);

		System.out.println();

		while (rs.next()) {

			String orderDate = rs.getString("order_date");
			int trackNumber = rs.getInt("track_number");
			String deliveryPersonName = rs.getString("dperson_name");
			String customerName = rs.getString("customer_name");
			String postcode = rs.getString("postcode");
			int deliveryStatus = rs.getInt("delivery_status");

			System.out.printf("%20s", orderDate);
			System.out.printf("%20s", trackNumber);
			System.out.printf("%20s", deliveryPersonName);
			System.out.printf("%20s", customerName);
			System.out.printf("%20s", postcode);
			System.out.printf("%20s", deliveryStatus);

			System.out.println();
		}

		System.out.println();
		System.out.println("         -----------------------------------------------------------------------------------------------------------------");

		return true;
	}

	@Override
	public void execute() {

		try {

			DeliveryDocketForNewsagentCRUD connect = new DeliveryDocketForNewsagentCRUD();
			String functionNumber;
			boolean keepAppOpen = true;

			while (keepAppOpen) {
				deliveryDocketFunctionalities();
				functionNumber = scanner.next();

				switch (functionNumber) {

				case "1":
					// View All Delivery Docket
					ResultSet rSet = connect.readAllDeliveryDocket();

					if (rSet == null) {

						System.out.println("No Delivery Dockets Found");

					} else {

						boolean tablePrinted = printDeliveryDocketTable(rSet);
						if (tablePrinted) {
							rSet.close();

						}
					}
					break;

				case "2":
					// Create a New Delivery Docket
					System.out.print("Enter Customer ID: ");
					int customerId = scanner.nextInt();

					System.out.print("Enter Delivery Person ID: ");
					int deliveryPersonId = scanner.nextInt();

					System.out.print("Enter Order Date (DD/MM/YYYY): ");
					String orderDate = scanner.next();

					System.out.print("Enter Track Number: ");
					int trackNumber = scanner.nextInt();

					System.out.print("Enter Delivery Status: ");
					int deliveryStatus = scanner.nextInt();

					DeliveryDocket newDocket = new DeliveryDocket(orderDate, trackNumber, deliveryStatus, customerId, deliveryPersonId);
					boolean docketCreated = connect.createDeliveryDocket(newDocket);

					if (docketCreated) {
						System.out.println("New Delivery Docket Created Successfully.");
					} else {
						System.out.println("ERROR: Failed to Create Delivery Docket.");
					}
					break;

				case "3":
					// Read Delivery Docket by ID
					System.out.print("Enter Delivery Docket ID to Read: ");
					int docketIdRd = scanner.nextInt();

					// View Delivery Docket by ID
					ResultSet rSetId = connect.readDeliveryDocketById(docketIdRd);

					if (rSetId == null) {

						System.out.println("No Delivery Dockets Found");

					} else {

						boolean tablePrinted = printDeliveryDocketTable(rSetId);
						if (tablePrinted) {
							rSetId.close();

						}
					}
					break;

				case "4":
					// Update a Delivery Docket by ID
					System.out.print("Enter Delivery Docket ID to Update: ");
					int docketId = scanner.nextInt();

					System.out.print("Enter New Customer ID: ");
					int customerIdUp = scanner.nextInt();

					System.out.print("Enter New Delivery Person ID: ");
					int deliveryPersonUpId = scanner.nextInt();

					System.out.print("Enter New Order Date (DD/MM/YYYY): ");
					String newOrderDate = scanner.next();

					System.out.print("Enter New Track Number: ");
					int newTrackNumber = scanner.nextInt();

					System.out.print("Enter New Delivery Status: ");
					int deliveryStatusUp = scanner.nextInt();

					DeliveryDocket updatedDocket = new DeliveryDocket(newOrderDate, newTrackNumber, deliveryStatusUp, customerIdUp, deliveryPersonUpId);

					boolean docketUpdated = connect.updateDeliveryDocketById(docketId, updatedDocket);

					if (docketUpdated) {
						System.out.println("Delivery Docket Updated Successfully.");
					} else {
						System.out.println("ERROR: Failed to Update Delivery Docket.");
					}
					break;

				case "5":
					// Delete a Delivery Docket by ID
					System.out.print("Enter Delivery Docket ID to Delete: ");
					int deleteDocketId = scanner.nextInt();
					boolean docketDeleted = connect.deleteDeliveryDocketById(deleteDocketId);

					if (docketDeleted) {
						System.out.println("Delivery Docket Deleted Successfully.");
					} else {
						System.out.println("ERROR: Failed to Delete Delivery Docket.");
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