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

	public CustomerProfile(String name, String postcode, String phoneNumber, String email, int paymentStatus) throws EntitiesExceptionHandler {

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

		validatePaymentStatus(paymentStatus);
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

	public boolean validatePhoneNumber(String phoneNumber) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex pattern to match international phone numbers with or without spaces
		String phoneRegex = "^\\+\\d{1,3}( ?\\d{2,3})?( ?\\d{3})?( ?\\d{4})$";
		Pattern pattern = Pattern.compile(phoneRegex);
		Matcher matcher = pattern.matcher(phoneNumber);

		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new EntitiesExceptionHandler("Customer PhoneNumber NOT specified");

		} else if (phoneNumber.length() < 7) {
			throw new EntitiesExceptionHandler("Customer PhoneNumber does not meet minimum length requirements");

		} else if (phoneNumber.length() > 20) {
			throw new EntitiesExceptionHandler("Customer PhoneNumber exceeds maximum length requirements");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Customer PhoneNumber format NOT valid. Expected formats: +XXX XX XXX XXXX or +XXXXXXXXXXXX");

		} else {
			result = true;
		}

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
			throw new EntitiesExceptionHandler("Customer Payment Status does not meet minimum length requirements");

		else if (paymentStatus > 3)
			throw new EntitiesExceptionHandler("Customer Payment Status indicates debt over three months");

		else {
			result = true;
		}

		return result;

	}
}
