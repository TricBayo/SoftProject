package customer_profile_package;
import java.sql.ResultSet;
import java.util.Scanner;

public class CustomerProfileCommandLine {

	// Present Customer with Functionalities
	private static void customerProfileFuctionalities() {

		System.out.println();
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Customer Account");
		System.out.println("2. Read ALL Customer Records");
		System.out.println("3. Update ALL Customer Records");
		System.out.println("4. Delete Customer Record by ID");
		System.out.println("99. Close the NewsAgent Application");
		System.out.println("=============================================");
		System.out.println();

		// The option chosen by user will be read by scanner inside the Main method

	}

	// Print The Contents of the Full Customer Table to News Agent Persona
	private static boolean printCustomerProfileTable(ResultSet rs) throws Exception {

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("Table: " + rs.getMetaData().getTableName(1));

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%30s", rs.getMetaData().getColumnName(i));

		}

		System.out.println();
		while (rs.next()) {

			int id = rs.getInt("id");
			String name = rs.getString("name");
			String postcode = rs.getString("postcode");
			String phoneNumber = rs.getString("phoneNumber");
			String email = rs.getString("email");
			int paymentStatus = rs.getInt("paymentStatus");

			System.out.printf("%30s", id);
			System.out.printf("%30s", name);
			System.out.printf("%30s", postcode);
			System.out.printf("%30s", phoneNumber);
			System.out.printf("%30s", email);
			System.out.printf("%30s", paymentStatus);

			System.out.println();

		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	// Main
	public static void main(String[] args) {

		// Create the Database Object
		try {

			CustomerProfileCRUD connect = new CustomerProfileCRUD();

			// Configure System for Running
			Scanner sc = new Scanner(System.in);

			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen == true) {

				// Present list of functionality and get selection
				customerProfileFuctionalities();
				functionNumber = sc.next();

				switch (functionNumber) {

				// ---------------------------------------------------- //
				case "1":

					// Create New Customer Profile

					// Get Customer Details from the User
					System.out.printf("Enter Customer Name: \n");
					String name = sc.next();

					System.out.printf("Enter Customer Postcode: \n");
					String postcode = sc.next();

					System.out.printf("Enter Customer Phone Number: \n");
					String phoneNumber = sc.next();

					System.out.printf("Enter Customer Email: \n");
					String email = sc.next();

					System.out.printf("Enter Customer Payment Status: \n");
					int paymentStatus = sc.nextInt();

					// Creating new Customer Object using the attributes above collected from user
					CustomerProfile newCustomer = new CustomerProfile(name, phoneNumber, postcode, email, paymentStatus);

					// Insert Customer Details into the database
					boolean insertResult = connect.createCustomerDetailsAccount(newCustomer);

					if (insertResult == true) {
						System.out.println("Customer Details Saved");

					} else {
						System.out.println("ERROR: Customer Details NOT Saved");

					}
					break;

				// ---------------------------------------------------- //
				case "2":

					// Read ALL Customer Profiles
					ResultSet rSet = connect.readAllCustomerAccounts();

					if (rSet == null) {
						System.out.println("No Records Found");
						break;

					} else {
						boolean tablePrinted = printCustomerProfileTable(rSet);

						if (tablePrinted == true)
							rSet.close();

					}
					break;

				// ---------------------------------------------------- //
				case "3":

					// Update Customer Profile by Id

					// Get New Customer Details from User
					System.out.printf("Enter Customer Id related to the Existing Customer Profile you wish to be Updated: \n");
					int id = sc.nextInt();

					System.out.printf("Enter Customer Name: \n");
					String newName = sc.next();

					System.out.printf("Enter Customer Postcode: \n");
					String newPostcode = sc.next();

					System.out.printf("Enter Customer Phone Number: \n");
					String newPhoneNumber = sc.next();

					System.out.printf("Enter Customer Email: \n");
					String newEmail = sc.next();

					System.out.printf("Enter Customer Payment Status: \n");
					int newPaymentStatus = sc.nextInt();

					// Creating new Customer Object using the attributes above collected from user
					CustomerProfile updateCustomer = new CustomerProfile(newName, newPhoneNumber, newPostcode, newEmail, newPaymentStatus);

					// Insert Customer Details into the database
					boolean updateResult = connect.updateCustomerById(id, updateCustomer);

					if (updateResult == true) {
						System.out.println("Customer Details Updates");

					} else {
						System.out.println("ERROR: Customer Details NOT Updated");

					}
					break;

				// ---------------------------------------------------- //
				case "4":

					// Delete Customer Profile by ID
					System.out.println("Enter Customer Id to be deleted or -99 to Clear all Rows");
					String deleteCustomerProfile = sc.next();

					boolean customerProfileDeleted = connect.deleteCustomerById(Integer.parseInt(deleteCustomerProfile));

					if ((customerProfileDeleted == true) && (deleteCustomerProfile.equals("-99"))) {
						System.out.println("Customer Table Emptied");

					} else if (customerProfileDeleted == true)
						System.out.println("Customer Deleted");

					else
						System.out.println("ERROR: Customer Details NOT Deleted or Do Not Exist");

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