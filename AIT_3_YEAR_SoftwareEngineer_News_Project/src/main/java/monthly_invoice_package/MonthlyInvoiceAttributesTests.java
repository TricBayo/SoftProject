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
		assertTrue(monthlyInvoice.validateInvoiceAmountToPay("150.50")); // Valid double value
	}

	@Test
	public void testInvalidInvoiceAmount_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validateInvoiceAmountToPay("-50.75"); // Invalid: Negative amount
		});
	}

	// invoice payment date
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
	public void testInvalidInvoiceAmount_NotNumber() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validateInvoiceAmountToPay("ABC"); // Invalid input: Not a number
		});
	}

	// invalid deadline with letters in date
	@Test
	public void testInvalidInvoiceDeadline_ContainsLetters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validatePaymentDate("31/Oct/2024");
		});
	}

	// customerId tests
	@Test
	public void testValidCustomerId() throws EntitiesExceptionHandler {
		assertTrue(monthlyInvoice.validateCustomerId(127)); // Valid customerId
	}

	@Test
	public void testInvalidCustomerId_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validateCustomerId(-1); // Negative customerId
		});
	}

	@Test
	public void testInvalidCustomerId_Zero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			monthlyInvoice.validateCustomerId(0); // Zero customerId (invalid)
		});
	}
}
