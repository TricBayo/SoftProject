package order_book_package;

import java.sql.ResultSet;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class OrderBookCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Display Menu Options for OrderBook
	private void orderBookFunctionalities() {
		System.out.println();
		System.out.println("============= Order Book Menu ===============");
		System.out.println("Please, choose one of the following options: |");
		System.out.println("                                             |");
		System.out.println("1. Create New Order Book                     |");
		System.out.println("2. Read All Order Books                      |");
		System.out.println("3. Read Order Book by ID                     |");
		System.out.println("4. Update Order Book by ID                   |");
		System.out.println("5. Delete Order Book by ID                   |");
		System.out.println("99. Return to Main Menu                      |");
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
					System.out.print("Please, Enter CUSTOMER PROFILE ID to Create a New Order Book: ");
					int customerId = scanner.nextInt();

					System.out.print("Please, Enter PUBLICATION ID to Create a New Order Book: ");
					int publicationId = scanner.nextInt();

					OrderBook newOrderBook = new OrderBook(customerId, publicationId);

					boolean orderBookCreated = connect.createOrderBook(newOrderBook);

					if (orderBookCreated) {
						System.out.println("New Order Book Created Successfully.");
					} else {
						System.out.println("ERROR: Failed to Create Order Book.");
					}
					break;

				case "2":
					// Read All Order Books
					ResultSet resultSet = connect.readAllOrderBook();

					if (resultSet != null) {

						while (resultSet.next()) {

							System.out.println("-------------------------");
							System.out.println("Name: " + resultSet.getString("customer_name"));
							System.out.println("Postcode: " + resultSet.getString("postcode"));
							System.out.println("Publication Name: " + resultSet.getString("publication_name"));
							System.out.println("-------------------------");
						}
					} else {
						System.out.println("No order books found.");

					}
					break;

				case "3":
					// Read Order Book by ID
					System.out.print("Enter Order Book ID to Read: ");
					int readOrderBookId = scanner.nextInt();

					ResultSet readResultSet = connect.readOrderBookById(readOrderBookId);

					if (readResultSet != null && readResultSet.next()) {

						System.out.println("Name: " + readResultSet.getString("customer_name"));
						System.out.println("Postcode: " + readResultSet.getString("postcode"));
						System.out.println("Publication Name: " + readResultSet.getString("publication_name"));

					} else {
						System.out.println("Order Book with ID " + readOrderBookId + " not found.");

					}

					break;

				case "4":

					// Update New Order Book
					System.out.print("Enter Report ID to Update: ");
					int updateId = scanner.nextInt();

					System.out.print("Please, Enter new CUSTOMER PROFILE ID: ");
					int customerIdUp = scanner.nextInt();

					System.out.print("Please, Enter new PUBLICATION ID: ");
					int publicationIdUp = scanner.nextInt();

					OrderBook newOrderBookUp = new OrderBook(customerIdUp, publicationIdUp);

					boolean orderBookCreatedUp = connect.updateOrderBookById(updateId, newOrderBookUp);

					if (orderBookCreatedUp) {
						System.out.println("Order Book Updated Successfully.");
					} else {
						System.out.println("ERROR: Failed to Update Order Book.");
					}
					break;

				case "5":
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
