package command;

//public class WarningLetterCommandLine {
//
//}

import java.sql.ResultSet;
import java.util.Scanner;

public class WarningLetterCommandLine {

	// Present Customer with Functionalities
	private static void warningLetterFuctionalities() {

		System.out.println();
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Warning Letter");
		System.out.println("2. Read ALL Warning Letter Records");
		System.out.println("3. Update ALL Warning Letter Records");
		System.out.println("4. Delete Warning Letter Record by ID");
		System.out.println("99. Close the NewsAgent Application");
		System.out.println("=============================================");
		System.out.println();

		// The option chosen by user will be read by scanner inside the Main method

	}

	// Print The Contents of the Full Customer Table to News Agent Persona
	private static boolean printWarningLetterTable(ResultSet rs) throws Exception {

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("Table: " + rs.getMetaData().getTableName(1));

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%30s", rs.getMetaData().getColumnName(i));

		}

		System.out.println();
		while (rs.next()) {

			int id = rs.getInt("id");
			String Iamount = rs.getString("Invoice Amount");
			String Damount = rs.getString("Debt Amount");
			String Ideadline = rs.getString("Invoice Deadline");
			String name = rs.getString("name");
			String postcode = rs.getString("postcode");
			String phoneNumber = rs.getString("phoneNumber");
			String email = rs.getString("email");
			int paymentStatus = rs.getInt("paymentStatus");

			System.out.printf("%30s", id);
			System.out.printf("%30s", Iamount);
			System.out.printf("%30s",Damount);
			System.out.printf("%30s", Ideadline);
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

			WarningLetterCRUD connect = new WarningLetterCRUD();

			// Configure System for Running
			Scanner sc = new Scanner(System.in);

			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen == true) {

				// Present list of functionality and get selection
				warningLetterFuctionalities();
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
					
					System.out.printf("Enter Invoice Amount: \n");
					String Iamount = sc.next();
					
					System.out.printf("Enter Debt Amount: \n");
					String Damount = sc.next();
					
					System.out.printf("Enter Invoice Deadline: \n");
					String Ideadline = sc.next();

					System.out.printf("Enter Customer Payment Status: \n");
					int paymentStatus = sc.nextInt();

					// Creating new Customer Object using the attributes above collected from user
					WarningLetter newWarningLetter = new WarningLetter(name, phoneNumber, postcode, email, paymentStatus, Iamount, Damount, Ideadline);

					// Insert Customer Details into the database
					boolean insertResult = connect.createWarningLetterDetailsAccount(newWarningLetter);

					if (insertResult == true) {
						System.out.println("WarningLetter Details Saved");

					} else {
						System.out.println("ERROR: WarningLetter Details NOT Saved");

					}
					break;

				// ---------------------------------------------------- //
				case "2":

					// Read ALL Customer Profiles
					ResultSet rSet = connect.readAllWarningLetterAccounts();

					if (rSet == null) {
						System.out.println("No Records Found");
						break;

					} else {
						boolean tablePrinted = printWarningLetterTable(rSet);

						if (tablePrinted == true)
							rSet.close();

					}
					break;

				// ---------------------------------------------------- //
				case "3":

					// Update Customer Profile by Id

					// Get New Customer Details from User
					System.out.printf("Enter Warning Letter Id related to the Existing Warning Letter you wish to be Updated: \n");
					int id = sc.nextInt();

					System.out.printf("Enter Customer Name: \n");
					String newName = sc.next();

					System.out.printf("Enter Customer Postcode: \n");
					String newPostcode = sc.next();

					System.out.printf("Enter Customer Phone Number: \n");
					String newPhoneNumber = sc.next();

					System.out.printf("Enter Customer Email: \n");
					String newEmail = sc.next();
					
					System.out.printf("Enter Invoice Amount: \n");
					String newIamount = sc.next();
					
					System.out.printf("Enter Debt Amount: \n");
					String newDamount = sc.next();
					
					System.out.printf("Enter Invoice Deadline: \n");
					String newIdeadline = sc.next();

					System.out.printf("Enter Customer Payment Status: \n");
					int newPaymentStatus = sc.nextInt();

					// Creating new Customer Object using the attributes above collected from user
					WarningLetter updateWarningLetter = new WarningLetter(newName, newPhoneNumber, newPostcode, newEmail, newPaymentStatus, newIamount, newDamount, newIdeadline );

					// Insert Customer Details into the database
					boolean updateResult = connect.updateWarningLetterById(id, updateWarningLetter);

					if (updateResult == true) {
						System.out.println("Warning Letter Details Updates");

					} else {
						System.out.println("ERROR: Warning Letter Details NOT Updated");

					}
					break;

				// ---------------------------------------------------- //
				case "4":

					// Delete Customer Profile by ID
					System.out.println("Enter Warning Letter Id to be deleted or -99 to Clear all Rows");
					String deleteWarningLetter = sc.next();

					boolean warningLetterDeleted = connect.deleteWarningLetterById(Integer.parseInt(deleteWarningLetter));

					if ((warningLetterDeleted == true) && (deleteWarningLetter.equals("-99"))) {
						System.out.println("Warning Letter Table Emptied");

					} else if (warningLetterDeleted == true)
						System.out.println("Warning Letter Deleted");

					else
						System.out.println("ERROR: Warning Letter Details NOT Deleted or Do Not Exist");

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