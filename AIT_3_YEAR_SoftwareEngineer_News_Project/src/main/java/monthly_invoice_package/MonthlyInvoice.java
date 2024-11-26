package monthly_invoice_package;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class MonthlyInvoice {

	private String amountToPay;
	private String paymentDate;

	private int customerId;

	// ----------------------- Constructors ------------------------ //

	public MonthlyInvoice() {

		// No-argument constructor

	}

	public MonthlyInvoice(int customer_Id, String paymentDate, String amountToPay) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validatePaymentDate(paymentDate);
			validateInvoiceAmountToPay(amountToPay);
			validateCustomerId(customer_Id);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.paymentDate = paymentDate;
		this.amountToPay = amountToPay;
		this.customerId = customer_Id;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public int getCustomerId() {

		return customerId;
	}

	public void setCustomerId(int customerId) throws EntitiesExceptionHandler {

		validateCustomerId(customerId);
		this.customerId = customerId;
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

		validateInvoiceAmountToPay(amountToPay);
		this.amountToPay = amountToPay;
	}

	// ----------------- Attributes Validating Methods ----------------- //
	public boolean validateCustomerId(int customerId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (customerId <= 0) {
			throw new EntitiesExceptionHandler("Customer ID must be greater than 0");

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
			throw new EntitiesExceptionHandler("Report Date format NOT valid. Expected format: DD/MM/YYYY");
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

	public boolean validateInvoiceAmountToPay(String amountToPay) throws EntitiesExceptionHandler {

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
