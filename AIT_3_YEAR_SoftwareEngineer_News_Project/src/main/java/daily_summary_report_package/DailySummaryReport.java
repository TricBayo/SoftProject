package daily_summary_report_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import for_all_entities_package.EntitiesExceptionHandler;

public class DailySummaryReport {

	private String date;
	private int stock;

	// ----------------------- Constructors ------------------------ //

	public DailySummaryReport() {

		// No-argument constructor
	}

	public DailySummaryReport(String date, int stock) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateDate(date);
			validateStockAmount(stock);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.date = date;
		this.stock = stock;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public String getDate() {

		return date;
	}

	public void setDate(String date) throws EntitiesExceptionHandler {

		validateDate(date);
		this.date = date;
	}

	public int getStock() {

		return stock;
	}

	public void setStock(int stock) throws EntitiesExceptionHandler {

		validateStockAmount(stock);
		this.stock = stock;
	}

	// ----------------- Attributes Validating Method ----------------- //

	public boolean validateDate(String date) throws EntitiesExceptionHandler {

		boolean result = false;

		// Regex to validate date format as YYYY-MM-DD
		String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(date);

		if (date.isBlank() || date.isEmpty()) {
			throw new EntitiesExceptionHandler("Report Date NOT specified");

		} else if (!matcher.matches()) {
			throw new EntitiesExceptionHandler("Report Date format NOT valid. Expected format: YYYY-MM-DD");

		}
		return result;
	}

	public boolean validateStockAmount(int stock) throws EntitiesExceptionHandler {

		boolean result = false;

		if (stock < 0) {
			throw new EntitiesExceptionHandler("Stock cannot be negative");

		} else if (stock == 0) {
			throw new EntitiesExceptionHandler("Stock is zero now");

		} else if (stock < 4) {
			throw new EntitiesExceptionHandler("Stock is low, less than 4 items: " + stock);

		} else {
			result = true;

		}

		return result;
	}
}
