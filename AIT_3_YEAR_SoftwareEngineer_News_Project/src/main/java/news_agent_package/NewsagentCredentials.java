package news_agent_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class NewsagentCredentials {

	private String newsagentName;
	private String password;

	// ----------------------- Constructors ------------------------ //

	public NewsagentCredentials() {

		// No-argument constructor

	}

	public NewsagentCredentials(String newsagentName, String password) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateName(newsagentName);
			validateNewsagentPassword(password);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.newsagentName = newsagentName;
		this.password = password;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getNewsagentName() {

		return newsagentName;
	}

	public void setNewsagentName(String newsagentName) throws EntitiesExceptionHandler {

		validateName(newsagentName);
		this.newsagentName = newsagentName;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) throws EntitiesExceptionHandler {

		validateNewsagentPassword(password);
		this.password = password;
	}

	// ----------------- Attributes Validating Methods ----------------- //

	public boolean validateName(String newsagentName) throws EntitiesExceptionHandler {

		boolean result = false;

		if (newsagentName == null || newsagentName.isBlank()) {
			throw new EntitiesExceptionHandler("Newsagent Name NOT specified");

		} else if (newsagentName.length() < 2) {
			throw new EntitiesExceptionHandler("Newsagent Name does not meet minimum length requirements");

		} else if (newsagentName.length() > 50) {
			throw new EntitiesExceptionHandler("Newsagent Name exceeds maximum length requirements");

		} else if (!newsagentName.matches("[a-zA-Z ]+")) { // Only allows letters and spaces
			throw new EntitiesExceptionHandler("Newsagent Name contains invalid characters");

		} else {
			result = true;
		}

		return result;
	}

	public boolean validateNewsagentPassword(String password) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to ensure password contains at least one letter and one number or
		// special character
		String passwordRegex = "^(?=.*[A-Za-z])(?=.*[0-9!@#$%^&*]).{8,15}$";
		Pattern pattern = Pattern.compile(passwordRegex);
		Matcher matcher = pattern.matcher(password);

		if (password == null || password.isBlank()) {
			throw new EntitiesExceptionHandler("Password NOT specified");

		} else if (password.length() < 8 || password.length() > 15) {
			throw new EntitiesExceptionHandler("Password must be between 8 and 15 characters");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Password must contain at least one letter and one number or special character");

		} else {
			result = true;
		}

		return result;
	}
}
