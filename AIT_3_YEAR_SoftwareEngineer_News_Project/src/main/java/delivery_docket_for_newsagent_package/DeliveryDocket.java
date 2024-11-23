package delivery_docket_for_newsagent_package;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryDocket {

	private String orderDate;
	private int trackNumber;
	private int deliveryStatus;

	private int customerId;
	private int deliveryPersonId;

	// ----------------------- Constructors ------------------------ //

	public DeliveryDocket() {

		// No-argument constructor
	}

	public DeliveryDocket(String orderDate, int trackNumber, int deliveryStatus, int customerId, int deliveryPersonId) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateDeliveryDocketDate(orderDate);
			validateTrackingNumber(trackNumber);
			validateDeliveryStatus(deliveryStatus);

			validateCustomerId(customerId);
			validateDeliveryPersonId(deliveryPersonId);

		} catch (EntitiesExceptionHandler e) {

			throw e;

		}

		this.orderDate = orderDate;
		this.trackNumber = trackNumber;
		this.deliveryStatus = deliveryStatus;

		this.customerId = customerId;
		this.deliveryPersonId = deliveryPersonId;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public int getCustomerId() {

		return this.customerId;
	}

	public void setCustomerId(int customerId) throws EntitiesExceptionHandler {

		validateCustomerId(customerId);
		this.customerId = customerId;
	}

	public int getDeliveryPersonId() {

		return this.deliveryPersonId;
	}

	public void setDeliveryPersonId(int deliveryPersonId) throws EntitiesExceptionHandler {

		validateDeliveryPersonId(deliveryPersonId);
		this.deliveryPersonId = deliveryPersonId;
	}

	public String getOrderDate() {

		return this.orderDate;
	}

	public void setOrderDate(String orderDate) throws EntitiesExceptionHandler {

		validateDeliveryDocketDate(orderDate);
		this.orderDate = orderDate;
	}

	public int getTrackNumber() {

		return this.trackNumber;
	}

	public void setTrackNumber(int trackNumber) throws EntitiesExceptionHandler {

		validateTrackingNumber(trackNumber);
		this.trackNumber = trackNumber;
	}

	public int getDeliveryStatus() {

		return deliveryStatus;
	}

	public void setDeliveryStatus(int deliveryStatus) {

		this.deliveryStatus = deliveryStatus;
	}

	// ----------------- Attributes Validating Method ----------------- //

	public boolean validateCustomerId(int customerId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (customerId <= 0) {
			throw new EntitiesExceptionHandler("Customer ID must be greater than 0");

		} else {

			result = true;
		}

		return result;
	}

	public boolean validateDeliveryPersonId(int deliveryPersonId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (deliveryPersonId <= 0) {
			throw new EntitiesExceptionHandler("Delivery Person ID must be greater than 0");

		} else {

			result = true;
		}

		return result;
	}

	public boolean validateDeliveryDocketDate(String date) throws EntitiesExceptionHandler {

		// Regex to validate date format as DD/MM/YYYY
		String dateRegex = "^\\d{2}/\\d{2}/\\d{4}$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(date);

		if (date == null || date.isBlank()) {
			throw new EntitiesExceptionHandler("Delivery Docket Date NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Delivery Docket  Date format NOT valid. Expected format: DD/MM/YY");
		}

		// Further validate logical correctness of the date
		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(date, formatter); // This will throw an exception if the date is invalid

		} catch (DateTimeParseException e) {

			throw new EntitiesExceptionHandler("Delivery Docket Date is not a valid date.");
		}

		return true; // Return true if the date is valid
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

	public boolean validateDeliveryStatus(int deliveryStatus) throws EntitiesExceptionHandler {

		boolean result = true;

		if (deliveryStatus < 0) {
			result = false;
			throw new EntitiesExceptionHandler("Delivery Status cannot be a negative number");

		} else if (deliveryStatus > 1) {
			result = false;
			throw new EntitiesExceptionHandler("Delivery Status value should be: (1 If Delivered Successfully) or (0 If NOT Delivered)");

		} else if (deliveryStatus == 0) {
			System.out.println("Status: NOT Delivered");

		} else if (deliveryStatus == 1) {
			System.out.println("Status: Delivered SUCCESSFULLY");

		}

		return result;
	}

}
