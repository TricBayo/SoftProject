package monthly_invoice_package;

public class MonthyInvoice {

	private String name;
	private String postcode;
	private String paymentDate;
	private double amountToPay;

	public MonthyInvoice(String name, String postcode, String paymentDate, double amountToPay) {

		this.name = name;
		this.postcode = postcode;
		this.paymentDate = paymentDate;
		this.amountToPay = amountToPay;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}

	@Override
	public String toString() {
		return "MonthyInvoice [name=" + name + ", postcode=" + postcode + ", paymentDate=" + paymentDate + ", amountToPay=" + amountToPay + "]";
	}

}
