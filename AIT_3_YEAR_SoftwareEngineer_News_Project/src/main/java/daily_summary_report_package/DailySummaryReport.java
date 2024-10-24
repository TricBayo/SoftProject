package daily_summary_report_package;

public class DailySummaryReport {

	private String date;
	private int stock;

	public DailySummaryReport(String date, int stock) {

		this.date = date;
		this.stock = stock;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "DailySummaryReport [date=" + date + ", stock=" + stock + "]";
	}

}
