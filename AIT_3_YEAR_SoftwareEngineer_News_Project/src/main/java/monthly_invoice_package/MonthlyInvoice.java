package monthly_invoice_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class MonthlyInvoice {

	private String name;
	private String postcode;
	private String paymentDate;
	private double amountToPay;

	// ----------------------- Constructors ------------------------ //

	public MonthlyInvoice() {

		// No-argument constructor

	}

	public MonthlyInvoice(String name, String postcode, String paymentDate, double amountToPay) throws EntitiesExceptionHandler {

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

	public double getAmountToPay() {

		return amountToPay;
	}

	public void setAmountToPay(double amountToPay) throws EntitiesExceptionHandler {

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

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Postcode format NOT valid. Expected format: A11XX22");

		} else {
			result = true;

		}

		return result;
	}

	public boolean validatePaymentDate(String paymentDate) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to validate payment date in format YYYY-MM-DD
		String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(paymentDate);

		if (paymentDate == null || paymentDate.isBlank()) {
			throw new EntitiesExceptionHandler("Payment Date NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Payment Date format NOT valid. Expected format: YYYY-MM-DD");

		} else {
			result = true;

		}

		return result;

	}

	public boolean validateInvoiceAmount(double amountToPay) throws EntitiesExceptionHandler {

		boolean result = false;

		if (amountToPay < 0) {
			throw new EntitiesExceptionHandler("Amount to Pay must be a non-negative value");

		} else {
			result = true;

		}

		return result;
	}
}
