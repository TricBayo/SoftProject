package delivery_person_package;

import java.sql.ResultSet;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class DeliveryPersonCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Display Menu Options for Delivery Person
	private void deliveryPersonFunctionalities() {
		System.out.println();
		System.out.println("======== DELIVERY PERSON ENTIY MENU =========");
		System.out.println("Please, choose ONE of the following options:");
		System.out.println("1. View All Delivery Persons");
		System.out.println("2. Create a New Delivery Person");
		System.out.println("3. Update a Delivery Person by ID");
		System.out.println("4. Delete a Delivery Person by ID");
		System.out.println("99. Return to Main Menu");
		System.out.println("=============================================");
		System.out.println();
	}

	// Print the Delivery Person Table
	private boolean printDeliveryPersonTable(ResultSet rs) throws Exception {

		System.out.println();
		System.out.println("                 ---------------------------------------------------------------------------------------------------------");
		System.out.println("                                                       Table: " + rs.getMetaData().getTableName(1) + " :");
		System.out.println();

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%20s", rs.getMetaData().getColumnName(i));
		}

		System.out.println();

		while (rs.next()) {
			int personId = rs.getInt("id");
			String deliveryPersonName = rs.getString("dperson_name");
			String phoneNumber = rs.getString("phone_number");
			String areaId = rs.getString("area_Id");
			String postcode = rs.getString("postcode");
			String password = rs.getString("password");

			System.out.printf("%20s", personId);
			System.out.printf("%20s", deliveryPersonName);
			System.out.printf("%20s", phoneNumber);
			System.out.printf("%20s", areaId);
			System.out.printf("%20s", postcode);
			System.out.printf("%20s", password);
			System.out.println();
		}

		System.out.println();
		System.out.println("                 ---------------------------------------------------------------------------------------------------------");

		return true;
	}

	@Override
	public void execute() {
		try {
			DeliveryPersonCRUD connect = new DeliveryPersonCRUD();
			String functionNumber;
			boolean keepAppOpen = true;

			while (keepAppOpen) {
				deliveryPersonFunctionalities();
				functionNumber = scanner.next();

				switch (functionNumber) {

				case "1":
					// View All Delivery Persons
					ResultSet rSet = connect.readAllDeliveryPerson();

					if (rSet == null) {
						System.out.println("No Delivery Persons Found");
					} else {
						boolean tablePrinted = printDeliveryPersonTable(rSet);
						if (tablePrinted) {
							rSet.close();
						}
					}
					break;

				case "2":
					// Create a New Delivery Person
					System.out.print("Enter Name: ");
					String name = scanner.nextLine();
					scanner.nextLine();

					System.out.print("Enter Phone Number: ");
					String phoneNumber = scanner.nextLine();

					System.out.print("Enter Area ID: ");
					String areaId = scanner.nextLine();

					System.out.print("Enter Postcode: ");
					String postcode = scanner.nextLine();

					System.out.print("Enter Password: ");
					String password = scanner.nextLine();

					DeliveryPerson newPerson = new DeliveryPerson(name, phoneNumber, areaId, postcode, password);
					boolean personCreated = connect.createDeliveryPerson(newPerson);

					if (personCreated) {
						System.out.println("New Delivery Person Created Successfully.");
					} else {
						System.out.println("ERROR: Failed to Create Delivery Person.");
					}
					break;

				case "3":
					// Update a Delivery Person by ID
					System.out.print("Enter Delivery Person ID to Update: ");
					int personId = scanner.nextInt();
					scanner.nextLine();

					System.out.print("Enter New Name: ");
					String newName = scanner.nextLine();
					scanner.nextLine();

					System.out.print("Enter New Phone Number: ");
					String newPhoneNumber = scanner.nextLine();

					System.out.print("Enter New Area ID: ");
					String newAreaId = scanner.nextLine();

					System.out.print("Enter New Postcode: ");
					String newPostcode = scanner.nextLine();

					System.out.print("Enter New Password: ");
					String newPassword = scanner.nextLine();

					DeliveryPerson updatedPerson = new DeliveryPerson(newName, newPhoneNumber, newAreaId, newPostcode, newPassword);
					boolean personUpdated = connect.updateDeliveryPersonById(personId, updatedPerson);

					if (personUpdated) {
						System.out.println("Delivery Person Updated Successfully.");
					} else {
						System.out.println("ERROR: Failed to Update Delivery Person.");
					}
					break;

				case "4":
					// Delete a Delivery Person by ID
					System.out.print("Enter Delivery Person ID to Delete: ");
					int deletePersonId = scanner.nextInt();
					boolean personDeleted = connect.deleteDeliveryPersonById(deletePersonId);

					if (personDeleted) {
						System.out.println("Delivery Person Deleted Successfully.");
					} else {
						System.out.println("ERROR: Failed to Delete Delivery Person.");
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
			e.printStackTrace();
		}
	}
}
