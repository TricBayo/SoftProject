package delivery_docket_for_newsagent_package;

public class DeliveryDocket {

	private String customerName;
	private String deliveryPersonName;
	private String orderDate;
	private String postcode;
	private String trackNumber;

	public DeliveryDocket(String customerName, String deliveryPersonName, String orderDate, String postcode, String trackNumber) {

		this.customerName = customerName;
		this.deliveryPersonName = deliveryPersonName;
		this.orderDate = orderDate;
		this.postcode = postcode;
		this.trackNumber = trackNumber;

	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryPersonName() {
		return deliveryPersonName;
	}

	public void setDeliveryPersonName(String deliveryPersonName) {
		this.deliveryPersonName = deliveryPersonName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	@Override
	public String toString() {
		return "DeliveryDocket [customerName=" + customerName + ", deliveryPersonName=" + deliveryPersonName + ", orderDate=" + orderDate + ", postcode=" + postcode + ", trackNumber=" + trackNumber + "]";
	}

}
