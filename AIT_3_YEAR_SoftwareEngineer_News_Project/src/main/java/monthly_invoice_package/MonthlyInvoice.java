package monthly_invoice_package;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class MonthlyInvoice {

	private String name;
	private String postcode;
	private String paymentDate;
	private String amountToPay;

	// ----------------------- Constructors ------------------------ //

	public MonthlyInvoice() {

		// No-argument constructor

	}

	public MonthlyInvoice(String name, String postcode, String paymentDate, String amountToPay) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateName(name);
			validatePostcode(postcode);
			validatePaymentDate(paymentDate);
			validateInvoiceAmount(amountToPay);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.name = name;
		this.postcode = postcode;
		this.paymentDate = paymentDate;
		this.amountToPay = amountToPay;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getName() {

		return name;
	}

	public void setName(String name) throws EntitiesExceptionHandler {

		validateName(name);
		this.name = name;
	}

	public String getPostcode() {

		return postcode;
	}

	public void setPostcode(String postcode) throws EntitiesExceptionHandler {

		validatePostcode(postcode);
		this.postcode = postcode;
	}

	public String getPaymentDate() {

		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) throws EntitiesExceptionHandler {

		validatePaymentDate(paymentDate);
		this.paymentDate = paymentDate;
	}

	public String getAmountToPay() {

		return amountToPay;
	}

	public void setAmountToPay(String amountToPay) throws EntitiesExceptionHandler {

		validateInvoiceAmount(amountToPay);
		this.amountToPay = amountToPay;
	}

	// ----------------- Attributes Validating Methods ----------------- //

	public boolean validateName(String name) throws EntitiesExceptionHandler {

		boolean result = false;

		if (name == null || name.isBlank()) {
			throw new EntitiesExceptionHandler("Customer Name NOT specified");

		} else if (name.length() < 2) {
			throw new EntitiesExceptionHandler("Customer Name does not meet minimum length requirements");

		} else if (name.length() > 50) {
			throw new EntitiesExceptionHandler("Customer Name exceeds maximum length requirements");

		} else if (!name.matches("[a-zA-Z ]+")) { // Only allows letters and spaces
			throw new EntitiesExceptionHandler("Customer Name contains invalid characters");

		} else {
			result = true;
		}

		return result;
	}

	public boolean validatePostcode(String postCode) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex for the format: one letter, two digits, two letters, two digits
		String postCodeRegex = "^[A-Z]{1}\\d{2}[A-Z]{2}\\d{2}$";
		Pattern pattern = Pattern.compile(postCodeRegex);
		Matcher matcher = pattern.matcher(postCode);

		if (postCode == null || postCode.isBlank()) {
			throw new EntitiesExceptionHandler("Postcode NOT specified");

		} else if (postCode.contains(" ")) {
			throw new EntitiesExceptionHandler("Postcode must not contain spaces");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Postcode format NOT valid. Expected format: A11XX22");

		} else {
			result = true;
		}

		return result;
	}

	public boolean validatePaymentDate(String date) throws EntitiesExceptionHandler {
		// Regex to validate date format as DD/MM/YYYY
		String dateRegex = "^\\d{2}/\\d{2}/\\d{4}$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(date);

		if (date == null || date.isBlank()) {
			throw new EntitiesExceptionHandler("Report Date NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Report Date format NOT valid. Expected format: DD/MM/YY");
		}

		// Further validate logical correctness of the date
		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(date, formatter); // This will throw an exception if the date is invalid

		} catch (DateTimeParseException e) {

			throw new EntitiesExceptionHandler("Report Date is not a valid date.");
		}

		return true; // Return true if the date is valid
	}

	public boolean validateInvoiceAmount(String amountToPay) throws EntitiesExceptionHandler {

		boolean result = false;
		double amount;

		try {

			amount = Double.parseDouble(amountToPay);

		} catch (NumberFormatException e) {

			throw new EntitiesExceptionHandler("Amount to Pay must be a valid number");

		}

		if (amount < 0) {

			throw new EntitiesExceptionHandler("Amount to Pay must be a non-negative value");

		} else {

			result = true;

		}

		return result;
	}

}
