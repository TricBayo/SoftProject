package customer_profile_package;

import java.sql.ResultSet;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class CustomerProfileCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Present Customer with Functionalities
	private void customerProfileFunctionalities() {

		System.out.println();
		System.out.println("======== CUSTOMER PROFILE ENTITY MENU ========");
		System.out.println("Please, choose ONE of the following options:  |");
		System.out.println("                                              |");
		System.out.println("1. Create Customer Account                    |");
		System.out.println("2. Read ALL Customer Records                  |");
		System.out.println("3. Update Customer Record by ID               |");
		System.out.println("4. Delete Customer Record by ID               |");
		System.out.println("99. Return to NewsAgent Menu                  |");
		System.out.println("==============================================");
		System.out.println();

	}

	private boolean printCustomerProfileTable(ResultSet rs) throws Exception {

		System.out.println();
		System.out.println("                 ---------------------------------------------------------------------------------------------------------");
		System.out.println("                                                      Table: " + rs.getMetaData().getTableName(1) + " :");
		System.out.println();

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%20s", rs.getMetaData().getColumnName(i));
		}

		System.out.println();

		while (rs.next()) {

			int id = rs.getInt("id");
			String name = rs.getString("customer_name");
			String postcode = rs.getString("postcode");
			String phoneNumber = rs.getString("phone_number");
			String email = rs.getString("email");
			int paymentStatus = rs.getInt("payment_status");

			System.out.printf("%20s", id);
			System.out.printf("%20s", name);
			System.out.printf("%20s", postcode);
			System.out.printf("%20s", phoneNumber);
			System.out.printf("%20s", email);
			System.out.printf("%20s", paymentStatus);

			System.out.println();

		}

		System.out.println();
		System.out.println("                 ---------------------------------------------------------------------------------------------------------");

		return true;
	}

	// Implement the execute method from CommandLinesExecution Interface
	@Override
	public void execute() {

		try {

			CustomerProfileCRUD connect = new CustomerProfileCRUD();
			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen) {

				// Present list of functionality and get selection
				customerProfileFunctionalities();
				functionNumber = scanner.next();

				switch (functionNumber) {

				case "1":

					// Create New Customer Profile
					scanner.nextLine();
					System.out.print("Enter Customer Name: ");
					String name = scanner.nextLine();

					System.out.print("Enter Customer Postcode: ");
					String postcode = scanner.nextLine();

					System.out.print("Enter Customer Phone Number: ");
					String phoneNumber = scanner.nextLine();

					System.out.print("Enter Customer Email: ");
					String email = scanner.nextLine();

					System.out.print("Enter Customer Debt Months: (0 to 2 = Green Alert | 3 or greater = Red Alert)");
					int paymentStatus = scanner.nextInt();
					scanner.nextLine();

					CustomerProfile newCustomer = new CustomerProfile(name, phoneNumber, postcode, email, paymentStatus);

					if (paymentStatus >= 3) {
						newCustomer.setPaymentStatus(1);

					} else {
						newCustomer.setPaymentStatus(0);

					}

					boolean insertResult = connect.createCustomerDetailsAccount(newCustomer);

					if (insertResult) {

						System.out.println("Customer Details Saved");

					} else {

						System.out.println("ERROR: Customer Details NOT Saved");

					}

					break;

				case "2":

					// Read ALL Customer Profiles
					ResultSet rSet = connect.readAllCustomerAccounts();

					if (rSet == null) {

						System.out.println("No Records Found");
						break;

					} else {

						boolean tablePrinted = printCustomerProfileTable(rSet);

						if (tablePrinted) {

							rSet.close();
						}
					}

					break;

				case "3":

					// Update Customer Profile by ID
					System.out.print("Enter Customer Id to Update: ");
					int id = scanner.nextInt();
					scanner.nextLine();

					System.out.print("Enter New Customer Name: ");
					String newName = scanner.nextLine();

					System.out.print("Enter New Customer Postcode: ");
					String newPostcode = scanner.nextLine();

					System.out.print("Enter New Customer Phone Number: ");
					String newPhoneNumber = scanner.nextLine();

					System.out.print("Enter New Customer Email: ");
					String newEmail = scanner.nextLine(); //

					System.out.print("Enter New Customer Debt Months: (0 to 2 = Green Alert | 3 or greater = Red Alert): ");
					int newPaymentStatus = scanner.nextInt();
					scanner.nextLine();

					CustomerProfile updateCustomer = new CustomerProfile(newName, newPhoneNumber, newPostcode, newEmail, newPaymentStatus);

					if (newPaymentStatus >= 3) {
						updateCustomer.setPaymentStatus(1);

					} else {
						updateCustomer.setPaymentStatus(0);

					}

					boolean updateResult = connect.updateCustomerById(id, updateCustomer);

					if (updateResult) {
						System.out.println("Customer Details Updated");

					} else {
						System.out.println("ERROR: Customer Details NOT Updated");

					}

					break;

				case "4":

					// Delete Customer Profile by ID
					System.out.print("Enter Customer Id to be deleted or -99 to Return: ");
					String deleteCustomerProfile = scanner.next();

					if (deleteCustomerProfile.equals("-99")) {

						keepAppOpen = false;
						System.out.println("Returning to NewsAgent Menu...");

					} else {

						boolean customerProfileDeleted = connect.deleteCustomerById(Integer.parseInt(deleteCustomerProfile));

						if (customerProfileDeleted) {

							System.out.println("Customer Deleted");

						} else {

							System.out.println("ERROR: Customer Details NOT Deleted or Do Not Exist");

						}

					}

					break;

				case "99":

					// Return to NewsAgent Menu
					keepAppOpen = false;
					System.out.println("Returning to NewsAgent Menu...");

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
