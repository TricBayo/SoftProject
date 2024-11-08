package news_agent_package;

import java.util.Scanner;
import for_all_entities_package.CommandLinesExecution;

public class NewsagentCommandLine implements CommandLinesExecution {

    Scanner scanner = new Scanner(System.in);

    // Display Menu Options for Newsagent Credentials
    private void newsagentFunctionalities() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Please, choose ONE of the following options:");
        System.out.println("1. Create New Newsagent Credentials");
        System.out.println("2. Update Newsagent Credentials by ID");
        System.out.println("99. Return to Main Menu");
        System.out.println("=============================================");
        System.out.println();
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
                        System.out.print("Enter Newsagent Name: ");
                        String name = scanner.next();

                        System.out.print("Enter Newsagent Password: ");
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
                        // Update Newsagent Credentials by ID
                        System.out.print("Enter Newsagent Credentials ID to Update: ");
                        int credentialsId = scanner.nextInt();

                        System.out.print("Enter New Newsagent Name: ");
                        String newName = scanner.next();

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
