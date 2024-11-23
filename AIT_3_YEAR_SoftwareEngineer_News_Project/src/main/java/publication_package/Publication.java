package publication_package;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class Publication {

	private String publication_name;
	private String publication_date;
	private int stock_amount;
	private double edition_price;

	// ----------------------- Constructors ------------------------ //

	public Publication() {

		// No-argument constructor
	}

	public Publication(String publication_name, String publication_date, int stock_amount, double edition_price) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validatePublicationName(publication_name);
			validatePublicationDate(publication_date);
			validateStockAmount(stock_amount);
			validateEditionPrice(edition_price);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.publication_name = publication_name;
		this.publication_date = publication_date;
		this.stock_amount = stock_amount;
		this.edition_price = edition_price;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getPublicationName() {
		return publication_name;
	}

	public void setPublicationName(String publication_name) throws EntitiesExceptionHandler {

		validatePublicationName(publication_name);
		this.publication_name = publication_name;
	}

	public String getPublicationtDate() {
		return publication_date;
	}

	public void setPublicationDate(String publication_date) throws EntitiesExceptionHandler {

		validatePublicationDate(publication_date);
		this.publication_date = publication_date;
	}

	public int getStockAmount() {
		return stock_amount;
	}

	public void setStockAmount(int stock_amount) throws EntitiesExceptionHandler {
		validateStockAmount(stock_amount);
		this.stock_amount = stock_amount;
	}

	public double getEditionPrice() {
		return edition_price;
	}

	public void setEditionPrice(double edition_price) throws EntitiesExceptionHandler {

		validateEditionPrice(edition_price);
		this.edition_price = edition_price;
	}

	// ----------------- Attributes Validating Methods ----------------- //

	public boolean validatePublicationName(String publication_name) throws EntitiesExceptionHandler {

		boolean result = false;

		if (publication_name == null || publication_name.isBlank()) {
			throw new EntitiesExceptionHandler("Publication Name NOT specified");

		} else if (publication_name.length() < 2) {
			throw new EntitiesExceptionHandler("Publication Name does not meet minimum length requirements");

		} else if (publication_name.length() > 50) {
			throw new EntitiesExceptionHandler("Publication Name exceeds maximum length requirements");

		} else if (!publication_name.matches("[a-zA-Z ]+")) { // Only allows letters and spaces
			throw new EntitiesExceptionHandler("Publication Name contains invalid characters");

		} else {
			result = true;
		}

		return result;
	}

	public boolean validatePublicationDate(String publication_date) throws EntitiesExceptionHandler {

		// Regex to validate date format as DD/MM/YYYY
		String dateRegex = "^\\d{2}/\\d{2}/\\d{4}$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(publication_date);

		if (publication_date == null || publication_date.isBlank()) {
			throw new EntitiesExceptionHandler("Publication Date NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Publication Date format NOT valid. Expected format: DD/MM/YYYY");
		}

		// Further validate logical correctness of the date
		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(publication_date, formatter); // This will throw an exception if the date is invalid

		} catch (DateTimeParseException e) {

			throw new EntitiesExceptionHandler("Publication Date is not a valid date.");
		}

		return true; // Return true if the date is valid
	}

	public boolean validateStockAmount(int stock_amount) throws EntitiesExceptionHandler {

		boolean result = true;

		if (stock_amount < 0) {

			result = false;
			throw new EntitiesExceptionHandler("Publication Stock must be a non-negative integer");

		} else if (stock_amount <= 3) {

			System.out.println("Low Stock: " + stock_amount + ". Please, Update Stock Amount by Increasing it to any value greater than 3.");

		} else if (stock_amount > 3) {

			System.out.println("Current Stock: " + stock_amount);
		}

		return result;
	}

	public boolean validateEditionPrice(double edition_price) throws EntitiesExceptionHandler {

		boolean result = false;

		if (edition_price < 0) {
			throw new EntitiesExceptionHandler("Edition Price must be a non-negative value");

		} else {
			result = true;

		}
		return result;
	}
}
