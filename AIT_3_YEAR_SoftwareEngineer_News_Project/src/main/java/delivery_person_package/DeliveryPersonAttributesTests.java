package delivery_person_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class DeliveryPersonAttributesTests {

	DeliveryPerson deliveryPerson = new DeliveryPerson();

	// name
	@Test
	public void testValidName() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validateName("John Doe")); // Valid name
	}

	@Test
	public void testInvalidName_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validateName("Jo"); // Less than 3 chars
		});
	}

	@Test
	public void testInvalidName_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validateName("ThisNameIsWayTooLongToBeValid"); // More than 20 chars
		});
	}

	// postcode
	@Test
	public void testValidPostcode() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validatePostcode("N37 AO24")); // Valid postcode
	}

	@Test
	public void testInvalidPostcode_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePostcode("N37 A02"); // Incorrect length
		});
	}

	@Test
	public void testInvalidPostcode_WrongFormat() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePostcode("37N AO24"); // Incorrect format
		});
	}

	// phone number
	@Test
	public void testValidPhoneNumber() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validatePhoneNumber("+353 01 123 4567")); // Valid phone number
	}

	@Test
	public void testInvalidPhoneNumber_NoCountryCode() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePhoneNumber("01 123 4567"); // Missing country code
		});
	}

	@Test
	public void testInvalidPhoneNumber_WrongFormat() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePhoneNumber("+353011234567"); // Incorrect format
		});
	}

	// area id
	@Test
	public void testValidAreaId() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validateAreaId("12")); // Valid Area Id
	}

	@Test
	public void testInvalidAreaIdEmpty() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validateAreaId("")); // Area Id is Empty
	}

	@Test
	public void testInvalidAreaIdZero() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validateAreaId("0"); // Area Id is zero
		});
	}

	@Test
	public void testInvalidAreaIdLessThanZero() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validateAreaId("-1"); // Area Id less than zero
		});
	}

	@Test
	public void testInvalidAreaIdGreaterThan24() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validateAreaId("25"); // Area Id greater than 24
		});
	}

	// password
	@Test
	public void testValidPassword() throws EntitiesExceptionHandler {
		assertTrue(deliveryPerson.validatePassword("Password1!")); // Valid password
	}

	@Test
	public void testInvalidPassword_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePassword("Pass1!"); // Less than 8 characters
		});
	}

	@Test
	public void testInvalidPassword_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePassword("ThisIsAVeryLongPassword123!"); // More than 15 characters
		});
	}

	@Test
	public void testInvalidPassword_NoNumberOrSpecialChar() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePassword("Password"); // Missing number or special character
		});
	}

	// name containing numbers
	@Test
	public void testInvalidName_ContainsNumbers() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validateName("John123");
		});
	}

	// postcode with special characters
	@Test
	public void testInvalidPostcode_SpecialCharacters() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePostcode("N37@AO24");
		});
	}

	// phone number with extra spaces
	@Test
	public void testInvalidPhoneNumber_ExtraSpaces() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePhoneNumber("+353  01 123 4567");
		});
	}

	// password with only special characters
	@Test
	public void testInvalidPassword_OnlySpecialChars() {
		assertThrows(RuntimeException.class, () -> {
			deliveryPerson.validatePassword("!!!!!!!!");
		});
	}
}
