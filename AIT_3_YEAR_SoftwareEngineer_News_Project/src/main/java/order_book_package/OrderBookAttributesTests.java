package order_book_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class OrderBookAttributesTests {

	OrderBook orderBook = new OrderBook();

	// customer name
	@Test
	public void testValidCustomerName() throws EntitiesExceptionHandler {
		assertTrue(orderBook.validateCustomerName("John Doe")); // Valid customer name (matches profile)
	}

	@Test
	public void testInvalidCustomerName_MismatchWithProfile() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validateCustomerName("Jane Doe"); // Name does not match profile
		});
	}

	@Test
	public void testInvalidCustomerName_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validateCustomerName("Jo"); // Name too short
		});
	}

	@Test
	public void testInvalidCustomerName_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validateCustomerName("ThisIsWayTooLongForAValidCustomerName"); // Name too long
		});
	}

	// postcode
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(orderBook.validatePostcode("N37 AO24")); // Valid postcode
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validatePostcode("AO24 N37"); // Incorrect format
		});
	}

	// publication name
	@Test
	public void testValidPublicationName() throws EntitiesExceptionHandler {
		assertTrue(orderBook.validatePublicationName("The Daily News")); // Valid publication name
	}

	@Test
	public void testInvalidPublicationName_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validatePublicationName("DN"); // Name too short
		});
	}

	@Test
	public void testInvalidPublicationName_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validatePublicationName("ThisPublicationNameIsWayTooLongToBeValid"); // Name too long
		});
	}

	// extra tests

	// special characters in Customer Name
	@Test
	public void testInvalidCustomerName_SpecialCharacters() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validateCustomerName("John@Doe");
		});
	}

	// publication Name with special characters
	@Test
	public void testInvalidPublicationName_SpecialCharacters() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validatePublicationName("The Daily News@");
		});
	}

	// empty Customer Name
	@Test
	public void testInvalidCustomerName_Empty() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validateCustomerName("");
		});
	}

	// empty Publication Name
	@Test
	public void testInvalidPublicationName_Empty() {
		assertThrows(RuntimeException.class, () -> {
			orderBook.validatePublicationName("");
		});
	}
}
