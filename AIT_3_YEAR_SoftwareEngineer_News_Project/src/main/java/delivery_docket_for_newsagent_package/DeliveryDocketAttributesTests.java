package delivery_docket_for_newsagent_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryDocketAttributesTests {

	DeliveryDocket deliveryDocket = new DeliveryDocket();

	// delivery docket date
	@Test
	public void testValidDocketDate() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.deliveryDocketDate("01/10/2024")); // Valid date, matches order date
	}

	@Test
	public void testInvalidDocketDate_NotMatchingOrderDate() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.deliveryDocketDate("2024-10-02"); // Docket date doesn't match order date
		});
	}

	// tracking number
	@Test
	public void testValidTrackingNumber() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateTrackingNumber(600)); // Valid tracking number within range
	}

	@Test
	public void testInvalidTrackingNumber_TooLow() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateTrackingNumber(0); // Below valid range
		});
	}

	@Test
	public void testInvalidTrackingNumber_TooHigh() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateTrackingNumber(1001); // Above valid range
		});
	}

	// customer name
	@Test
	public void testValidCustomerName() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateCustomerName("John Doe")); // Valid name
	}

	@Test
	public void testInvalidCustomerName_TooShort() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateCustomerName("J"); // Less than 2 chars
		});
	}

	@Test
	public void testInvalidCustomerName_TooLong() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateCustomerName("ThisNameIsWayTooLongToBeValidThisNameIsWayTooLongToBeValid"); // More than 50 chars
		});
	}

	// delivery person name
	@Test
	public void testValidDeliveryPersonName() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateDeliveryPersonName("Jane Smith")); // Valid name
	}

	@Test
	public void testInvalidDeliveryPersonName_TooShort() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryPersonName("J"); // Less than 2 chars
		});
	}

	@Test
	public void testInvalidDeliveryPersonName_TooLong() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryPersonName("ThisNameIsWayTooLongToBeValidThisNameIsWayTooLongToBeValid"); // More than 50 chars
		});
	}

	// postcode
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validatePostcode("N37AO24")); // Valid postcode
	}

	@Test
	public void testInvalidPostcode_LessThanRequiredLength() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validatePostcode("N37 A02"); // Incorrect length
		});
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validatePostcode("37N AO24"); // Incorrect format
		});
	}

	// special characters in customer name
	@Test
	public void testInvalidCustomerName_SpecialCharacters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateCustomerName("John@Doe");
		});
	}

	// special characters in Delivery Person name
	@Test
	public void testInvalidDeliveryPersonName_SpecialCharacters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryPersonName("Jane@Smith");
		});
	}

	// postcode with special characters
	@Test
	public void testInvalidPostcode_SpecialCharacters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validatePostcode("N37@AO24");
		});
	}
}
