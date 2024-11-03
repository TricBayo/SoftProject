package delivery_area_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryAreaAttributesTests {

	DeliveryArea deliveryArea = new DeliveryArea();

	// area postcode tests
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(deliveryArea.validatePostcode("N37 AO24")); // Valid postcode
	}

	@Test
	public void testInvalidPostcode_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			deliveryArea.validatePostcode("N37 A02"); // Incorrect length
		});
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(RuntimeException.class, () -> {
			deliveryArea.validatePostcode("37N AO24"); // Incorrect format
		});
	}

	@Test
	public void testInvalidPostcode_SpecialCharacters() {
		assertThrows(RuntimeException.class, () -> {
			deliveryArea.validatePostcode("N37@AO24"); // Special characters in postcode
		});
	}

	// area name tests
	@Test
	public void testValidAreaName() throws EntitiesExceptionHandler {
		assertTrue(deliveryArea.validateAreaName("North Region")); // Valid Area Name
	}

	@Test
	public void testInvalidAreaName_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			deliveryArea.validateAreaName("ThisAreaNameIsWayTooLongToBeValidForOurApplication"); // More than 50 chars
		});
	}

	@Test
	public void testInvalidAreaName_Empty() {
		assertThrows(RuntimeException.class, () -> {
			deliveryArea.validateAreaName(""); // Empty area name
		});
	}

}
