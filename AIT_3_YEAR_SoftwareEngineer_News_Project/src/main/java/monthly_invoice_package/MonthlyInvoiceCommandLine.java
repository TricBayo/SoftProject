package monthly_invoice_package;

import java.sql.ResultSet;
import java.util.Scanner;
import for_all_entities_package.CommandLinesExecution;

public class MonthlyInvoiceCommandLine implements CommandLinesExecution {

    Scanner scanner = new Scanner(System.in);

    // Display Menu Options for Monthly Invoice
    private void monthlyInvoiceFunctionalities() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Please, choose ONE of the following options:");
        System.out.println("1. View All Monthly Invoices");
        System.out.println("2. Create a New Monthly Invoice");
        System.out.println("3. Update a Monthly Invoice by ID");
        System.out.println("4. Delete a Monthly Invoice by ID");
        System.out.println("99. Return to Main Menu");
        System.out.println("=============================================");
        System.out.println();
    }

    // Print the Monthly Invoice Table
    private boolean printMonthlyInvoiceTable(ResultSet rs) throws Exception {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Table: " + rs.getMetaData().getTableName(1));

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.printf("%20s", rs.getMetaData().getColumnName(i));
        }

        System.out.println();

        while (rs.next()) {
            int invoiceId = rs.getInt("id");
            String name = rs.getString("name");
            String postcode = rs.getString("postcode");
            String paymentDate = rs.getString("paymentDate");
            String amountToPay = rs.getString("amountToPay");

            System.out.printf("%20s", invoiceId);
            System.out.printf("%20s", name);
            System.out.printf("%20s", postcode);
            System.out.printf("%20s", paymentDate);
            System.out.printf("%20s", amountToPay);
            System.out.println();
        }

        System.out.println("--------------------------------------------------------------------");

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
                        // View All Monthly Invoices
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
                        System.out.print("Enter Customer Name: ");
                        String name = scanner.next();

                        System.out.print("Enter Postcode: ");
                        String postcode = scanner.next();

                        System.out.print("Enter Payment Date: ");
                        String paymentDate = scanner.next();

                        System.out.print("Enter Amount to Pay: ");
                        String amountToPay = scanner.next();

                        MonthlyInvoice newInvoice = new MonthlyInvoice(name, postcode, paymentDate, amountToPay);
                        boolean invoiceCreated = connect.createMonthlyInvoice(newInvoice);

                        if (invoiceCreated) {
                            System.out.println("New Monthly Invoice Created Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Create Monthly Invoice.");
                        }
                        break;

                    case "3":
                        // Update a Monthly Invoice by ID
                        System.out.print("Enter Monthly Invoice ID to Update: ");
                        int invoiceId = scanner.nextInt();

                        System.out.print("Enter New Customer Name: ");
                        String newName = scanner.next();

                        System.out.print("Enter New Postcode: ");
                        String newPostcode = scanner.next();

                        System.out.print("Enter New Payment Date: ");
                        String newPaymentDate = scanner.next();

                        System.out.print("Enter New Amount to Pay: ");
                        String newAmountToPay = scanner.next();

                        MonthlyInvoice updatedInvoice = new MonthlyInvoice(newName, newPostcode, newPaymentDate, newAmountToPay);
                        boolean invoiceUpdated = connect.updateMonthlyInvoiceById(invoiceId, updatedInvoice);

                        if (invoiceUpdated) {
                            System.out.println("Monthly Invoice Updated Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Update Monthly Invoice.");
                        }
                        break;

                    case "4":
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
