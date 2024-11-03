package daily_summary_report_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class DailySummaryReportAttributesTests {

	DailySummaryReport summaryReport = new DailySummaryReport();

	// stock amount
	@Test
	public void testValidStockAmount() throws EntitiesExceptionHandler {
		assertTrue(summaryReport.validateStockAmount(50)); // Valid stock amount (positive number)
	}

	@Test
	public void testInvalidStockAmount_Negative() {
		assertThrows(RuntimeException.class, () -> {
			summaryReport.validateStockAmount(-10); // Invalid: Negative stock amount
		});
	}

	@Test
	public void testInvalidStockAmount_LessThanFour() {
		assertThrows(RuntimeException.class, () -> {
			summaryReport.validateStockAmount(3); // Invalid: Stock is low, less than 4
		});
	}

	@Test
	public void testInvalidStockAmount_Zero() {
		assertThrows(RuntimeException.class, () -> {
			summaryReport.validateStockAmount(0); // Invalid: Stock is zero
		});
	}

	// report date
	@Test
	public void testValidReportDate() throws EntitiesExceptionHandler {
		assertTrue(summaryReport.validateDate("25/12/2025")); // Valid date in DD/MM/YYYY format
	}

	@Test
	public void testInvalidReportDate_WrongFormat() {
		assertThrows(DateTimeParseException.class, () -> {
			summaryReport.validateDate("2025/12/25"); // Invalid: Wrong format (YYYY/MM/DD)
		});
	}

	@Test
	public void testInvalidReportDate_InvalidDay() {
		assertThrows(RuntimeException.class, () -> {
			summaryReport.validateDate("32/12/2025"); // Invalid: Day greater than 31
		});
	}

	@Test
	public void testInvalidReportDate_InvalidMonth() {
		assertThrows(RuntimeException.class, () -> {
			summaryReport.validateDate("25/13/2025"); // Invalid: Month greater than 12
		});
	}

	@Test
	public void testInvalidReportDate_ContainsLetters() {
		assertThrows(DateTimeParseException.class, () -> {
			summaryReport.validateDate("25/Dec/2025"); // Invalid: Date contains letters
		});
	}

}
