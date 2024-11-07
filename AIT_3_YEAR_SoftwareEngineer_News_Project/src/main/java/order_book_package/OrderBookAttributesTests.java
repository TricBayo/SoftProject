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
	public void testInvalidCustomerName_TooShort() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validateCustomerName("J"); // Name too short
		});
	}

	@Test
	public void testInvalidCustomerName_TooLong() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validateCustomerName("ThisIsWayTooLongForAValidCustomerNameThisIsWayTooLongForAValidCustomerName"); // Name too long
		});
	}

	// postcode
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(orderBook.validatePostcode("N37AO24")); // Valid postcode
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
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
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validatePublicationName("J"); // Name too short
		});
	}

	@Test
	public void testInvalidPublicationName_TooLong() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validatePublicationName("ThisPublicationNameIsWayTooLongToBeValidThisPublicationNameIsWayTooLongToBeValid"); // Name too long
		});
	}

	// extra tests

	// special characters in Customer Name
	@Test
	public void testInvalidCustomerName_SpecialCharacters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validateCustomerName("John@Doe");
		});
	}

	// publication Name with special characters
	@Test
	public void testInvalidPublicationName_SpecialCharacters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validatePublicationName("The Daily News@");
		});
	}

	// empty Customer Name
	@Test
	public void testInvalidCustomerName_Empty() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validateCustomerName("");
		});
	}

	// empty Publication Name
	@Test
	public void testInvalidPublicationName_Empty() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validatePublicationName("");
		});
	}
}
