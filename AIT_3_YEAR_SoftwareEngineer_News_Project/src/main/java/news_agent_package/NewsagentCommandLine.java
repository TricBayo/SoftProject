package news_agent_package;

import java.sql.ResultSet;
import java.util.Scanner;

import for_all_entities_package.CommandLinesExecution;

public class NewsagentCommandLine implements CommandLinesExecution {

	Scanner scanner = new Scanner(System.in);

	// Display Menu Options for Newsagent Credentials
	private void newsagentFunctionalities() {
		System.out.println();
		System.out.println("=============================================");
		System.out.println("                                             |");
		System.out.println("Please, choose ONE of the following options: |");
		System.out.println("1. Create New Newsagent Credentials          |");
		System.out.println("2. Read Newsagent Credentials                |");
		System.out.println("3. Update Newsagent Credentials by ID        |");
		System.out.println("4. Delete Newsagent Credentials by ID        |");
		System.out.println("99. Return to Main Menu                      |");
		System.out.println("=============================================");
		System.out.println();
	}

	// Print the Delivery Person Table
	private boolean printNewsagentTable(ResultSet rs) throws Exception {

		System.out.println();
		System.out.println("                 ---------------------------------------------");
		System.out.println("                         Table " + rs.getMetaData().getTableName(1) + " :");
		System.out.println();

		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

			System.out.printf("%20s", rs.getMetaData().getColumnName(i));

		}

		System.out.println();

		while (rs.next()) {

			int id = rs.getInt("id");
			String newsagentName = rs.getString("newsagent_name");
			String password = rs.getString("password");

			System.out.printf("%20s", id);
			System.out.printf("%20s", newsagentName);
			System.out.printf("%20s", password);

			System.out.println();
		}

		System.out.println("                 ---------------------------------------------");

		return true;
	}

	@Override
	public void execute() {
		try {
			NewsagentCredentialsCRUD connect = new NewsagentCredentialsCRUD();
			String functionNumber;
			boolean keepAppOpen = true;

			while (keepAppOpen) {
				newsagentFunctionalities();
				functionNumber = scanner.next();

				switch (functionNumber) {
				case "1":
					// Create New Newsagent Credentials
					scanner.nextLine();
					System.out.println("Enter Newsagent Name: ");
					String name = scanner.nextLine();
					scanner.nextLine();

					System.out.println("Enter Newsagent Password: ");
					String password = scanner.next();

					NewsagentCredentials newCredentials = new NewsagentCredentials(name, password);
					boolean credentialsCreated = connect.createNewsagentCredentials(newCredentials);

					if (credentialsCreated) {
						System.out.println("New Newsagent Credentials Created Successfully.");
					} else {
						System.out.println("ERROR: Failed to Create Newsagent Credentials.");
					}
					break;

				case "2":
					// View Newsagent Credentials
					ResultSet rSet = connect.readNewsagentCredentials();

					if (rSet == null) {
						System.out.println("No Newsagent Credentials Found");

					} else {

						boolean tablePrinted = printNewsagentTable(rSet);

						if (tablePrinted) {
							rSet.close();
						}
					}
					break;

				case "3":
					// Update Newsagent Credentials by ID
					System.out.print("Enter Newsagent Credentials ID to Update: ");
					int credentialsId = scanner.nextInt();

					System.out.print("Enter New Newsagent Name: ");
					String newName = scanner.next();
					scanner.nextLine();

					System.out.print("Enter New Newsagent Password: ");
					String newPassword = scanner.next();

					NewsagentCredentials updatedCredentials = new NewsagentCredentials(newName, newPassword);
					boolean credentialsUpdated = connect.updateNewsagentCredentialsId(credentialsId, updatedCredentials);

					if (credentialsUpdated) {
						System.out.println("Newsagent Credentials Updated Successfully.");
					} else {
						System.out.println("ERROR: Failed to Update Newsagent Credentials.");
					}
					break;

				case "4":
					// Delete a Newsagent by ID
					System.out.print("Enter Newsagent ID to Delete: ");
					int deleteNewsagentId = scanner.nextInt();
					boolean newsagentDeleted = connect.deleteNewsagentById(deleteNewsagentId);

					if (newsagentDeleted) {
						System.out.println("Newsagent Credentials Deleted Successfully.");

					} else {
						System.out.println("ERROR: Failed to Delete Newsagent Credentials.");

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
			e.printStackTrace();
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE: " + e.getMessage());
		}
	}
}
