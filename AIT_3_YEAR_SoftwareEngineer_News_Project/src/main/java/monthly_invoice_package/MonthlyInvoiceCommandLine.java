package monthly_invoice_package;

import java.sql.ResultSet;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class MonthlyInvoiceCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Display Menu Options for Monthly Invoice
	private void monthlyInvoiceFunctionalities() {
		System.out.println();
		System.out.println("======== Monthly Invoice Entity Menu ========");
		System.out.println("Please, choose ONE of the following options: |");
		System.out.println("                                             |");
		System.out.println("1. Read All Monthly Invoices                 |");
		System.out.println("2. Create a New Monthly Invoice              |");
		System.out.println("3. Read a Monthly Invoice by ID              |");
		System.out.println("4. Update a Monthly Invoice by ID            |");
		System.out.println("5. Delete a Monthly Invoice by ID            |");
		System.out.println("99. Return to Main Menu                      |");
		System.out.println("=============================================");
		System.out.println();
	}

	// Print the Monthly Invoice Table
	private boolean printMonthlyInvoiceTable(ResultSet rs) throws Exception {

		System.out.println("      ----------------------------------------------------------------------------");

		System.out.println("                             Table: " + rs.getMetaData().getTableName(1));
		System.out.println();

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%20s", rs.getMetaData().getColumnName(i));

		}

		System.out.println();

		while (rs.next()) {

			String name = rs.getString("customer_name");
			String postcode = rs.getString("postcode");
			String paymentDate = rs.getString("payment_date");
			String amountToPay = rs.getString("amount_to_pay");

			System.out.printf("%20s", name);
			System.out.printf("%20s", postcode);
			System.out.printf("%20s", paymentDate);
			System.out.printf("%20s", amountToPay);
			System.out.println();
		}

		System.out.println();
		System.out.println("      ----------------------------------------------------------------------------");

		return true;
	}

	@Override
	public void execute() {
		try {
			MonthlyInvoiceCRUD connect = new MonthlyInvoiceCRUD();
			String functionNumber;
			boolean keepAppOpen = true;

			while (keepAppOpen) {

				monthlyInvoiceFunctionalities();
				functionNumber = scanner.next();

				switch (functionNumber) {

				case "1":

					// Read All Monthly Invoices
					ResultSet rSet = connect.readAllMonthlyInvoice();

					if (rSet == null) {
						System.out.println("No Monthly Invoices Found");
					} else {
						boolean tablePrinted = printMonthlyInvoiceTable(rSet);

						if (tablePrinted) {
							rSet.close();
						}
					}
					break;

				case "2":

					// Create a New Monthly Invoice
					System.out.print("Enter Customer ID: ");
					int customerId = scanner.nextInt();

					System.out.print("Enter Payment Date: ");
					String paymentDate = scanner.next();

					System.out.print("Enter Amount to Pay: ");
					String amountToPay = scanner.next();

					MonthlyInvoice newInvoice = new MonthlyInvoice(customerId, paymentDate, amountToPay);
					boolean invoiceCreated = connect.createMonthlyInvoice(newInvoice);

					if (invoiceCreated) {
						System.out.println("New Monthly Invoice Created Successfully.");
					} else {
						System.out.println("ERROR: Failed to Create Monthly Invoice.");
					}
					break;

				case "3":

					// Read a Monthly Invoice by ID
					System.out.print("Enter Monthly Invoice ID to Read: ");
					int invoiceId = scanner.nextInt();

					ResultSet rSetId = connect.readMonthlyInvoiceById(invoiceId);

					if (rSetId == null) {

						System.out.println("No Delivery Dockets Found");

					} else {

						boolean tablePrinted = printMonthlyInvoiceTable(rSetId);
						if (tablePrinted) {
							rSetId.close();

						}
					}
					break;

				case "4":

					// Update a Monthly Invoice by ID
					System.out.print("Enter Monthly Invoice ID to Update: ");
					int invoiceIdUp = scanner.nextInt();

					System.out.print("Enter New Customer ID: ");
					int customerIdUp = scanner.nextInt();

					System.out.print("Enter New Payment Date: ");
					String paymentDateUp = scanner.next();

					System.out.print("Enter New Amount to Pay: ");
					String amountToPayUp = scanner.next();

					MonthlyInvoice updatedInvoice = new MonthlyInvoice(customerIdUp, paymentDateUp, amountToPayUp);

					boolean invoiceUpdated = connect.updateMonthlyInvoiceById(invoiceIdUp, updatedInvoice);

					if (invoiceUpdated) {
						System.out.println("Monthly Invoice Updated Successfully.");

					} else {
						System.out.println("ERROR: Failed to Update Monthly Invoice.");

					}

					break;

				case "5":

					// Delete a Monthly Invoice by ID
					System.out.print("Enter Monthly Invoice ID to Delete: ");
					int deleteInvoiceId = scanner.nextInt();
					boolean invoiceDeleted = connect.deleteMonthlyInvoiceById(deleteInvoiceId);

					if (invoiceDeleted) {
						System.out.println("Monthly Invoice Deleted Successfully.");
					} else {
						System.out.println("ERROR: Failed to Delete Monthly Invoice.");
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
