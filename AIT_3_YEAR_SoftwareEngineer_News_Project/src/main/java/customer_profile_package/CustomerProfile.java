package customer_profile_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class CustomerProfile {

	private String name;
	private String postcode;
	private String phoneNumber;
	private String email;
	private int paymentStatus;

	// ----------------------- Constructors ------------------------ //

	public CustomerProfile() {
		// No-argument constructor
	}

	public CustomerProfile(String name, String phoneNumber, String postcode, String email, int paymentStatus) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateName(name);
			validatePostcode(postcode);
			validatePhoneNumber(phoneNumber);
			validateEmail(email);
			validatePaymentStatus(paymentStatus);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.name = name;
		this.postcode = postcode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.paymentStatus = paymentStatus;

	}

	// ----------------------- Getter and Setters ------------------------ //

	String getName() {

		return this.name;
	}

	void setName(String name) throws EntitiesExceptionHandler {

		validateName(name);
		this.name = name;
	}

	String getPostcode() {

		return this.postcode;
	}

	void setPostcode(String postcode) throws EntitiesExceptionHandler {

		validatePostcode(postcode);
		this.postcode = postcode;
	}

	String getPhoneNumber() {

		return this.phoneNumber;
	}

	void setPhoneNumber(String phoneNumber) throws EntitiesExceptionHandler {

		validatePhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {

		return this.email;
	}

	public void setEmail(String email) throws EntitiesExceptionHandler {

		validateEmail(email);
		this.email = email;
	}

	public int getPaymentStatus() {

		return this.paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) throws EntitiesExceptionHandler {

		validatePaymentStatusWithoutDisplayMessage(paymentStatus);
		this.paymentStatus = paymentStatus;
	}

	// ----------------- Attributes Validating Method ----------------- //

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

		// Regex Pattern
		String postCodeRegex = "^[A-Za-z]{1}\\d{2}[A-Za-z]{2}\\d{2}$";
		Pattern pattern = Pattern.compile(postCodeRegex);

		// Validate if the input is null or empty
		if (postCode == null || postCode.isBlank()) {
			throw new EntitiesExceptionHandler("Postcode NOT specified");
		}

		// Remove any spaces from the input
		postCode = postCode.replaceAll("\\s", "");

		// Match against the regex
		Matcher matcher = pattern.matcher(postCode);

		if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Postcode format NOT valid. Expected format: A11XX22");
		} else {
			result = true;
		}

		return result;
	}

	public boolean validatePhoneNumber(String phoneNumber) throws EntitiesExceptionHandler {
		boolean result = false;

		// Regex Pattern to match international phone numbers with or without spaces
		String phoneRegex = "^\\+\\d{1,3}(\\s?\\d{2,3})?(\\s?\\d{3})?(\\s?\\d{4})$";
		Pattern pattern = Pattern.compile(phoneRegex);

		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new EntitiesExceptionHandler("Customer PhoneNumber NOT specified");
		}

		// Trim and normalize whitespace
		String trimmedPhoneNumber = phoneNumber.trim();
		String normalizedPhoneNumber = trimmedPhoneNumber.replaceAll("\\s+", "");

		if (normalizedPhoneNumber.length() < 7) {
			throw new EntitiesExceptionHandler("Customer PhoneNumber does not meet minimum length requirements");

		} else if (normalizedPhoneNumber.length() > 20) {
			throw new EntitiesExceptionHandler("Customer PhoneNumber exceeds maximum length requirements");
		}

		Matcher matcher = pattern.matcher(trimmedPhoneNumber);

		if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Customer PhoneNumber format NOT valid. Expected formats: +XXX XX XXX XXXX or +XXXXXXXXXXXX");
		}

		result = true;

		return result;
	}

	public boolean validateEmail(String email) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to validate general email format
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);

		if (email.isBlank() || email.isEmpty()) {

			throw new EntitiesExceptionHandler("Customer Email NOT specified");
		}
		// Check if the email matches the format
		else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Customer Email format NOT valid");

		} else {

			result = true;
		}

		return result;

	}

	public boolean validatePaymentStatus(int paymentStatus) throws EntitiesExceptionHandler {

		boolean result = false;

		String paymentStatusString = String.valueOf(paymentStatus);

		if (paymentStatusString.isBlank() || paymentStatusString.isEmpty())
			throw new EntitiesExceptionHandler("Customer Payment Status NOT specified");

		else if (paymentStatus < 0)
			throw new EntitiesExceptionHandler("Customer Payment Status cannot be less than zero");

		else {

			if (paymentStatus >= 0 && paymentStatus < 3) {
				System.out.println();
				System.out.println("Payment Status Level: Green => " + paymentStatus + " Months in Debt");
				System.out.println("Warning Letter Debt Alert = 0.");

			} else if (paymentStatus >= 3) {
				System.out.println();
				System.out.println("Payment Status Attention Level: RED => " + paymentStatus + " Months in Debt");
				System.out.println("Warning Letter Debt = 1.");

			}

			result = true;
		}

		return result;

	}

	public boolean validatePaymentStatusWithoutDisplayMessage(int paymentStatusSet) throws EntitiesExceptionHandler {

		boolean result = false;

		String paymentStatusString = String.valueOf(paymentStatus);

		if (paymentStatusString.isBlank() || paymentStatusString.isEmpty())
			throw new EntitiesExceptionHandler("Customer Payment Status NOT specified");

		else if (paymentStatus < 0)
			throw new EntitiesExceptionHandler("Customer Payment Status cannot be less than zero");

		else {

			result = true;
		}

		return result;

	}
}
