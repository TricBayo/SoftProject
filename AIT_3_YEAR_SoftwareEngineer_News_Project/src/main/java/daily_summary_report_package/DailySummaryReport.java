package daily_summary_report_package;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class DailySummaryReport {

	private String date;

	private int customerId;
	private int publicationId;
	private int deliveryDocketId;

	// ----------------------- Constructors ------------------------ //

	public DailySummaryReport() {

		// No-argument constructor
	}

	public DailySummaryReport(String date, int customerId, int publicationId, int deliveryDocketId) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateDate(date);
			validateCustomerId(customerId);
			validatePublicationId(publicationId);
			validateDeliveryDocketId(deliveryDocketId);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.date = date;
		this.customerId = customerId;
		this.publicationId = publicationId;
		this.deliveryDocketId = deliveryDocketId;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public int getDeliveryDocketId() {
		return deliveryDocketId;
	}

	public void setDeliveryDocketId(int deliveryDocketId) {
		this.deliveryDocketId = deliveryDocketId;
	}

	public String getDate() {

		return date;
	}

	public void setDate(String date) throws EntitiesExceptionHandler {

		validateDate(date);
		this.date = date;
	}

	// ----------------- Attributes Validating Method ----------------- //

	public boolean validateCustomerId(int customerId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (customerId <= 0) {
			throw new EntitiesExceptionHandler("Customer ID must be greater than 0");

		} else {

			result = true;
		}

		return result;
	}

	public boolean validatePublicationId(int publicationId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (publicationId <= 0) {
			throw new EntitiesExceptionHandler("Publication ID must be greater than 0");

		} else {

			result = true;
		}

		return result;
	}

	public boolean validateDeliveryDocketId(int deliveryDocketId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (deliveryDocketId <= 0) {
			throw new EntitiesExceptionHandler("Delivery Docket ID must be greater than 0");

		} else {

			result = true;
		}

		return result;
	}

	public boolean validateDate(String date) throws EntitiesExceptionHandler {
		// Regex to validate date format as DD/MM/YYYY
		String dateRegex = "^\\d{2}/\\d{2}/\\d{4}$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(date);

		if (date == null || date.isBlank()) {
			throw new EntitiesExceptionHandler("Report Date NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Report Date format NOT valid. Expected format: DD/MM/YYYY");
		}

		// Further validate logical correctness of the date
		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(date, formatter);

		} catch (DateTimeParseException e) {

			throw new EntitiesExceptionHandler("Report Date is not a valid date.");
		}

		return true; // Return true if the date is valid
	}

}
