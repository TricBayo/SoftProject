package news_agent_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class NewsagentCredentials {

	private String name;
	private String password;

	// ----------------------- Constructors ------------------------ //

	public NewsagentCredentials() {

		// No-argument constructor

	}

	public NewsagentCredentials(String name, String password) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateNewsagentName(name);
			validateNewsagentPassword(password);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.name = name;
		this.password = password;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getName() {

		return name;
	}

	public void setName(String name) throws EntitiesExceptionHandler {

		validateNewsagentName(name);
		this.name = name;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) throws EntitiesExceptionHandler {

		validateNewsagentPassword(password);
		this.password = password;
	}

	// ----------------- Attributes Validating Methods ----------------- //

	public boolean validateNewsagentName(String name) throws EntitiesExceptionHandler {

		boolean result = false;

		if (name == null || name.isBlank()) {
			throw new EntitiesExceptionHandler("Newsagent Name NOT specified");

		} else if (name.length() < 2) {
			throw new EntitiesExceptionHandler("Newsagent Name does not meet minimum length requirements");

		} else if (name.length() > 50) {
			throw new EntitiesExceptionHandler("Newsagent Name exceeds maximum length requirements");

		} else {
			result = true;
		}

		return result;
	}

	public boolean validateNewsagentPassword(String password) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to ensure password contains at least one number or special character
		String passwordRegex = "^(?=.*[0-9!@#$%^&*]).{8,15}$";
		Pattern pattern = Pattern.compile(passwordRegex);
		Matcher matcher = pattern.matcher(password);

		if (password == null || password.isBlank()) {
			throw new EntitiesExceptionHandler("Password NOT specified");

		} else if (password.length() < 8 || password.length() > 15) {
			throw new EntitiesExceptionHandler("Password must be between 8 and 15 characters");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Password must contain at least one number or special character");

		} else {
			result = true;

		}

		return result;
	}
}
