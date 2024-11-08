package command;




import java.sql.ResultSet;
import java.util.Scanner;

public class DeliveryDocketCommandLine {

	// Present Customer with Functionalities
	private static void deliveryDocketFuctionalities() {

		System.out.println();
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Delivery Docket");
		System.out.println("2. Read ALL Delivery Docket Records");
		System.out.println("3. Update ALL Delivery Docket Records");
		System.out.println("4. Delete Delivery Docket Record by ID");
		System.out.println("99. Close the NewsAgent Application");
		System.out.println("=============================================");
		System.out.println();

		// The option chosen by user will be read by scanner inside the Main method

	}

	// Print The Contents of the Full Customer Table to News Agent Persona
	private static boolean printDeliveryDocketTable(ResultSet rs) throws Exception {

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("Table: " + rs.getMetaData().getTableName(1));

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%30s", rs.getMetaData().getColumnName(i));

		}

		System.out.println();
		while (rs.next()) {

			int id = rs.getInt("id");
			String Oid = rs.getString("Order id");
			String Ddate = rs.getString("Docket Date");
			String Tnumber = rs.getString("Tracking Number");
			String postcode = rs.getString("postcode");
			String name = rs.getString("Customer name");
			String DPname = rs.getString("Delivery Person name");
			

			System.out.printf("%30s", id);
			System.out.printf("%30s", Oid);
			System.out.printf("%30s", Ddate);
			System.out.printf("%30s", Tnumber);
			System.out.printf("%30s", postcode);
			System.out.printf("%30s", name);
			System.out.printf("%30s", DPname);

			System.out.println();

		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	// Main
	public static void main(String[] args) {

		// Create the Database Object
		try {

			DeliveryDocketCRUD connect = new DeliveryDocketCRUD();

			// Configure System for Running
			Scanner sc = new Scanner(System.in);

			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen == true) {

				// Present list of functionality and get selection
				deliveryDocketFuctionalities();
				functionNumber = sc.next();

				switch (functionNumber) {

				// ---------------------------------------------------- //
				case "1":

					// Create New Customer Profile

					// Get Customer Details from the User
					System.out.printf("Enter Customer Name: \n");
					String name = sc.next();

					System.out.printf("Enter  Postcode: \n");
					String postcode = sc.next();

					System.out.printf("Enter Order id: \n");
					String Oid = sc.next();

					System.out.printf("Enter Docket Date: \n");
					String Ddate = sc.next();
					
					System.out.printf("Enter Tracking Number: \n");
					String Tnumber = sc.next();
					
					System.out.printf("Enter Delivery Docket Person name: \n");
					String DPname = sc.next();

					

					// Creating new Customer Object using the attributes above collected from user
					DeliveryDocket newDeliveryDocket = new DeliveryDocket(name, Oid, postcode, Ddate, Tnumber, DPname);

					// Insert Customer Details into the database
					boolean insertResult = connect.createCustomerDetailsAccount(newDeliveryDocket);

					if (insertResult == true) {
						System.out.println("Delivery Docket Details Saved");

					} else {
						System.out.println("ERROR: Delivery Docket Details NOT Saved");

					}
					break;

				// ---------------------------------------------------- //
				case "2":

					// Read ALL Customer Profiles
					ResultSet rSet = connect.readAllDeliveryDocketAccounts();

					if (rSet == null) {
						System.out.println("No Records Found");
						break;

					} else {
						boolean tablePrinted = printDeliveryDocketTable(rSet);

						if (tablePrinted == true)
							rSet.close();

					}
					break;

				// ---------------------------------------------------- //
				case "3":

					// Update Customer Profile by Id

					// Get New Customer Details from User
					System.out.printf("Enter Delivery Docket Id related to the Existing Delivery Docket you wish to be Updated: \n");
					int id = sc.nextInt();

					System.out.printf("Enter Customer Name: \n");
					String newName = sc.next();

					System.out.printf("Enter Postcode: \n");
					String newPostcode = sc.next();

					System.out.printf("Enter Order id: \n");
					String newOid = sc.next();

					System.out.printf("Enter Docket Date: \n");
					String newDdate = sc.next();
					
					System.out.printf("Enter Tracking Number: \n");
					String newTnumber = sc.next();
					
					System.out.printf("Enter Delivery Docket Person: \n");
					String newDPname = sc.next();

					

					// Creating new Customer Object using the attributes above collected from user
					DeliveryDocket updateDeliveryDocket = new DeliveryDocket(newName, newOid, newPostcode, newDdate, newTnumber, newDPname);

					// Insert Customer Details into the database
					boolean updateResult = connect.updateDeliveryDocketById(id, updateDeliveryDocket);

					if (updateResult == true) {
						System.out.println("Delivery Docket Details Updates");

					} else {
						System.out.println("ERROR: Delivery Docket Details NOT Updated");

					}
					break;

				// ---------------------------------------------------- //
				case "4":

					// Delete Customer Profile by ID
					System.out.println("Enter Delivery Docket Id to be deleted or -99 to Clear all Rows");
					String deleteDeliveryDocket = sc.next();

					boolean deliveryDocketDeleted = connect.deleteDeliveryDocketById(Integer.parseInt(deleteDeliveryDocket));

					if ((deliveryDocketDeleted == true) && (deleteDeliveryDocket.equals("-99"))) {
						System.out.println("Customer Table Emptied");

					} else if (deliveryDocketDeleted == true)
						System.out.println("Delivery Docket Deleted");

					else
						System.out.println("ERROR: Delivery Docket Details NOT Deleted or Do Not Exist");

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