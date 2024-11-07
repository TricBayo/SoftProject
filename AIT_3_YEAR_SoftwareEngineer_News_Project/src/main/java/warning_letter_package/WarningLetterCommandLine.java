package warning_letter_package;

import java.sql.ResultSet;
import java.util.Scanner;
import for_all_entities_package.CommandLinesExecution;

public class WarningLetterCommandLine implements CommandLinesExecution {

    Scanner scanner = new Scanner(System.in);

    // Display Menu Options for Warning Letter
    private void warningLetterFunctionalities() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Please, choose ONE of the following options:");
        System.out.println("1. Create New Warning Letter");
        System.out.println("2. Update Warning Letter by ID");
        System.out.println("3. Read All Warning Letters");
        System.out.println("4. Delete Warning Letter by ID");
        System.out.println("99. Return to Main Menu");
        System.out.println("=============================================");
        System.out.println();
    }

    @Override
    public void execute() {
        try {
            WarningLetterCRUD connect = new WarningLetterCRUD();
            String functionNumber;
            boolean keepAppOpen = true;

            while (keepAppOpen) {
                warningLetterFunctionalities();
                functionNumber = scanner.next();

                switch (functionNumber) {
                    case "1":
                        // Create New Warning Letter
                        System.out.print("Enter Warning Letter Name: ");
                        String name = scanner.next();

                        System.out.print("Enter Postcode: ");
                        String postcode = scanner.next();

                        System.out.print("Enter Amount In Debt: ");
                        double amountInDebt = scanner.nextDouble();

                        System.out.print("Enter Payment Status (1 for Paid, 0 for Unpaid): ");
                        int paymentStatus = scanner.nextInt();

                        WarningLetter newWarningLetter = new WarningLetter(name, postcode, amountInDebt, paymentStatus);
                        boolean warningLetterCreated = connect.createWarningLetter(newWarningLetter);

                        if (warningLetterCreated) {
                            System.out.println("New Warning Letter Created Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Create Warning Letter.");
                        }
                        break;

                    case "2":
                        // Update Warning Letter by ID
                        System.out.print("Enter Warning Letter ID to Update: ");
                        int warningLetterId = scanner.nextInt();

                        System.out.print("Enter New Warning Letter Name: ");
                        String newName = scanner.next();

                        System.out.print("Enter New Postcode: ");
                        String newPostcode = scanner.next();

                        System.out.print("Enter New Amount In Debt: ");
                        double newAmountInDebt = scanner.nextDouble();

                        System.out.print("Enter New Payment Status (1 for Paid, 0 for Unpaid): ");
                        int newPaymentStatus = scanner.nextInt();

                        WarningLetter updatedWarningLetter = new WarningLetter(newName, newPostcode, newAmountInDebt, newPaymentStatus);
                        boolean warningLetterUpdated = connect.updateWarningLetterById(warningLetterId, updatedWarningLetter);

                        if (warningLetterUpdated) {
                            System.out.println("Warning Letter Updated Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Update Warning Letter.");
                        }
                        break;

                    case "3":
                        // Read All Warning Letters
                        ResultSet resultSet = connect.readAllWarningLetter();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                System.out.println("ID: " + resultSet.getInt("id"));
                                System.out.println("Name: " + resultSet.getString("name"));
                                System.out.println("Postcode: " + resultSet.getString("postcode"));
                                System.out.println("Amount In Debt: " + resultSet.getDouble("amountInDebt"));
                                System.out.println("Payment Status: " + (resultSet.getInt("paymentStatus") == 1 ? "Paid" : "Unpaid"));
                                System.out.println("------------------------------------");
                            }
                        } else {
                            System.out.println("No warning letters found.");
                        }
                        break;

                    case "4":
                        // Delete Warning Letter by ID
                        System.out.print("Enter Warning Letter ID to Delete: ");
                        int deleteWarningLetterId = scanner.nextInt();

                        boolean warningLetterDeleted = connect.deleteWarningLetterById(deleteWarningLetterId);

                        if (warningLetterDeleted) {
                            System.out.println("Warning Letter Deleted Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Delete Warning Letter.");
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
