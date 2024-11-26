package order_book_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class OrderBookAttributesTests {

	OrderBook orderBook = new OrderBook();

	// customerId tests
	@Test
	public void testValidCustomerId() throws EntitiesExceptionHandler {
		assertTrue(orderBook.validateCustomerId(123)); // Valid customerId
	}

	@Test
	public void testInvalidCustomerId_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validateCustomerId(-1); // Negative customerId
		});
	}

	@Test
	public void testInvalidCustomerId_Zero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validateCustomerId(0); // Zero customerId (invalid)
		});
	}

	// publicationId tests
	@Test
	public void testValidPublicationId() throws EntitiesExceptionHandler {
		assertTrue(orderBook.validatePublicationId(456)); // Valid publicationId
	}

	@Test
	public void testInvalidPublicationId_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validatePublicationId(-10); // Negative publicationId
		});
	}

	@Test
	public void testInvalidPublicationId_Zero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			orderBook.validatePublicationId(0); // Zero publicationId (invalid)
		});
	}

}
