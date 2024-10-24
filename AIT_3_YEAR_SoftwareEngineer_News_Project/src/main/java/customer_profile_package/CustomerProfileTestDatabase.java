package customer_profile_package;
import java.sql.ResultSet;

public class CustomerProfileTestDatabase {

	public static void main(String[] args) {

		try {

			// Instantiate the MySQLAccess class to establish the connection
			CustomerProfileCRUD connect = new CustomerProfileCRUD();

			// Test Insert Action
			CustomerProfile newCustomer = new CustomerProfile("John Doe", "123 Main St", "555-5555", "john.doe@example.com", 0);
			boolean insertSuccess = connect.createCustomerDetailsAccount(newCustomer);

			if (insertSuccess) {
				System.out.println("Customer inserted successfully!");
				System.out.println();

			} else {
				System.out.println("Failed to insert customer.");

			}

			// Test Read Action
			System.out.println("Reading all customer accounts...");
			ResultSet rs = connect.readAllCustomerAccounts();

			while (rs != null && rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Address: " + rs.getString("postcode") + ", Phone: " + rs.getString("phoneNumber") + ", Email: " + rs.getString("email") + ", Payment Status: " + rs.getInt("paymentstatus"));

			}

			// Test Update Action
			CustomerProfile updatedCustomer = new CustomerProfile("Maria Silva", "457 Elm St", "777-7777", "maria.silva@example.com", 0);
			boolean updateSuccess = connect.updateCustomerById(4, updatedCustomer);

			if (updateSuccess) {
				System.out.println();
				System.out.println("Customer updated successfully!");
				System.out.println();

			} else {
				System.out.println("Failed to update customer.");

			}

			// Test Delete Action
			boolean deleteSuccess = connect.deleteCustomerById(2);

			if (deleteSuccess) {
				System.out.println("Customer deleted successfully!");
				System.out.println();

			} else {
				System.out.println("Failed to delete customer.");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
