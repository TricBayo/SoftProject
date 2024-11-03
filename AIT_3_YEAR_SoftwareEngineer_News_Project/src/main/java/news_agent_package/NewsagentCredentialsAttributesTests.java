package news_agent_package;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import for_all_entities_package.EntitiesExceptionHandler;

public class NewsagentCredentialsAttributesTests {

	NewsagentCredentials newsagent = new NewsagentCredentials();

	// newsagent name
	@Test
	public void testValidNewsagentName() throws EntitiesExceptionHandler {
		assertTrue(newsagent.validateNewsagentName("John Doe")); // Valid name
	}

	@Test
	public void testInvalidNewsagentName_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentName("Jo"); // Name too short
		});
	}

	@Test
	public void testInvalidNewsagentName_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentName("ThisNameIsWayTooLongToBeValidForNewsagent"); // Name too long
		});
	}

	// password
	@Test
	public void testValidNewsagentPassword() throws EntitiesExceptionHandler {
		assertTrue(newsagent.validateNewsagentPassword("Password1!")); // Valid password
	}

	@Test
	public void testInvalidNewsagentPassword_TooShort() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentPassword("Pass1!"); // Password too short (less than 8 characters)
		});
	}

	@Test
	public void testInvalidNewsagentPassword_TooLong() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentPassword("ThisIsAVeryLongPassword123!"); // Password too long (more than 15 characters)
		});
	}

	@Test
	public void testInvalidNewsagentPassword_NoNumberOrSpecialChar() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentPassword("Password"); // Missing number or special character
		});
	}

	// extra tests

	// newsagent name with numbers
	@Test
	public void testInvalidNewsagentName_ContainsNumbers() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentName("John123");
		});
	}

	// newsagent name with special characters
	@Test
	public void testInvalidNewsagentName_ContainsSpecialCharacters() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentName("John@Doe");
		});
	}

	// password with only special characters
	@Test
	public void testInvalidNewsagentPassword_OnlySpecialChars() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentPassword("!!!!!!!!");
		});
	}

	// password with only numbers
	@Test
	public void testInvalidNewsagentPassword_OnlyNumbers() {
		assertThrows(RuntimeException.class, () -> {
			newsagent.validateNewsagentPassword("12345678");
		});
	}
}
