package menus;

import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.Statement;
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

	private static EntitiesMySQLAccess menusAccess;

	public Menus() throws Exception {

		menusAccess = new EntitiesMySQLAccess();

	}

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		boolean keepRunning = true;

		while (keepRunning) {

			System.out.println("Select login type");
			System.out.println("---------------------------");
			System.out.println("1 - Newsagent");
			System.out.println("2 - Delivery Person");
			System.out.println("0 - Exit");
			System.out.println("---------------------------");
			System.out.print("Please, Enter choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice == 1) {
				System.out.println("\nHi, Welcome to Newsagent Login Account...");

			} else if (choice == 2) {
				System.out.println("\nHi, Welcome to Delivery Person Login Account...");

			} else if (choice == 0) {

				System.out.println("Exiting program...");

				keepRunning = false;
				continue;

			}

			// Prompt for Credentials
			System.out.print("Please, Enter Name: ");
			String name = scanner.nextLine();

			System.out.print("Please, Enter Password: ");
			String password = scanner.nextLine();

			boolean isAuthenticated = authenticate(choice, name, password);

			if (isAuthenticated) {

				if (choice == 1) {

					showNewsagentMenu(scanner);

				} else if (choice == 2) {

					showDeliveryPersonMenu(scanner);

				}

			} else {

				System.out.println("Invalid credentials. Please try again.");

			}

		}

		scanner.close();
	}

	// Authentication method
	private static boolean authenticate(int choice, String name, String password) throws Exception {

		Connection connection = menusAccess.getConnection();

		Statement s = connection.createStatement();

		boolean nameFound = false;

		if (choice == 1 && !(name.isBlank()) && !(password.isBlank())) {

			ResultSet rs1 = s.executeQuery("SELECT name, password FROM Software_Project_NewsCompany.newsagent_profile");

			while (rs1.next()) {

				String nameFromDB = rs1.getString("name");
				String passwordFromDB = rs1.getString("password");

				if (nameFromDB.equalsIgnoreCase(name) && passwordFromDB.equalsIgnoreCase(password)) {

					nameFound = true;
				}

			}

		} else if (choice == 2 && !(name.isBlank()) && !(password.isBlank())) {

			ResultSet rs2 = s.executeQuery("SELECT name, password FROM Software_Project_NewsCompany.delivery_person");

			while (rs2.next()) {

				String nameFromDB = rs2.getString("name");
				String passwordFromDB = rs2.getString("password");

				if (nameFromDB.equalsIgnoreCase(name) && passwordFromDB.equalsIgnoreCase(password)) {

					nameFound = true;
				}

			}

		}

		return nameFound;

	}

	// Newsagent menu
	private static void showNewsagentMenu(Scanner scanner) {

		while (true) {

			System.out.println("\nNewsagent Menu");
			System.out.println("---------------------------");
			System.out.println("1 - Customer Profile");
			System.out.println("2 - Delivery Person");
			System.out.println("3 - Delivery Area");
			System.out.println("4 - Delivery Docket");
			System.out.println("5 - Order Book");
			System.out.println("6 - Publication");
			System.out.println("7 - Daily Summary Report");
			System.out.println("8 - Monthly Invoice");
			System.out.println("9 - Warning Letter");
			System.out.println("10 - Newsagent");
			System.out.println("11 - Logout");
			System.out.println("---------------------------");
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

	// Delivery Person menu
	private static void showDeliveryPersonMenu(Scanner scanner) {

		while (true) {

			System.out.println("\nDelivery Person Menu");
			System.out.println("---------------------------");
			System.out.println("1 - Delivery Docket");
			System.out.println("2 - Logout");
			System.out.println("---------------------------");
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

}
