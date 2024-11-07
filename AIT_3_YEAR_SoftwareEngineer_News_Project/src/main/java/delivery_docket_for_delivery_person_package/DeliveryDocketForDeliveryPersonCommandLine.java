package delivery_docket_for_delivery_person_package;

import java.sql.ResultSet;
import java.util.Scanner;
import for_all_entities_package.CommandLinesExecution;

public class DeliveryDocketForDeliveryPersonCommandLine implements CommandLinesExecution {

    Scanner scanner = new Scanner(System.in);

    // Display Menu Options for Delivery Docket
    private void deliveryDocketFunctionalities() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Please, choose ONE of the following options:");
        System.out.println("1. View All Delivery Dockets");
        System.out.println("2. Update a Delivery Docket by ID");
        System.out.println("99. Return to Main Menu");
        System.out.println("=============================================");
        System.out.println();
    }

    // Print the Delivery Docket Table
    private boolean printDeliveryDocketTable(ResultSet rs) throws Exception {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Table: " + rs.getMetaData().getTableName(1));

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.printf("%20s", rs.getMetaData().getColumnName(i));
        }

        System.out.println();

        while (rs.next()) {
            int docketId = rs.getInt("id");
            String customerName = rs.getString("customerName");
            String deliveryPersonName = rs.getString("deliveryPersonName");
            String orderDate = rs.getString("orderDate");
            String postcode = rs.getString("postcode");
            int trackNumber = rs.getInt("trackNumber");

            System.out.printf("%20s", docketId);
            System.out.printf("%20s", customerName);
            System.out.printf("%20s", deliveryPersonName);
            System.out.printf("%20s", orderDate);
            System.out.printf("%20s", postcode);
            System.out.printf("%20s", trackNumber);
            System.out.println();
        }

        System.out.println("--------------------------------------------------------------------");

        return true;
    }

    @Override
    public void execute() {
        try {
            DeliveryDocketForDeliveryPersonRU connect = new DeliveryDocketForDeliveryPersonRU();
            String functionNumber;
            boolean keepAppOpen = true;

            while (keepAppOpen) {
                deliveryDocketFunctionalities();
                functionNumber = scanner.next();

                switch (functionNumber) {
                    case "1":
                        // View All Delivery Dockets
                        ResultSet rSet = connect.readAllDeliveryDocket();

                        if (rSet == null) {
                            System.out.println("No Delivery Dockets Found");
                        } else {
                            boolean tablePrinted = printDeliveryDocketTable(rSet);
                            if (tablePrinted) {
                                rSet.close();
                            }
                        }
                        break;

                    case "2":
                        // Update a Delivery Docket by ID
                        System.out.print("Enter Delivery Docket ID to Update: ");
                        int docketId = scanner.nextInt();

                        System.out.print("Enter New Customer Name: ");
                        String newCustomerName = scanner.next();

                        System.out.print("Enter New Delivery Person Name: ");
                        String newDeliveryPersonName = scanner.next();

                        System.out.print("Enter New Order Date (YYYY-MM-DD): ");
                        String newOrderDate = scanner.next();

                        System.out.print("Enter New Postcode: ");
                        String newPostcode = scanner.next();

                        System.out.print("Enter New Track Number: ");
                        int newTrackNumber = scanner.nextInt();

                        DeliveryDocket updatedDocket = new DeliveryDocket(newCustomerName, newDeliveryPersonName, newOrderDate, newPostcode, newTrackNumber);
                        boolean docketUpdated = connect.updateDeliveryDocketById(docketId, updatedDocket);

                        if (docketUpdated) {
                            System.out.println("Delivery Docket Updated Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Update Delivery Docket.");
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

