package for_all_entities_package;

public class EntitiesExceptionHandler extends Exception {

	String message;

	public EntitiesExceptionHandler(String errMessage) {
		message = errMessage;
	}

	public String getMessage() {
		return message;
	}
}
