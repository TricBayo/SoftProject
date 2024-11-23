package daily_summary_report_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class DailySummaryReportAttributesTests {

	DailySummaryReport summaryReport = new DailySummaryReport();

	// report date
	@Test
	public void testValidReportDate() throws EntitiesExceptionHandler {
		assertTrue(summaryReport.validateDate("25/12/2025")); // Valid date in DD/MM/YYYY format
	}

	@Test
	public void testInvalidReportDate_WrongFormat() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			summaryReport.validateDate("2025/12/25"); // Invalid: Wrong format (YYYY/MM/DD)
		});
	}

	@Test
	public void testInvalidReportDate_InvalidDay() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			summaryReport.validateDate("32/12/2025"); // Invalid: Day greater than 31
		});
	}

	@Test
	public void testInvalidReportDate_InvalidMonth() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			summaryReport.validateDate("25/13/2025"); // Invalid: Month greater than 12
		});
	}

	@Test
	public void testInvalidReportDate_ContainsLetters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			summaryReport.validateDate("25/Dec/2025"); // Invalid: Date contains letters
		});
	}

	// customerId tests
	@Test
	public void testValidCustomerId() throws EntitiesExceptionHandler {
		assertTrue(summaryReport.validateCustomerId(127)); // Valid customerId
	}

	@Test
	public void testInvalidCustomerId_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			summaryReport.validateCustomerId(-1); // Negative customerId
		});
	}

	@Test
	public void testInvalidCustomerId_Zero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			summaryReport.validateCustomerId(0); // Zero customerId (invalid)
		});
	}

	// publicationId tests
	@Test
	public void testValidPublicationId() throws EntitiesExceptionHandler {
		assertTrue(summaryReport.validatePublicationId(457)); // Valid publicationId
	}

	@Test
	public void testInvalidPublicationId_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			summaryReport.validatePublicationId(-10); // Negative publicationId
		});
	}

	@Test
	public void testInvalidPublicationId_Zero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			summaryReport.validatePublicationId(0); // Zero publicationId (invalid)
		});
	}

}
