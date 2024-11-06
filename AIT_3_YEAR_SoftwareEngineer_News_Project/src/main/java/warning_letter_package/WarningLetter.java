package warning_letter_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class WarningLetter {

	private String name;
	private String postcode;
	private double amountInDebt;
	private int paymentStatus;

	// ----------------------- Constructors ------------------------ //

	public WarningLetter() {

		// No-argument constructor
	}

	public WarningLetter(String name, String postcode, double amountInDebt, int paymentStatus) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateName(name);
			validatePostcode(postcode);
			validateAmountInDebt(amountInDebt);
			validatePaymentStatus(paymentStatus);

		} catch (EntitiesExceptionHandler e) {
			throw e;

		}

		this.name = name;
		this.postcode = postcode;
		this.amountInDebt = amountInDebt;
		this.paymentStatus = paymentStatus;
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

	public double getAmountInDebt() {
		return amountInDebt;

	}

	public void setAmountInDebt(double amountInDebt) throws EntitiesExceptionHandler {
		validateAmountInDebt(amountInDebt);
		this.amountInDebt = amountInDebt;

	}

	public int getPaymentStatus() {
		return paymentStatus;

	}

	public void setPaymentStatus(int paymentStatus) throws EntitiesExceptionHandler {
		validatePaymentStatus(paymentStatus);
		this.paymentStatus = paymentStatus;

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

	public boolean validateAmountInDebt(double amountInDebt) throws EntitiesExceptionHandler {

		boolean result = false;

		if (amountInDebt < 0) {
			throw new EntitiesExceptionHandler("Amount in debt must be a non-negative value");

		} else {
			result = true;

		}
		return result;
	}

	public boolean validatePaymentStatus(int paymentStatus) throws EntitiesExceptionHandler {

		boolean result = true;

		if (paymentStatus < 0) {
			result = false;
			throw new EntitiesExceptionHandler("Payment Status must be a non-negative integer");

		} else if (paymentStatus < 3) {
			System.out.println("Less than three months in debt: " + paymentStatus);

		} else if (paymentStatus >= 3) {
			System.out.println("More than or Equal three months in debt: " + paymentStatus);
			System.out.println("Warning Letter Issued to Customer.");

		}
		return result;

	}
}
