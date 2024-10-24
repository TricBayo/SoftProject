import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerProfileAttributesTests {

    // All valid tests

    // Valid customer ID is accepted
    @Test
    public void test_valid_customer_id() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertTrue(customerProfile.validateCustomerID("AE0000000000"));
    }

    // Valid name is accepted
    @Test
    public void test_valid_name_accepted() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertTrue(customerProfile.validateName("John"));
    }

    // Valid postcode is accepted
    @Test
    public void test_valid_postcode() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertTrue(customerProfile.validatePostcode("N37 AO24"));
    }

    // Valid phone number is accepted
    @Test
    public void test_valid_phone_number() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertTrue(customerProfile.validatePhoneNumber("+353 01 123 4567"));
    }

    // Valid email is accepted
    @Test
    public void test_valid_email() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertTrue(customerProfile.validateEmail("user@example.com"));
    }

    // Valid monthly payment status is accepted
    @Test
    public void test_valid_monthly_payment_status() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertTrue(customerProfile.validateMonthlyPaymentStatus(2));
    }

    // Monthly payment status boundary value
    @Test
    public void testValidMonthlyPaymentStatus_BoundaryValue() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertTrue(customerProfile.validateMonthlyPaymentStatus(3));  // Boundary value (3 months)
    }

    // All invalid tests - causing exceptions

    // Customer ID less than 12 characters throws exception
    @Test
    public void test_invalid_customer_id_less_than_12_chars() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateCustomerID("AE0000000");
        });
    }

    // Customer ID more than 12 characters throws exception
    @Test
    public void test_customer_id_more_than_12_chars_throws_exception() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateCustomerID("AE00000000000");  // More than required length
        });
    }

    // Customer ID with no letters throws exception
    @Test
    public void test_invalid_customer_id_no_letters() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateCustomerID("000000000000");  // Missing letters
        });
    }

    // Customer ID with no digits throws exception
    @Test
    public void test_invalid_customer_id_no_digits() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateCustomerID("AEABCDEFGHI");  // Missing digits
        });
    }

    // Check for special characters in Customer ID
    @Test
    public void testInvalidCustomerID_SpecialCharacters() {
    assertThrows(RuntimeException.class, () -> {
        customerProfile.validateCustomerID("AE#00000000");  // Contains special character
    });
}

    // Name less than 3 characters throws exception
    @Test
    public void test_name_less_than_3_chars_throws_exception() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateName("Jo");
        });
    }

    // Name more than 20 characters throws exception
    @Test
    public void test_name_more_than_20_chars_throws_exception() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateName("ThisNameIsWayTooLongToBeValid");
        });
    }

    // Check if name contains numbers
    @Test
    public void testInvalidName_ContainsNumbers() {
    assertThrows(RuntimeException.class, () -> {
        customerProfile.validateName("John123");  // Name contains digits
    });
}
    // Check if name contains special characters
    @Test
    public void testInvalidName_ContainsSpecialCharacters() {
    assertThrows(RuntimeException.class, () -> {
        customerProfile.validateName("John@Doe");  // Contains special character
    });
}

    // Postcode with incorrect length throws exception
    @Test
    public void test_invalid_postcode_incorrect_length() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validatePostcode("N37 A02");  // Incorrect length
        });
    }

    // Postcode with wrong format throws exception
    @Test
    public void test_invalid_postcode_wrong_format() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validatePostcode("37N AO24");  // Incorrect format
        });
    }

    // Check if postcode contains special characters
    @Test
    public void testInvalidPostcode_ContainsSpecialCharacters() {
    assertThrows(RuntimeException.class, () -> {
        customerProfile.validatePostcode("N37@AO24");  // Contains special character
    });
}

    // Phone number without country code throws exception
    @Test
    public void test_phone_number_without_country_code_throws_exception() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validatePhoneNumber("01 123 4567");  // Missing country code
        });
    }

    // Phone number with wrong format throws exception
    @Test
    public void test_invalid_phone_number_wrong_format() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validatePhoneNumber("+353011234567");  // Incorrect format
        });
    }

    // Check for invalid spaces
    @Test
    public void testInvalidPhoneNumber_ExtraSpaces() {
    assertThrows(RuntimeException.class, () -> {
        customerProfile.validatePhoneNumber("+353  01 123 4567");  // Double space between code and number
    });
}

    // Check for too short phone numbers
    @Test
    public void testInvalidPhoneNumber_ShortLength() {
    assertThrows(RuntimeException.class, () -> {
        customerProfile.validatePhoneNumber("+353 01 123");  // Too short phone number
    });
}

    // Email missing '@' symbol throws exception
    @Test
    public void test_invalid_email_missing_at_symbol() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateEmail("userexample.com");  // Missing @ symbol
        });
    }

    // Email missing domain throws exception
    @Test
    public void test_invalid_email_missing_domain() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateEmail("user@.com");  // Missing domain
        });
    }

    // Email missing top-level domain throws exception
    @Test
    public void test_email_missing_tld_throws_exception() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateEmail("user@example");
        });
    }

    // Monthly payment status more than 3 months delayed throws exception
    @Test
    public void test_invalid_monthly_payment_status() {
        CustomerProfileAttributesTests customerProfile = new CustomerProfileAttributesTests();
        assertThrows(RuntimeException.class, () -> {
            customerProfile.validateMonthlyPaymentStatus(4);  // More than 3 months delayed
        });
    }

    // Check for special characters in domain
    @Test
    public void testInvalidEmail_SpecialCharactersInDomain() {
    assertThrows(RuntimeException.class, () -> {
        customerProfile.validateEmail("user@example$.com");  // Special character in domain
    });
}
}