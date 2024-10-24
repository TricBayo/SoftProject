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

	// ----------------------- Constructor ------------------------ //

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

	void setName(String name) {
		this.name = name;
	}

	String getPostcode() {
		return this.postcode;
	}

	void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	String getPhoneNumber() {
		return this.phoneNumber;

	}

	void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	// ----------------- Parameters Validating Method ----------------- //

	public static void validateName(String name) throws EntitiesExceptionHandler {

		if (name.isBlank() || name.isEmpty())
			throw new EntitiesExceptionHandler("Customer Name NOT specified");

		else if (name.length() < 2)
			throw new EntitiesExceptionHandler("Customer Name does not meet minimum length requirements");

		else if (name.length() > 50)
			throw new EntitiesExceptionHandler("Customer Name does not exceeds maximum length requirements");

	}

	public static void validatePostcode(String postcode) throws EntitiesExceptionHandler {

		if (postcode.isBlank() || postcode.isEmpty())
			throw new EntitiesExceptionHandler("Customer Address NOT specified");

		else if (postcode.length() < 5)
			throw new EntitiesExceptionHandler("Customer Address does not meet minimum length requirements");

		else if (postcode.length() > 60)
			throw new EntitiesExceptionHandler("Customer Address does not exceeds maximum length requirements");

	}

	public static void validatePhoneNumber(String phoneNumber) throws EntitiesExceptionHandler {

		if (phoneNumber.isBlank() || phoneNumber.isEmpty())
			throw new EntitiesExceptionHandler("Customer PhoneNumber NOT specified");

		else if (phoneNumber.length() < 7)
			throw new EntitiesExceptionHandler("Customer PhoneNumber does not meet minimum length requirements");

		else if (phoneNumber.length() > 15)
			throw new EntitiesExceptionHandler("Customer PhoneNumber does not exceeds maximum length requirements");

	}

	public static void validateEmail(String email) throws EntitiesExceptionHandler {

		if (email.isBlank() || email.isEmpty())

			throw new EntitiesExceptionHandler("Customer Email NOT specified");

		else {

			// Regex to validate general email format
			String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

			// Compile Regex pattern
			Pattern pattern = Pattern.compile(emailRegex);
			Matcher matcher = pattern.matcher(email);

			// Check if the email matches the format
			if (!matcher.matches()) {

				throw new EntitiesExceptionHandler("Customer Email format NOT valid");

			}

		}

	}

	public static void validatePaymentStatus(int paymentStatus) throws EntitiesExceptionHandler {

		String paymentStatusString = String.valueOf(paymentStatus);

		if (paymentStatusString.isBlank() || paymentStatusString.isEmpty())

			throw new EntitiesExceptionHandler("Customer Payment Status NOT specified");

		else if (paymentStatus < 0)

			throw new EntitiesExceptionHandler("Customer Payment Status does not meet minimum length requirements");

	}

}
