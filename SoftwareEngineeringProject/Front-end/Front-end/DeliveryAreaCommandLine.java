package command;





import java.sql.ResultSet;
import java.util.Scanner;

public class DeliveryAreaCommandLine {

	// Present Customer with Functionalities
	private static void deliveryAreaFuctionalities() {

		System.out.println();
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Delivery Area");
		System.out.println("2. Read ALL Delivery Area Records");
		System.out.println("3. Update ALL Delivery Area Records");
		System.out.println("4. Delete Delivery Area Record by ID");
		System.out.println("99. Close the NewsAgent Application");
		System.out.println("=============================================");
		System.out.println();

		// The option chosen by user will be read by scanner inside the Main method

	}

	// Print The Contents of the Full Customer Table to News Agent Persona
	private static boolean printDeliveryAreaTable(ResultSet rs) throws Exception {

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("Table: " + rs.getMetaData().getTableName(1));

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%30s", rs.getMetaData().getColumnName(i));

		}

		System.out.println();
		while (rs.next()) {

			int id = rs.getInt("id");
			String Aname = rs.getString("Area name");
			String Apostcode = rs.getString("Area postcode");
			

			System.out.printf("%30s", id);
			System.out.printf("%30s", Aname);
			System.out.printf("%30s", Apostcode);
		

			System.out.println();

		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	// Main
	public static void main(String[] args) {

		// Create the Database Object
		try {

			DeliveryAreaCRUD connect = new DeliveryAreaCRUD();

			// Configure System for Running
			Scanner sc = new Scanner(System.in);

			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen == true) {

				// Present list of functionality and get selection
				deliveryAreaFuctionalities();
				functionNumber = sc.next();

				switch (functionNumber) {

				// ---------------------------------------------------- //
				case "1":

					// Create New Customer Profile

					// Get Customer Details from the User
					System.out.printf("Enter Area Name: \n");
					String Aname = sc.next();

					System.out.printf("Enter Area Postcode: \n");
					String Apostcode = sc.next();

					int paymentStatus = sc.nextInt();

					// Creating new Customer Object using the attributes above collected from user
					DeliveryArea newDeliveryArea = new DeliveryArea(Aname, Apostcode);

					// Insert Customer Details into the database
					boolean insertResult = connect.createDeliveryAreaDetailsAccount(newDeliveryArea);

					if (insertResult == true) {
						System.out.println("Area Details Saved");

					} else {
						System.out.println("ERROR: Area Details NOT Saved");

					}
					break;

				// ---------------------------------------------------- //
				case "2":

					// Read ALL Customer Profiles
					ResultSet rSet = connect.readAllDeliveryAreaAccounts();

					if (rSet == null) {
						System.out.println("No Records Found");
						break;

					} else {
						boolean tablePrinted = printDeliveryAreaTable(rSet);

						if (tablePrinted == true)
							rSet.close();

					}
					break;

				// ---------------------------------------------------- //
				case "3":

					// Update Customer Profile by Id

					// Get New Customer Details from User
					System.out.printf("Enter DeliveryArea Id related to the Existing DeliveryArea you wish to be Updated: \n");
					int id = sc.nextInt();

					System.out.printf("Enter Area Name: \n");
					String newAName = sc.next();

					System.out.printf("Enter Customer Postcode: \n");
					String newAPostcode = sc.next();

				

					// Creating new Customer Object using the attributes above collected from user
					DeliveryArea updateDeliveryArea = new DeliveryArea(newAName,  newAPostcode);

					// Insert Customer Details into the database
					boolean updateResult = connect.updateDeliveryAreaById(id, updateDeliveryArea);

					if (updateResult == true) {
						System.out.println("Delivery Area Details Updates");

					} else {
						System.out.println("ERROR: Delivery Area Details NOT Updated");

					}
					break;

				// ---------------------------------------------------- //
				case "4":

					// Delete Customer Profile by ID
					System.out.println("Enter Delivery Area Id to be deleted or -99 to Clear all Rows");
					String deleteDeliveryArea = sc.next();

					boolean deliveryAreaDeleted = connect.deleteDeliveryAreaById(Integer.parseInt(deleteDeliveryArea));

					if ((deliveryAreaDeleted == true) && (deleteDeliveryArea.equals("-99"))) {
						System.out.println("Customer Table Emptied");

					} else if (deliveryAreaDeleted == true)
						System.out.println("Delivery Area Deleted");

					else
						System.out.println("ERROR: Delivery Area Details NOT Deleted or Do Not Exist");

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