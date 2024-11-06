package warning_letter_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class WarningLetterAttributesTests {

	WarningLetter warningLetter = new WarningLetter();

	// amount in debt
	@Test
	public void testValidAmountInDebt() throws EntitiesExceptionHandler {
		assertTrue(warningLetter.validateAmountInDebt(100.50)); // Valid amount in debt (double)
	}

	@Test
	public void testInvalidAmountInDebt_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validateAmountInDebt(-10.00); // Invalid: Negative amount in debt
		});
	}

	// customer name
	@Test
	public void testValidName() throws EntitiesExceptionHandler {
		assertTrue(warningLetter.validateName("John Doe")); // Valid name (3 to 20 characters)
	}

	@Test
	public void testInvalidName_TooShort() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validateName("J"); // Invalid: Too short
		});
	}

	@Test
	public void testInvalidName_TooLong() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validateName("ThisNameIsWayTooLongToBeValidForACustomerProfileThisNameIsWayTooLongToBeValidForACustomerProfile"); // Invalid: Too long
		});
	}

	// postcode
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(warningLetter.validatePostcode("N37AO24")); // Valid postcode format
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validatePostcode("N3724AO"); // Invalid: Wrong format
		});
	}

	// monthly payment status
	@Test
	public void testValidMonthlyPaymentStatusLessThanThree() throws EntitiesExceptionHandler {
		assertTrue(warningLetter.validatePaymentStatus(2)); // Valid payment status (less than 3 months delayed)
	}

	@Test
	public void testValidMonthlyPaymentStatusGreaterEqualThree() throws EntitiesExceptionHandler {
		assertTrue(warningLetter.validatePaymentStatus(3)); // Valid payment status (greater or equal 3 months delayed)

	}

	@Test
	public void testValidMonthlyPaymentStatusLessThanZero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			warningLetter.validatePaymentStatus(-1); // Invalid Payment Status (less than zero)
		});
	}

}
