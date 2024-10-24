package order_book_package;

public class OrderBook {

	private String name;
	private String postcode;
	private String publicationName;

	public OrderBook(String name, String postcode, String publicationName) {

		this.name = name;
		this.postcode = postcode;
		this.publicationName = publicationName;

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

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	@Override
	public String toString() {
		return "MonthyInvoice [name=" + name + ", postcode=" + postcode + ", publicationName=" + publicationName + "]";
	}

}
