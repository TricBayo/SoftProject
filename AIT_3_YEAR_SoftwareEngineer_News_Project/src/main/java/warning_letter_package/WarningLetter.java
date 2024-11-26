package warning_letter_package;

import for_all_entities_package.EntitiesExceptionHandler;

public class WarningLetter {

	private int customerId;
	private int deliveryAreaId;

	// ----------------------- Constructors ------------------------ //

	public WarningLetter() {

		// No-argument constructor
	}

	public WarningLetter(int customerId, int deliveryAreaId) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateCustomerId(customerId);
			validateAreaId(deliveryAreaId);

		} catch (EntitiesExceptionHandler e) {
			throw e;

		}

		this.customerId = customerId;
		this.deliveryAreaId = deliveryAreaId;
	}

	// ----------------------- Getter and Setters ------------------------ //

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getDeliveryAreaId() {
		return deliveryAreaId;
	}

	public void setDeliveryAreaId(int deliveryAreaId) {
		this.deliveryAreaId = deliveryAreaId;
	}

	// ----------------- Attributes Validating Methods ----------------- //
	public boolean validateCustomerId(int customerId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (customerId <= 0) {
			throw new EntitiesExceptionHandler("Customer ID must be greater than 0");

		} else {

			result = true;
		}

		return result;
	}

	public boolean validateAreaId(int areaId) throws EntitiesExceptionHandler {

		boolean result = false;

		String areaIdString = String.valueOf(areaId);

		// Check if the areaId is null or blank before parsing
		if (areaIdString == null || areaIdString.isBlank()) {
			throw new EntitiesExceptionHandler("Area Id Number NOT specified");

		}

		int areaIdNumber;

		try {
			areaIdNumber = Integer.parseInt(areaIdString);

		} catch (NumberFormatException e) {
			throw new EntitiesExceptionHandler("Area Id must be a valid number");

		}

		// Additional checks for areaIdNumber
		if (areaIdNumber <= 0) {
			throw new EntitiesExceptionHandler("Area Id must be greater than zero");

		} else if (areaIdNumber > 24) {
			throw new EntitiesExceptionHandler("Area Id must be less than or equal to 24");

		} else {
			result = true;

		}

		return result;
	}

}
