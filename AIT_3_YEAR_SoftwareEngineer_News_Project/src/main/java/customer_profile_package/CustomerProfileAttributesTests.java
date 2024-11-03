package customer_profile_package;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import for_all_entities_package.EntitiesExceptionHandler;
import junit.framework.TestCase;

public class CustomerProfileAttributesTests extends TestCase {

	// All valid tests

	// Valid name is accepted
	@Test
	public void test_valid_name_accepted() throws EntitiesExceptionHandler {
		CustomerProfile customerProfile = new CustomerProfile();
		assertTrue(customerProfile.validateName("John"));
	}

	// Valid postcode is accepted
	@Test
	public void test_valid_postcode() throws EntitiesExceptionHandler {
		CustomerProfile customerProfile = new CustomerProfile();
		assertTrue(customerProfile.validatePostcode("N37 AO24"));
	}

	// Valid phone number is accepted
	@Test
	public void test_valid_phone_number() throws EntitiesExceptionHandler {
		CustomerProfile customerProfile = new CustomerProfile();
		assertTrue(customerProfile.validatePhoneNumber("+353 01 123 4567"));
	}

	// Valid email is accepted
	@Test
	public void test_valid_email() throws EntitiesExceptionHandler {
		CustomerProfile customerProfile = new CustomerProfile();
		assertTrue(customerProfile.validateEmail("user@example.com"));
	}

	// Valid monthly payment status is accepted
	@Test
	public void test_valid_monthly_payment_status() throws EntitiesExceptionHandler {
		CustomerProfile customerProfile = new CustomerProfile();
		assertTrue(customerProfile.validatePaymentStatus(2));
	}

	// Monthly payment status boundary value
	@Test
	public void testValidMonthlyPaymentStatus_BoundaryValue() throws EntitiesExceptionHandler {
		CustomerProfile customerProfile = new CustomerProfile();
		assertTrue(customerProfile.validatePaymentStatus(3)); // Boundary value (3 months)
	}

	// All invalid tests - causing exceptions

	// Name less than 3 characters throws exception
	@Test
	public void test_name_less_than_3_chars_throws_exception() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validateName("Jo");
		});
	}

	// Name more than 20 characters throws exception
	@Test
	public void test_name_more_than_20_chars_throws_exception() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validateName("ThisNameIsWayTooLongToBeValid");
		});
	}

	// Check if name contains numbers
	@Test
	public void testInvalidName_ContainsNumbers() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validateName("John123"); // Name contains digits
		});
	}

	// Check if name contains special characters
	@Test
	public void testInvalidName_ContainsSpecialCharacters() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validateName("John@Doe"); // Contains special character
		});
	}

	// Postcode with incorrect length throws exception
	@Test
	public void test_invalid_postcode_incorrect_length() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validatePostcode("N37 A02"); // Incorrect length
		});
	}

	// Postcode with wrong format throws exception
	@Test
	public void test_invalid_postcode_wrong_format() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validatePostcode("37N AO24"); // Incorrect format
		});
	}

	// Check if postcode contains special characters
	@Test
	public void testInvalidPostcode_ContainsSpecialCharacters() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validatePostcode("N37@AO24"); // Contains special character
		});
	}

	// Phone number without country code throws exception
	@Test
	public void test_phone_number_without_country_code_throws_exception() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validatePhoneNumber("01 123 4567"); // Missing country code
		});
	}

	// Phone number with wrong format throws exception
	@Test
	public void test_invalid_phone_number_wrong_format() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validatePhoneNumber("+353011234567"); // Incorrect format
		});
	}

	// Check for invalid spaces
	@Test
	public void testInvalidPhoneNumber_ExtraSpaces() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validatePhoneNumber("+353  01 123 4567"); // Double space between code and number
		});
	}

	// Check for too short phone numbers
	@Test
	public void testInvalidPhoneNumber_ShortLength() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validatePhoneNumber("+353 01 123"); // Too short phone number
		});
	}

	// Email missing '@' symbol throws exception
	@Test
	public void test_invalid_email_missing_at_symbol() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validateEmail("userexample.com"); // Missing @ symbol
		});
	}

	// Email missing domain throws exception
	@Test
	public void test_invalid_email_missing_domain() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validateEmail("user@.com"); // Missing domain
		});
	}

	// Email missing top-level domain throws exception
	@Test
	public void test_email_missing_tld_throws_exception() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validateEmail("user@example");
		});
	}

	// Monthly payment status more than 3 months delayed throws exception
	@Test
	public void test_invalid_monthly_payment_status() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validatePaymentStatus(4); // More than 3 months delayed
		});
	}

	// Check for special characters in domain
	@Test
	public void testInvalidEmail_SpecialCharactersInDomain() {
		CustomerProfile customerProfile = new CustomerProfile();
		assertThrows(RuntimeException.class, () -> {
			customerProfile.validateEmail("user@example$.com"); // Special character in domain
		});
	}

}