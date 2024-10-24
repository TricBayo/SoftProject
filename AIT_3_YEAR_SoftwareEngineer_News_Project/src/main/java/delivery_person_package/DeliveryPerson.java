package delivery_person_package;

public class DeliveryPerson {

	private String name;
	private String phoneNumber;
	private String areaId;
	private String postcode;
	private String password;

	public DeliveryPerson(String name, String phoneNumber, String areaId, String postcode, String password) {

		this.name = name;
		this.phoneNumber = phoneNumber;
		this.areaId = areaId;
		this.postcode = postcode;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DeliveryPerson [name=" + name + ", phoneNumber=" + phoneNumber + ", areaId=" + areaId + ", postcode=" + postcode + ", password=" + password + "]";
	}

}
