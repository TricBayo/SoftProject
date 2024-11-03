package publication_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class Publication {

	private String name;
	private String date;
	private int stock;
	private double price;

	// ----------------------- Constructors ------------------------ //

	public Publication() {

		// No-argument constructor
	}

	public Publication(String name, String date, int stock, double price) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validatePublicationName(name);
			validatePublicationDate(date);
			validateStockBalance(stock);
			validatePublicationPrice(price);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.name = name;
		this.date = date;
		this.stock = stock;
		this.price = price;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getName() {
		return name;
	}

	public void setName(String name) throws EntitiesExceptionHandler {

		validatePublicationName(name);
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) throws EntitiesExceptionHandler {

		validatePublicationDate(date);
		this.date = date;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) throws EntitiesExceptionHandler {
		validateStockBalance(stock);
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws EntitiesExceptionHandler {

		validatePublicationPrice(price);
		this.price = price;
	}

	// ----------------- Attributes Validating Methods ----------------- //

	public boolean validatePublicationName(String name) throws EntitiesExceptionHandler {

		boolean result = false;

		if (name == null || name.isBlank()) {
			throw new EntitiesExceptionHandler("Publication Name NOT specified");

		} else if (name.length() < 2) {
			throw new EntitiesExceptionHandler("Publication Name must be at least 2 characters long");

		} else if (name.length() > 100) {

			throw new EntitiesExceptionHandler("Publication Name exceeds maximum length of 100 characters");
		} else {
			result = true;

		}

		return result;
	}

	public boolean validatePublicationDate(String date) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to validate date in format YYYY-MM-DD
		String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(date);

		if (date == null || date.isBlank()) {
			throw new EntitiesExceptionHandler("Publication Date NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Publication Date format NOT valid. Expected format: YYYY-MM-DD");

		} else {
			result = true;

		}

		return result;
	}

	public boolean validateStockBalance(int stock) throws EntitiesExceptionHandler {

		boolean result = true;

		if (stock < 0) {

			result = false;
			throw new EntitiesExceptionHandler("Publication Stock must be a non-negative integer");

		} else if (stock >= 4) {

			stock -= 1;

		} else if (stock <= 3) {

			stock += 1;
		}

		return result;
	}

	public boolean validatePublicationPrice(double price) throws EntitiesExceptionHandler {

		boolean result = false;

		if (price < 0) {
			throw new EntitiesExceptionHandler("Publication Price must be a non-negative value");

		} else {
			result = true;

		}
		return result;
	}
}
