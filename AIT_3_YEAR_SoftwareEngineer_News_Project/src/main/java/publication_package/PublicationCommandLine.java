package publication_package;

import java.sql.ResultSet;
import java.util.Scanner;
import for_all_entities_package.CommandLinesExecution;

public class PublicationCommandLine implements CommandLinesExecution {

    Scanner scanner = new Scanner(System.in);

    // Display Menu Options for Publication
    private void publicationFunctionalities() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Please, choose ONE of the following options:");
        System.out.println("1. Create New Publication");
        System.out.println("2. Update Publication by ID");
        System.out.println("3. Read All Publications");
        System.out.println("4. Delete Publication by ID");
        System.out.println("99. Return to Main Menu");
        System.out.println("=============================================");
        System.out.println();
    }

    @Override
    public void execute() {
        try {
            PublicationCRUD connect = new PublicationCRUD();
            String functionNumber;
            boolean keepAppOpen = true;

            while (keepAppOpen) {
                publicationFunctionalities();
                functionNumber = scanner.next();

                switch (functionNumber) {
                    case "1":
                        // Create New Publication
                        System.out.print("Enter Publication Name: ");
                        String name = scanner.next();

                        System.out.print("Enter Publication Date: ");
                        String date = scanner.next();

                        System.out.print("Enter Stock Quantity: ");
                        int stock = scanner.nextInt();

                        System.out.print("Enter Publication Price: ");
                        double price = scanner.nextDouble();

                        Publication newPublication = new Publication(name, date, stock, price);
                        boolean publicationCreated = connect.createPublication(newPublication);

                        if (publicationCreated) {
                            System.out.println("New Publication Created Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Create Publication.");
                        }
                        break;

                    case "2":
                        // Update Publication by ID
                        System.out.print("Enter Publication ID to Update: ");
                        int publicationId = scanner.nextInt();

                        System.out.print("Enter New Publication Name: ");
                        String newName = scanner.next();

                        System.out.print("Enter New Publication Date: ");
                        String newDate = scanner.next();

                        System.out.print("Enter New Stock Quantity: ");
                        int newStock = scanner.nextInt();

                        System.out.print("Enter New Publication Price: ");
                        double newPrice = scanner.nextDouble();

                        Publication updatedPublication = new Publication(newName, newDate, newStock, newPrice);
                        boolean publicationUpdated = connect.updatePublicationById(publicationId, updatedPublication);

                        if (publicationUpdated) {
                            System.out.println("Publication Updated Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Update Publication.");
                        }
                        break;

                    case "3":
                        // Read All Publications
                        ResultSet resultSet = connect.readAllPublication();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                System.out.println("ID: " + resultSet.getInt("id"));
                                System.out.println("Name: " + resultSet.getString("name"));
                                System.out.println("Date: " + resultSet.getString("date"));
                                System.out.println("Stock: " + resultSet.getInt("stock"));
                                System.out.println("Price: " + resultSet.getDouble("price"));
                                System.out.println("------------------------------------");
                            }
                        } else {
                            System.out.println("No publications found.");
                        }
                        break;

                    case "4":
                        // Delete Publication by ID
                        System.out.print("Enter Publication ID to Delete: ");
                        int deletePublicationId = scanner.nextInt();

                        boolean publicationDeleted = connect.deletePublicationById(deletePublicationId);

                        if (publicationDeleted) {
                            System.out.println("Publication Deleted Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Delete Publication.");
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
