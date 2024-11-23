package delivery_area_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryArea {

	private String name;
	private String postcode;

	// ----------------------- Constructors ------------------------ //

	public DeliveryArea() {

		// No-argument constructor

	}

	public DeliveryArea(String name, String postcode) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateAreaName(name);
			validatePostcode(postcode);

		} catch (EntitiesExceptionHandler e) {

			e.printStackTrace();

			throw e;
		}

		this.name = name;
		this.postcode = postcode;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getName() {

		return name;
	}

	public void setName(String name) throws EntitiesExceptionHandler {

		validateAreaName(name);
		this.name = name;
	}

	public String getPostcode() {

		return postcode;
	}

	public void setPostcode(String postcode) throws EntitiesExceptionHandler {

		validatePostcode(postcode);
		this.postcode = postcode;
	}

	// ----------------- Attributes Validating Method ----------------- //

	public boolean validateAreaName(String name) throws EntitiesExceptionHandler {

		boolean result = false;

		if (name == null || name.isBlank()) {
			throw new EntitiesExceptionHandler("Delivery Area Name NOT specified");

		} else if (name.length() < 2) {
			throw new EntitiesExceptionHandler("Delivery Area Name does not meet minimum length requirements");

		} else if (name.length() > 50) {
			throw new EntitiesExceptionHandler("Delivery Area Name exceeds maximum length requirements");

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
}
