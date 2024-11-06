package monthly_invoice_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class MonthlyInvoiceAttributesTests {

	MonthlyInvoice monthlyInvoice = new MonthlyInvoice();

	// invoice amount
	@Test
	public void testValidInvoiceAmount() throws EntitiesExceptionHandler {
		assertTrue(monthlyInvoice.validateInvoiceAmount("150.50")); // Valid double value
	}

	@Test
	public void testInvalidInvoiceAmount_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validateInvoiceAmount("-50.75"); // Invalid: Negative amount
		});
	}

	// name customer
	@Test
	public void testValidName() throws EntitiesExceptionHandler {
		assertTrue(monthlyInvoice.validateName("John Doe")); // Valid customer name
	}

	@Test
	public void testInvalidName_TooShort() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validateName("J"); // Name too short
		});
	}

	@Test
	public void testInvalidName_TooLong() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validateName("ThisNameIsWayTooLongForAValidNameThisNameIsWayTooLongForAValidName"); // Name too long
		});
	}

	// postcode
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(monthlyInvoice.validatePostcode("N37AO24")); // Valid postcode
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validatePostcode("AO24 N37"); // Incorrect format
		});
	}

	// invoice deadline
	@Test
	public void testValidInvoiceDeadline() throws EntitiesExceptionHandler {
		LocalDate invoiceDate = LocalDate.now();
		LocalDate expectedDeadline = invoiceDate.plusDays(30);
		String formattedDeadline = expectedDeadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		assertTrue(monthlyInvoice.validatePaymentDate(formattedDeadline)); // Valid deadline
	}

	@Test
	public void testInvalidInvoiceDeadline_WrongFormat() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validatePaymentDate("2024-10-31"); // Incorrect format
		});
	}

	// invoice amount with invalid data type
	@Test
	public void testInvalidInvoiceAmount_NotANumber() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validateInvoiceAmount("ABC"); // Invalid input: Not a number
		});
	}

	// invalid deadline with letters in date
	@Test
	public void testInvalidInvoiceDeadline_ContainsLetters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validatePaymentDate("31/Oct/2024");
		});
	}
}
