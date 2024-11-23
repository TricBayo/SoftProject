package delivery_person_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryPersonAttributesTests {

	DeliveryPerson deliveryPerson = new DeliveryPerson();

	// delivery person name
	@Test
	public void testValidName() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validateName("John Doe")); // Valid name
	}

	@Test
	public void testInvalidName_TooShort() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validateName("J"); // Less than 2 chars
		});
	}

	@Test
	public void testInvalidName_TooLong() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validateName("ThisNameIsWayTooLongToBeValidThisNameIsWayTooLongToBeValid"); // More than 50 chars
		});
	}

	// delivery person postcode address
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validatePostcode("N37AO24")); // Valid postcode
	}

	@Test
	public void testInvalidPostcode_TooShort() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePostcode("N37 A02"); // Incorrect length
		});
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePostcode("37N AO24"); // Incorrect format
		});
	}

	// delivery person phone number
	@Test
	public void testValidPhoneNumberWithSpaces() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validatePhoneNumber("+353 01 123 4567")); // Valid phone number
	}

	@Test
	public void testValidPhoneNumberWithoutSpaces() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validatePhoneNumber("+353011234567")); // Valid phone number
	}

	@Test
	public void testInvalidPhoneNumber_NoCountryCode() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePhoneNumber("01 123 4567"); // Missing country code
		});
	}

	// delivery area id
	@Test
	public void testValidAreaId() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validateAreaId("12")); // Valid Area Id
	}

	@Test
	public void testInvalidAreaIdEmpty() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validateAreaId(""); // Area Id is Empty
		});
	}

	@Test
	public void testInvalidAreaIdZero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validateAreaId("0"); // Area Id is zero
		});
	}

	@Test
	public void testInvalidAreaIdLessThanZero() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validateAreaId("-1"); // Area Id less than zero
		});
	}

	@Test
	public void testInvalidAreaIdGreaterThan24() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validateAreaId("25"); // Area Id greater than 24
		});
	}

	// delivery person password
	@Test
	public void testValidPassword() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validatePassword("Password1!")); // Valid password
	}

	@Test
	public void testInvalidPassword_TooShort() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePassword("Pass1!"); // Less than 8 characters
		});
	}

	@Test
	public void testInvalidPassword_TooLong() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePassword("ThisIsAVeryLongPassword123!"); // More than 15 characters
		});
	}

	@Test
	public void testInvalidPassword_NoNumberOrSpecialChar() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePassword("Password"); // Missing number or special character
		});
	}

	// name containing numbers
	@Test
	public void testInvalidName_ContainsNumbers() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validateName("John123");
		});
	}

	// postcode with special characters
	@Test
	public void testInvalidPostcode_SpecialCharacters() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePostcode("N37@AO24");
		});
	}

	// phone number with extra spaces
	@Test
	public void testInvalidPhoneNumber_ExtraSpaces() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePhoneNumber("+353  01 123 4567");
		});
	}

	// password with only special characters
	@Test
	public void testInvalidPassword_OnlySpecialChars() {
		assertThrows(EntitiesExceptionHandler.class, () -> {
			deliveryPerson.validatePassword("!!!!!!!!");
		});
	}
}
