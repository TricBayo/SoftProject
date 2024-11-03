package delivery_docket_for_newsagent_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryDocket {

	private String customerName;
	private String deliveryPersonName;
	private String orderDate;
	private String postcode;
	private int trackNumber;

	// ----------------------- Constructors ------------------------ //

	public DeliveryDocket() {

		// No-argument constructor

	}

	public DeliveryDocket(String customerName, String deliveryPersonName, String orderDate, String postcode, int trackNumber) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateCustomerName(customerName);
			validateDeliveryPersonName(deliveryPersonName);
			deliveryDocketDate(orderDate);
			validatePostcode(postcode);
			validateTrackingNumber(trackNumber);

		} catch (EntitiesExceptionHandler e) {

			throw e;

		}

		this.customerName = customerName;
		this.deliveryPersonName = deliveryPersonName;
		this.orderDate = orderDate;
		this.postcode = postcode;
		this.trackNumber = trackNumber;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) throws EntitiesExceptionHandler {

		validateCustomerName(customerName);
		this.customerName = customerName;
	}

	public String getDeliveryPersonName() {

		return deliveryPersonName;
	}

	public void setDeliveryPersonName(String deliveryPersonName) throws EntitiesExceptionHandler {

		validateDeliveryPersonName(deliveryPersonName);
		this.deliveryPersonName = deliveryPersonName;
	}

	public String getOrderDate() {

		return orderDate;
	}

	public void setOrderDate(String orderDate) throws EntitiesExceptionHandler {

		deliveryDocketDate(orderDate);
		this.orderDate = orderDate;
	}

	public String getPostcode() {

		return postcode;
	}

	public void setPostcode(String postcode) throws EntitiesExceptionHandler {

		validatePostcode(postcode);
		this.postcode = postcode;
	}

	public int getTrackNumber() {

		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) throws EntitiesExceptionHandler {

		validateTrackingNumber(trackNumber);
		this.trackNumber = trackNumber;
	}

	// ----------------- Attributes Validating Method ----------------- //

	public boolean validateCustomerName(String customerName) throws EntitiesExceptionHandler {

		boolean result = false;

		if (customerName == null || customerName.isBlank()) {
			throw new EntitiesExceptionHandler("Customer Name NOT specified");

		} else if (customerName.length() < 2) {
			throw new EntitiesExceptionHandler("Customer Name does not meet minimum length requirements");

		} else if (customerName.length() > 50) {
			throw new EntitiesExceptionHandler("Customer Name exceeds maximum length requirements");

		} else {
			result = true;

		}

		return result;
	}

	public boolean validateDeliveryPersonName(String deliveryPersonName) throws EntitiesExceptionHandler {

		boolean result = false;

		if (deliveryPersonName == null || deliveryPersonName.isBlank()) {
			throw new EntitiesExceptionHandler("Delivery Person Name NOT specified");

		} else if (deliveryPersonName.length() < 2) {
			throw new EntitiesExceptionHandler("Delivery Person Name does not meet minimum length requirements");

		} else if (deliveryPersonName.length() > 50) {
			throw new EntitiesExceptionHandler("Delivery Person Name exceeds maximum length requirements");

		} else {
			result = true;

		}

		return result;
	}

	public boolean deliveryDocketDate(String deliveryDate) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to validate date format as YYYY-MM-DD
		String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(orderDate);

		if (deliveryDate == null || deliveryDate.isBlank()) {
			throw new EntitiesExceptionHandler("Delivery Docket Date NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Delivery Docket Date format NOT valid. Expected format: YYYY-MM-DD");

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

	public boolean validateTrackingNumber(int trackNumber) throws EntitiesExceptionHandler {

		boolean result = false;

		if (trackNumber < 1 || trackNumber > 1000) {
			throw new EntitiesExceptionHandler("Track Number must be in the range of 1 to 1000");

		} else {
			result = true;

		}

		return result;
	}

}
