package daily_summary_report_package;

import java.sql.ResultSet;
import java.util.Scanner;
import for_all_entities_package.CommandLinesExecution;

public class DailySummaryReportCommandLine implements CommandLinesExecution {

    Scanner scanner = new Scanner(System.in);

    // Display Menu Options for Daily Summary Report
    private void dailySummaryReportFunctionalities() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Please, choose ONE of the following options:");
        System.out.println("1. Generate Daily Summary Report");
        System.out.println("2. View All Daily Summary Reports");
        System.out.println("3. Update a Daily Summary Report by ID");
        System.out.println("4. Delete a Daily Summary Report by ID");
        System.out.println("99. Return to Main Menu");
        System.out.println("=============================================");
        System.out.println();
    }

    // Print the Daily Summary Report Table
    private boolean printDailySummaryReportTable(ResultSet rs) throws Exception {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Table: " + rs.getMetaData().getTableName(1));

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.printf("%20s", rs.getMetaData().getColumnName(i));
        }

        System.out.println();

        while (rs.next()) {
            int reportId = rs.getInt("id");
            String date = rs.getString("date");
            int stock = rs.getInt("stock");

            System.out.printf("%20s", reportId);
            System.out.printf("%20s", date);
            System.out.printf("%20s", stock);
            System.out.println();
        }

        System.out.println("--------------------------------------------------------------------");

        return true;
    }

    @Override
    public void execute() {
        try {
            DailySummaryReportCRUD connect = new DailySummaryReportCRUD();
            String functionNumber;
            boolean keepAppOpen = true;

            while (keepAppOpen) {
                dailySummaryReportFunctionalities();
                functionNumber = scanner.next();

                switch (functionNumber) {
                    case "1":
                        // Create (Generate) a New Daily Summary Report
                        System.out.print("Enter Date (dd/MM/yyyy): ");
                        String date = scanner.next();

                        System.out.print("Enter Stock Level: ");
                        int stock = scanner.nextInt();

                        DailySummaryReport report = new DailySummaryReport(date, stock);
                        boolean reportGenerated = connect.createDailySummaryReport(report);

                        if (reportGenerated) {
                            System.out.println("Daily Summary Report Generated.");
                        } else {
                            System.out.println("ERROR: Report Generation Failed.");
                        }
                        break;

                    case "2":
                        // View All Daily Summary Reports
                        ResultSet rSet = connect.readAllDailySummaryReport();

                        if (rSet == null) {
                            System.out.println("No Reports Found");
                        } else {
                            boolean tablePrinted = printDailySummaryReportTable(rSet);
                            if (tablePrinted) {
                                rSet.close();
                            }
                        }
                        break;

                    case "3":
                        // Update a Daily Summary Report by ID
                        System.out.print("Enter Report ID to Update: ");
                        int reportId = scanner.nextInt();

                        System.out.print("Enter New Date (dd/MM/yyyy): ");
                        String newDate = scanner.next();

                        System.out.print("Enter New Stock Level: ");
                        int newStock = scanner.nextInt();

                        DailySummaryReport updatedReport = new DailySummaryReport(newDate, newStock);
                        boolean reportUpdated = connect.updateDailySummaryReportById(reportId, updatedReport);

                        if (reportUpdated) {
                            System.out.println("Daily Summary Report Updated.");
                        } else {
                            System.out.println("ERROR: Report Update Failed.");
                        }
                        break;

                    case "4":
                        // Delete a Daily Summary Report by ID
                        System.out.print("Enter Report ID to Delete or -99 to Return: ");
                        String deleteReportId = scanner.next();

                        if (deleteReportId.equals("-99")) {
                            System.out.println("Returning to Main Menu...");
                        } else {
                            boolean reportDeleted = connect.deleteDailySummaryReportById(Integer.parseInt(deleteReportId));

                            if (reportDeleted) {
                                System.out.println("Daily Summary Report Deleted.");
                            } else {
                                System.out.println("ERROR: Report Not Deleted or Does Not Exist.");
                            }
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
