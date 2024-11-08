package order_book_package;

import java.sql.ResultSet;
import java.util.Scanner;
import for_all_entities_package.CommandLinesExecution;

public class OrderBookCommandLine implements CommandLinesExecution {

    Scanner scanner = new Scanner(System.in);

    // Display Menu Options for OrderBook
    private void orderBookFunctionalities() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Please, choose ONE of the following options:");
        System.out.println("1. Create New Order Book");
        System.out.println("2. Update Order Book by ID");
        System.out.println("3. Read All Order Books");
        System.out.println("4. Delete Order Book by ID");
        System.out.println("5. Read Order Book by ID");
        System.out.println("99. Return to Main Menu");
        System.out.println("=============================================");
        System.out.println();
    }

    @Override
    public void execute() {
        try {
            OrderBookCRUD connect = new OrderBookCRUD();
            String functionNumber;
            boolean keepAppOpen = true;

            while (keepAppOpen) {
                orderBookFunctionalities();
                functionNumber = scanner.next();

                switch (functionNumber) {
                    case "1":
                        // Create New Order Book
                        System.out.print("Enter Order Book Name: ");
                        String name = scanner.next();

                        System.out.print("Enter Postcode: ");
                        String postcode = scanner.next();

                        System.out.print("Enter Publication Name: ");
                        String publicationName = scanner.next();

                        OrderBook newOrderBook = new OrderBook(name, postcode, publicationName);
                        boolean orderBookCreated = connect.createOrderBook(newOrderBook);

                        if (orderBookCreated) {
                            System.out.println("New Order Book Created Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Create Order Book.");
                        }
                        break;

                    case "2":
                        // Update Order Book by ID
                        System.out.print("Enter Order Book ID to Update: ");
                        int orderBookId = scanner.nextInt();

                        System.out.print("Enter New Order Book Name: ");
                        String newName = scanner.next();

                        System.out.print("Enter New Postcode: ");
                        String newPostcode = scanner.next();

                        System.out.print("Enter New Publication Name: ");
                        String newPublicationName = scanner.next();

                        OrderBook updatedOrderBook = new OrderBook(newName, newPostcode, newPublicationName);
                        boolean orderBookUpdated = connect.updateOrderBookById(orderBookId, updatedOrderBook);

                        if (orderBookUpdated) {
                            System.out.println("Order Book Updated Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Update Order Book.");
                        }
                        break;

                    case "3":
                        // Read All Order Books
                        ResultSet resultSet = connect.readAllOrderBook();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                System.out.println("ID: " + resultSet.getInt("id"));
                                System.out.println("Name: " + resultSet.getString("name"));
                                System.out.println("Postcode: " + resultSet.getString("postcode"));
                                System.out.println("Publication Name: " + resultSet.getString("publicationName"));
                                System.out.println("------------------------------------");
                            }
                        } else {
                            System.out.println("No order books found.");
                        }
                        break;

                    case "4":
                        // Delete Order Book by ID
                        System.out.print("Enter Order Book ID to Delete: ");
                        int deleteOrderBookId = scanner.nextInt();

                        boolean orderBookDeleted = connect.deleteOrderBookById(deleteOrderBookId);

                        if (orderBookDeleted) {
                            System.out.println("Order Book Deleted Successfully.");
                        } else {
                            System.out.println("ERROR: Failed to Delete Order Book.");
                        }
                        break;

                    case "5":
                        // Read Order Book by ID
                        System.out.print("Enter Order Book ID to Read: ");
                        int readOrderBookId = scanner.nextInt();

                        ResultSet readResultSet = connect.readOrderBookById(readOrderBookId);
                        if (readResultSet != null && readResultSet.next()) {
                            System.out.println("ID: " + readResultSet.getInt("id"));
                            System.out.println("Name: " + readResultSet.getString("name"));
                            System.out.println("Postcode: " + readResultSet.getString("postcode"));
                            System.out.println("Publication Name: " + readResultSet.getString("publicationName"));
                        } else {
                            System.out.println("Order Book with ID " + readOrderBookId + " not found.");
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
