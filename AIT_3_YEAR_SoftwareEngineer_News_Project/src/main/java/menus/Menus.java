package menus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import customer_profile_package.CustomerProfileCommandLine;
import daily_summary_report_package.DailySummaryReportCommandLine;
import delivery_area_package.DeliveryAreaCommandLine;
import delivery_docket_for_delivery_person_package.DeliveryDocketForDeliveryPersonCommandLine;
import delivery_docket_for_newsagent_package.DeliveryDocketForNewsagentCommandLine;
import delivery_person_package.DeliveryPersonCommandLine;
import for_all_entities_package.EntitiesMySQLAccess;
import monthly_invoice_package.MonthlyInvoiceCommandLine;
import news_agent_package.NewsagentCommandLine;
import order_book_package.OrderBookCommandLine;
import publication_package.PublicationCommandLine;
import warning_letter_package.WarningLetterCommandLine;

public class Menus {

	// An instance of EntitiesMySQLAccess to get Connection
	private EntitiesMySQLAccess menusAccess;

	// Constructor
	public Menus() throws Exception {

		menusAccess = new EntitiesMySQLAccess();

	}

	// main method
	public static void main(String[] args) throws Exception {

		Menus menus = new Menus();

		Scanner scanner = new Scanner(System.in);
		boolean keepRunning = true;

		while (keepRunning) {

			System.out.println();
			System.out.println("Select login type:");
			System.out.println("--------------------");
			System.out.println("1 - Newsagent       |");
			System.out.println("2 - Delivery Person |");
			System.out.println("0 - Exit            |");
			System.out.println("--------------------");
			System.out.print("Please, Enter choice: ");

			if (!scanner.hasNextInt()) {

				System.out.println("Invalid input. Please enter a number (0, 1, or 2).");

				scanner.next();

				continue;
			}

			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice == 1) {
				System.out.println("\nHi, Welcome to Newsagent Login Account:");

			} else if (choice == 2) {
				System.out.println("\nHi, Welcome to Delivery Person Login Account:");

			} else if (choice == 0) {
				System.out.println("Exiting program...");

				keepRunning = false;
				continue;

			} else {
				System.out.println("Invalid choice. Please select a valid option from the menu.");
				continue;

			}

			// Prompt for Credentials
			System.out.println();
			System.out.print("Please, Enter Name: ");
			String name = scanner.nextLine();

			System.out.print("Please, Enter Password: ");
			String password = scanner.nextLine();
			System.out.println();

			boolean isAuthenticated = menus.authenticate(choice, name, password);

			if (isAuthenticated) {

				if (choice == 1) {
					showNewsagentMenu(scanner, name);

				} else if (choice == 2) {
					showDeliveryPersonMenu(scanner, name);

				}

			} else {
				System.out.println("Invalid credentials. Please try again.");

			}
		}

		scanner.close();

	}

	// ----------------------- End of the main method ----------------------- //

	// Authentication method
	public boolean authenticate(int choice, String name, String password) throws Exception {

		boolean isAuthenticated = false;

		String query = "";

		if (choice == 1) {

			query = "SELECT password FROM Software_Project_NewsCompany.newsagent_credentials WHERE LOWER(newsagent_name) = LOWER(?)";

		} else if (choice == 2) {
			query = "SELECT password FROM Software_Project_NewsCompany.delivery_person WHERE LOWER(dperson_name) = LOWER(?)";

		} else {
			System.out.println("Invalid choice!");
			return false;

		}

		try (Connection connection = menusAccess.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, name);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					String passwordFromDB = rs.getString("password");

					if (passwordFromDB.equals(password)) {
						isAuthenticated = true;

					}
				}
			}
		}

		if (!isAuthenticated) {
			System.out.println("Access Denied!");
		}

		return isAuthenticated;
	}

	// ------------------ End of the authentication method ------------------ //

	// Newsagent menu
	private static void showNewsagentMenu(Scanner scanner, String name) {

		System.out.println("-------------------------------");
		System.out.println("Welcome " + name + ", Login Successful |");
		System.out.println("-------------------------------");

		while (true) {

			System.out.println("\nNewsagent Menu:");
			System.out.println("--------------------------");
			System.out.println("1 - Customer Profile      |");
			System.out.println("2 - Delivery Person       |");
			System.out.println("3 - Delivery Area         |");
			System.out.println("4 - Delivery Docket       |");
			System.out.println("5 - Order Book            |");
			System.out.println("6 - Publication           |");
			System.out.println("7 - Daily Summary Report  |");
			System.out.println("8 - Monthly Invoice       |");
			System.out.println("9 - Warning Letter        |");
			System.out.println("10 - Newsagent            |");
			System.out.println("11 - Logout               |");
			System.out.println("--------------------------");
			System.out.print("Please, Select an Option: ");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {

			case 1:

				// Call CustomerProfileCommandLine EXECUTE METHOD
				new CustomerProfileCommandLine().execute();
				break;

			case 2:

				// Call DeliveryPersonCommandLine EXECUTE METHOD
				new DeliveryPersonCommandLine().execute();
				break;

			case 3:

				// Call DeliveryAreaCommandLine EXECUTE METHOD
				new DeliveryAreaCommandLine().execute();
				break;

			case 4:

				// Call DeliveryDocketForNewsagentCommandLine EXECUTE METHOD
				new DeliveryDocketForNewsagentCommandLine().execute();
				break;

			case 5:

				// Call OrderBookCommandLine EXECUTE METHOD
				new OrderBookCommandLine().execute();
				break;

			case 6:

				// Call PublicationCommandLine EXECUTE METHOD
				new PublicationCommandLine().execute();
				break;

			case 7:

				// Call DailySummaryReportCommandLine EXECUTE METHOD
				new DailySummaryReportCommandLine().execute();
				break;

			case 8:

				// Call MonthlyInvoiceCommandLine EXECUTE METHOD
				new MonthlyInvoiceCommandLine().execute();
				break;

			case 9:

				// Call WarningLetterCommandLine EXECUTE METHOD
				new WarningLetterCommandLine().execute();
				break;

			case 10:

				// Call NewsagentCommandLine EXECUTE METHOD
				new NewsagentCommandLine().execute();
				break;

			case 11:

				System.out.println("Logging out...");
				return; // Exit and return to the main menu

			default:

				System.out.println("Invalid option. Please select again.");

			}

		}

	}

	// ----------------- End of the show Newsagent Menu method ----------------- //

	// Delivery Person menu
	private static void showDeliveryPersonMenu(Scanner scanner, String name) {

		System.out.println("---------------------------------");
		System.out.println("| Welcome " + name + " Login Successful |");
		System.out.println("---------------------------------");

		while (true) {

			System.out.println("\nDelivery Person Menu:");
			System.out.println("-------------------------");
			System.out.println("1 - Delivery Docket     |");
			System.out.println("2 - Logout              |");
			System.out.println("-------------------------");
			System.out.print("Please, Select an Option: ");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {

			case 1:

				// Call DeliveryDocketForDeliveryPersonCommandLine EXECUTE METHOD
				new DeliveryDocketForDeliveryPersonCommandLine().execute();
				break;

			case 2:

				System.out.println("Logging out...");
				return; // Exit and return to the main menu

			default:

				System.out.println("Invalid option. Please select again.");

			}

		}

	}

	// -------------- End of the show Delivery Person Menu method -------------- //

}
