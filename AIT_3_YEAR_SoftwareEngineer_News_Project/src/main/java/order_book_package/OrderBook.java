package order_book_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class OrderBook {

	private String name;
	private String postcode;
	private String publicationName;

// ----------------------- Constructors ------------------------ //

	public OrderBook() {

		// No-argument constructor
	}

	public OrderBook(String name, String postcode, String publicationName) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateCustomerName(name);
			validatePostcode(postcode);
			validatePublicationName(publicationName);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.name = name;
		this.postcode = postcode;
		this.publicationName = publicationName;

	}

// ----------------------- Getter and Setters ------------------------ //

	public String getName() {

		return this.name;
	}

	public void setName(String name) throws EntitiesExceptionHandler {

		validateCustomerName(name);
		this.name = name;
	}

	public String getPostcode() {

		return this.postcode;
	}

	public void setPostcode(String postcode) throws EntitiesExceptionHandler {

		validatePostcode(postcode);
		this.postcode = postcode;
	}

	public String getPublicationName() {

		return this.publicationName;
	}

	public void setPublicationName(String publicationName) throws EntitiesExceptionHandler {

		validatePublicationName(publicationName);
		this.publicationName = publicationName;
	}

// ----------------- Attributes Validating Method ----------------- //

	public boolean validateCustomerName(String name) throws EntitiesExceptionHandler {

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

	public boolean validatePublicationName(String name) throws EntitiesExceptionHandler {

		boolean result = false;

		if (name == null || name.isBlank()) {
			throw new EntitiesExceptionHandler("Publication Name NOT specified");

		} else if (name.length() < 2) {
			throw new EntitiesExceptionHandler("Publication Name does not meet minimum length requirements");

		} else if (name.length() > 50) {
			throw new EntitiesExceptionHandler("Publication Name exceeds maximum length requirements");

		} else if (!name.matches("[a-zA-Z ]+")) { // Only allows letters and spaces
			throw new EntitiesExceptionHandler("Publication Name contains invalid characters");

		} else {
			result = true;
		}

		return result;
	}

}
