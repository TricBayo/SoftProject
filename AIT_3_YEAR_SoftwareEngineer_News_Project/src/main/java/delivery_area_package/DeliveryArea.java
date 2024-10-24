package delivery_area_package;

public class DeliveryArea {

	private String name;
	private int postcode;

	public DeliveryArea(String name, int postcode) {

		this.name = name;
		this.postcode = postcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return "DeliveryArea [name=" + name + ", postcode=" + postcode + "]";
	}

}
