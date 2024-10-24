package publication_package;

public class Publication {

	private String name;
	private String date;
	private int stock;
	private double price;

	public Publication(String name, String date, int stock, double price) {

		this.name = name;
		this.date = date;
		this.stock = stock;
		this.price = price;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Publication [name=" + name + ", date=" + date + ", stock=" + stock + ", price=" + price + "]";
	}

}
