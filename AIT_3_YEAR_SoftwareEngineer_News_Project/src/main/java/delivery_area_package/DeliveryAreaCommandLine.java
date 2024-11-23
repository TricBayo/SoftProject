package delivery_area_package;

import java.sql.ResultSet;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class DeliveryAreaCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Display Menu Options for Delivery Area
	private void deliveryAreaFunctionalities() {
		System.out.println();
		System.out.println("========= Delivery Area Entity Menu =========");
		System.out.println("Please, choose ONE of the following options: |");
		System.out.println("1. Add New Delivery Area                     |");
		System.out.println("2. View All Delivery Areas                   |");
		System.out.println("3. Update a Delivery Area by ID              |");
		System.out.println("4. Delete a Delivery Area by ID              |");
		System.out.println("99. Return to Main Menu                      |");
		System.out.println("=============================================");
		System.out.println();
	}

	// Print the Delivery Area Table
	private boolean printDeliveryAreaTable(ResultSet rs) throws Exception {
		System.out.println("                 --------------------------------------------");
		System.out.println("                            Table: " + rs.getMetaData().getTableName(1) + " :");
		System.out.println();

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%20s", rs.getMetaData().getColumnName(i));
		}

		System.out.println();

		while (rs.next()) {
			int areaId = rs.getInt("id");
			String name = rs.getString("area_name");
			String postcode = rs.getString("area_postcode");

			System.out.printf("%20s", areaId);
			System.out.printf("%20s", name);
			System.out.printf("%20s", postcode);
			System.out.println();
		}

		System.out.println();
		System.out.println("                 --------------------------------------------");

		return true;
	}

	@Override
	public void execute() {
		try {
			DeliveryAreaCRUD connect = new DeliveryAreaCRUD();
			String functionNumber;
			boolean keepAppOpen = true;

			while (keepAppOpen) {
				deliveryAreaFunctionalities();
				functionNumber = scanner.next();

				switch (functionNumber) {
				case "1":
					// Add New Delivery Area
					System.out.println("Enter Delivery Area Name: ");
					String name = scanner.nextLine();
					scanner.nextLine();

					System.out.println("Enter Postcode of the Delivery Area: ");
					String postcode = scanner.nextLine();

					DeliveryArea newArea = new DeliveryArea(name, postcode);
					boolean areaAdded = connect.createDeliveryArea(newArea);

					if (areaAdded) {
						System.out.println("Delivery Area Added Successfully.");
					} else {
						System.out.println("ERROR: Failed to Add Delivery Area.");
					}
					break;

				case "2":
					// View All Delivery Areas
					ResultSet rSet = connect.readAllDeliveryArea();

					if (rSet == null) {
						System.out.println("No Delivery Areas Found");
					} else {
						boolean tablePrinted = printDeliveryAreaTable(rSet);
						if (tablePrinted) {
							rSet.close();
						}
					}
					break;

				case "3":
					// Update a Delivery Area by ID
					System.out.print("Enter Delivery Area ID to Update: ");
					int areaId = scanner.nextInt();

					System.out.print("Enter New Delivery Area Name: ");
					String newName = scanner.nextLine();
					scanner.nextLine();

					System.out.print("Enter New Postcode: ");
					String newPostcode = scanner.next();

					DeliveryArea updatedArea = new DeliveryArea(newName, newPostcode);
					boolean areaUpdated = connect.updateDeliveryAreaById(areaId, updatedArea);

					if (areaUpdated) {
						System.out.println("Delivery Area Updated Successfully.");
					} else {
						System.out.println("ERROR: Failed to Update Delivery Area.");
					}
					break;

				case "4":
					// Delete a Delivery Area by ID
					System.out.print("Enter Delivery Area ID to Delete or -99 to Return: ");
					String deleteAreaId = scanner.next();

					if (deleteAreaId.equals("-99")) {
						System.out.println("Returning to Main Menu...");
					} else {
						boolean areaDeleted = connect.deleteDeliveryAreaById(Integer.parseInt(deleteAreaId));

						if (areaDeleted) {
							System.out.println("Delivery Area Deleted.");
						} else {
							System.out.println("ERROR: Delivery Area Not Deleted or Does Not Exist.");
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
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE: " + e.getMessage());
		}
	}
}
