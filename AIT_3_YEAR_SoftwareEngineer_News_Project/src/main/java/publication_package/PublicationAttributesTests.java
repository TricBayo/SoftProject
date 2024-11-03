package publication_package;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class PublicationAttributesTests {

	Publication publication = new Publication();

	// publication name
	@Test
	public void testValidPublicationName() throws EntitiesExceptionHandler {
		assertTrue(publication.validatePublicationName("Christmas Edition")); // Valid customer name (matches profile)
	}

	@Test
	public void testInvalidPublicationName_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			publication.validatePublicationName("C"); // Name too short
		});
	}

	@Test
	public void testInvalidPublicationName_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			publication.validatePublicationName("ThisIsWayTooLongForAValidPublicationName"); // Name too long
		});
	}

	// edition date
	public void testValidEditionDate() throws EntitiesExceptionHandler {
		assertTrue(publication.validatePublicationDate("25/12/2025")); // Valid date in DD/MM/YYYY format
	}

	@Test
	public void testInvalidEditionDate_WrongFormat() {
		assertThrows(DateTimeParseException.class, () -> {
			publication.validatePublicationDate("2025/12/25"); // Invalid: Wrong format
		});
	}

	@Test
	public void testInvalidEditionDate_InvalidDay() {
		assertThrows(RuntimeException.class, () -> {
			publication.validatePublicationDate("32/12/2025"); // Invalid: Day greater than 31
		});
	}

	@Test
	public void testInvalidEditionDate_InvalidMonth() {
		assertThrows(RuntimeException.class, () -> {
			publication.validatePublicationDate("25/13/2025"); // Invalid: Month greater than 12
		});
	}

	@Test
	public void testInvalidEditionDate_ContainsLetters() {
		assertThrows(DateTimeParseException.class, () -> {
			publication.validatePublicationDate("25/Dec/2025");
		});
	}

	// stock balance
	@Test
	public void testValidStockBalance() throws EntitiesExceptionHandler {
		assertTrue(publication.validateStockBalance(50)); // Valid stock balance (>= 0)
	}

	@Test
	public void testInvalidStockBalance_Negative() {
		assertThrows(RuntimeException.class, () -> {
			publication.validateStockBalance(-1); // Invalid: Negative stock balance
		});
	}

	@Test
	public void testIncrementValidStock() throws EntitiesExceptionHandler {
		assertTrue(publication.validateStockBalance(3)); // Valid: Stock incremented by 1

	}

	@Test
	public void testDecrementValidStock() throws EntitiesExceptionHandler {
		assertTrue(publication.validateStockBalance(50)); // Valid: Stock decremented by 1

	}

	@Test
	public void testInvalidStockBalance_ShowsErrorMessage() {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			publication.validateStockBalance(-5); // Invalid stock balance
		});
		assertEquals("Invalid stock balance", exception.getMessage()); // Validate the correct error message
	}

	// publication price
	@Test
	public void testValidPublicationPrice() throws EntitiesExceptionHandler {
		assertTrue(publication.validatePublicationPrice(12.25)); // Valid customer name (matches profile)

	}

	@Test
	public void invalidPublicationPrice() {

		assertThrows(RuntimeException.class, () -> {
			publication.validatePublicationPrice(-12.0); // Name too short

		});

	}

}
