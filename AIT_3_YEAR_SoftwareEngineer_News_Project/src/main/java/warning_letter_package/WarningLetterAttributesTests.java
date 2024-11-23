package warning_letter_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class WarningLetterAttributesTests {

	WarningLetter warningLetter = new WarningLetter();

	// customerId tests
	@Test
	public void testValidCustomerId() throws EntitiesExceptionHandler {
		assertTrue(warningLetter.validateCustomerId(123)); // Valid customerId
	}

	@Test
	public void testInvalidCustomerId_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validateCustomerId(-1); // Negative customerId
		});
	}

	@Test
	public void testInvalidCustomerId_Zero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validateCustomerId(0); // Zero customerId (invalid)
		});
	}

	// delivery area id
	@Test
	public void testValidAreaId() throws EntitiesExceptionHandler {
		assertTrue(warningLetter.validateAreaId(12)); // Valid Area Id
	}

	@Test
	public void testInvalidAreaIdZero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validateAreaId(0); // Area Id is zero
		});
	}

	@Test
	public void testInvalidAreaIdLessThanZero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validateAreaId(-1); // Area Id less than zero
		});
	}

	@Test
	public void testInvalidAreaIdGreaterThan24() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validateAreaId(25); // Area Id greater than 24
		});
	}

}
