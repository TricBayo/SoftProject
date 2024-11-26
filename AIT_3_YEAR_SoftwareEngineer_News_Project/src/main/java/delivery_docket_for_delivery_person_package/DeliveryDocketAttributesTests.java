package delivery_docket_for_delivery_person_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import delivery_docket_for_newsagent_package.DeliveryDocket;
import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryDocketAttributesTests {

	DeliveryDocket deliveryDocket = new DeliveryDocket();

	// delivery docket date
	@Test
	public void testValidDocketDate() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateDeliveryDocketDate("01/10/2024")); // Valid date, matches order date
	}

	@Test
	public void testInvalidDocketDate_NotMatchingOrderDate() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryDocketDate("2024-10-02"); // Docket date doesn't match order date
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

	// customerId tests
	@Test
	public void testValidCustomerId() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateCustomerId(123)); // Valid customerId
	}

	@Test
	public void testInvalidCustomerId_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateCustomerId(-1); // Negative customerId
		});
	}

	@Test
	public void testInvalidCustomerId_Zero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryPersonId(0); // Zero customerId (invalid)
		});
	}

	// deliveryPersonId tests
	@Test
	public void testValidDeliveryPersonId() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateDeliveryPersonId(123)); // Valid customerId
	}

	@Test
	public void testInvalidDeliveryPersonId_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryPersonId(-1); // Negative customerId
		});
	}

	@Test
	public void testInvalidDeliveryPersonId_Zero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryPersonId(0); // Zero customerId (invalid)
		});
	}

	// delivery status
	@Test
	public void testValidStatusDelivered() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateDeliveryStatus(1)); // Valid delivered status (one)
	}

	@Test
	public void testValidStatusNotDelivered() throws EntitiesExceptionHandler {
		assertTrue(deliveryDocket.validateDeliveryStatus(0)); // Valid not delivered status (zero)
	}

	@Test
	public void testInvalidStatus_Negative() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryStatus(-1); // Invalid: Negative status number
		});
	}

	@Test
	public void testInvalidStatus_GreaterThanOne() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryDocket.validateDeliveryStatus(2); // Invalid: Status is two
		});
	}

}
