package warning_letter_package;

public class WarningLetter {

	private String name;
	private String postcode;
	private double amountInDebt;
	private int paymentStatus;

	public WarningLetter(String name, String postcode, double amountInDebt, int paymentStatus) {

		this.name = name;
		this.postcode = postcode;
		this.amountInDebt = amountInDebt;
		this.paymentStatus = paymentStatus;

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

	public double getAmountInDebt() {
		return amountInDebt;
	}

	public void setAmountInDebt(double amountInDebt) {
		this.amountInDebt = amountInDebt;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "WarningLetter [name=" + name + ", postcode=" + postcode + ", amountInDebt=" + amountInDebt + ", paymentStatus=" + paymentStatus + "]";
	}

}