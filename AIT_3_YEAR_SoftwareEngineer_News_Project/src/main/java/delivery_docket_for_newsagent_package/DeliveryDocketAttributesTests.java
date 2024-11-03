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
		assertTrue(deliveryDocket.deliveryDocketDate("2024-10-01")); // Valid date, matches order date
	}

	@Test
	public void testInvalidDocketDate_NotMatchingOrderDate() {
		assertThrows(RuntimeException.class, () -> {
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
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validateTrackingNumber(0); // Below valid range
		});
	}

	@Test
	public void testInvalidTrackingNumber_TooHigh() {
		assertThrows(RuntimeException.class, () -> {
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
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validateCustomerName("Jo"); // Less than 3 chars
		});
	}

	@Test
	public void testInvalidCustomerName_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validateCustomerName("ThisNameIsWayTooLongToBeValid"); // More than 20 chars
		});
	}

	// delivery person name
	@Test
	public void testValidDeliveryPersonName() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateDeliveryPersonName("Jane Smith")); // Valid name
	}

	@Test
	public void testInvalidDeliveryPersonName_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validateDeliveryPersonName("Ja"); // Less than 3 chars
		});
	}

	@Test
	public void testInvalidDeliveryPersonName_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validateDeliveryPersonName("ThisNameIsWayTooLongToBeValid"); // More than 20 chars
		});
	}

	// postcode
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validatePostcode("N37 AO24")); // Valid postcode
	}

	@Test
	public void testInvalidPostcode_LessThanRequiredLength() {
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validatePostcode("N37 A02"); // Incorrect length
		});
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validatePostcode("37N AO24"); // Incorrect format
		});
	}

	// special characters in customer name
	@Test
	public void testInvalidCustomerName_SpecialCharacters() {
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validateCustomerName("John@Doe");
		});
	}

	// special characters in Delivery Person name
	@Test
	public void testInvalidDeliveryPersonName_SpecialCharacters() {
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validateDeliveryPersonName("Jane@Smith");
		});
	}

	// postcode with special characters
	@Test
	public void testInvalidPostcode_SpecialCharacters() {
		assertThrows(RuntimeException.class, () -> {
			deliveryDocket.validatePostcode("N37@AO24");
		});
	}
}
