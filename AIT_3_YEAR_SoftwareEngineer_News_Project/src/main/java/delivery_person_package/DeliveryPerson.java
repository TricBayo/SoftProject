package delivery_person_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryPerson {

	private String name;
	private String phoneNumber;
	private String areaId;
	private String postcode;
	private String password;

	// ----------------------- Constructors ------------------------ //

	public DeliveryPerson() {

		// No-argument constructor

	}

	public DeliveryPerson(String name, String phoneNumber, String areaId, String postcode, String password) throws EntitiesExceptionHandler {

		// Validate Input
		try {
			validateName(name);
			validatePhoneNumber(phoneNumber);
			validateAreaId(areaId);
			validatePostcode(postcode);
			validatePassword(password);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.name = name;
		this.phoneNumber = phoneNumber;
		this.areaId = areaId;
		this.postcode = postcode;
		this.password = password;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getName() {

		return name;
	}

	public void setName(String name) throws EntitiesExceptionHandler {

		validateName(name);
		this.name = name;
	}

	public String getPhoneNumber() {

		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws EntitiesExceptionHandler {

		validatePhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public String getAreaId() {

		return areaId;
	}

	public void setAreaId(String areaId) throws EntitiesExceptionHandler {

		validateAreaId(areaId);
		this.areaId = areaId;
	}

	public String getPostcode() {

		return postcode;
	}

	public void setPostcode(String postcode) throws EntitiesExceptionHandler {

		validatePostcode(postcode);
		this.postcode = postcode;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) throws EntitiesExceptionHandler {

		validatePassword(password);
		this.password = password;
	}

	// ----------------- Attributes Validating Methods ----------------- //

	public boolean validateName(String name) throws EntitiesExceptionHandler {

		boolean result = false;

		if (name == null || name.isBlank()) {
			throw new EntitiesExceptionHandler("Delivery Person Name NOT specified");

		} else if (name.length() < 2) {
			throw new EntitiesExceptionHandler("Delivery Person Name does not meet minimum length requirements");

		} else if (name.length() > 50) {
			throw new EntitiesExceptionHandler("Delivery Person Name exceeds maximum length requirements");

		} else if (!name.matches("[a-zA-Z ]+")) { // Only allows letters and spaces
			throw new EntitiesExceptionHandler("Delivery Person Name contains invalid characters");

		} else {
			result = true;
		}

		return result;
	}

	public boolean validatePhoneNumber(String phoneNumber) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to validate phone number format: +353 00 000 0000
		String phoneRegex = "^\\+353 \\d{2} \\d{3} \\d{4}$";
		Pattern pattern = Pattern.compile(phoneRegex);
		Matcher matcher = pattern.matcher(phoneNumber);

		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new EntitiesExceptionHandler("Phone Number NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Phone Number format NOT valid. Expected format: +353 00 000 0000");

		} else {
			result = true;

		}

		return result;

	}

	public boolean validateAreaId(String areaId) throws EntitiesExceptionHandler {

		boolean result = false;

		// Check if the areaId is null or blank before parsing
		if (areaId == null || areaId.isBlank()) {
			throw new EntitiesExceptionHandler("Area Id Number NOT specified");
		}

		int areaIdNumber;
		try {
			areaIdNumber = Integer.parseInt(areaId);
		} catch (NumberFormatException e) {
			throw new EntitiesExceptionHandler("Area Id must be a valid number");
		}

		// Additional checks for areaIdNumber
		if (areaIdNumber <= 0) {
			throw new EntitiesExceptionHandler("Area Id must be greater than zero");
		} else if (areaIdNumber > 24) {
			throw new EntitiesExceptionHandler("Area Id must be less than or equal to 24");
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

	public boolean validatePassword(String password) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to ensure password contains at least one letter and one number or
		// special character
		String passwordRegex = "^(?=.*[A-Za-z])(?=.*[0-9!@#$%^&*]).{8,15}$";
		Pattern pattern = Pattern.compile(passwordRegex);
		Matcher matcher = pattern.matcher(password);

		if (password == null || password.isBlank()) {
			throw new EntitiesExceptionHandler("Password NOT specified");

		} else if (password.length() < 8 || password.length() > 15) {
			throw new EntitiesExceptionHandler("Password must be between 8 and 15 characters");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Password must contain at least one letter and one number or special character");

		} else {
			result = true;
		}

		return result;
	}

}
