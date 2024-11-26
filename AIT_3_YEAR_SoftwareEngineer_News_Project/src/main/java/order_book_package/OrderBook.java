package order_book_package;

import for_all_entities_package.EntitiesExceptionHandler;

public class OrderBook {

	private int customerId;
	private int publicationId;

// ----------------------- Constructors ------------------------ //

	public OrderBook() {

		// No-argument constructor
	}

	public OrderBook(int customerId, int publicationId) throws EntitiesExceptionHandler {

		// Validate Input
		try {

			validateCustomerId(customerId);
			validatePublicationId(publicationId);

		} catch (EntitiesExceptionHandler e) {

			throw e;
		}

		this.customerId = customerId;
		this.publicationId = publicationId;

	}

// ----------------------- Getters ------------------------ //

	public int getCustomerId() {

		return this.customerId;
	}

	public int getPublicationId() {

		return this.publicationId;
	}

// ----------------- Attributes Validating Method ----------------- //

	public boolean validateCustomerId(int customerId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (customerId <= 0) {
			throw new EntitiesExceptionHandler("Customer ID must be greater than 0");

		} else {

			result = true;
		}

		return result;
	}

	public boolean validatePublicationId(int publicationId) throws EntitiesExceptionHandler {

		boolean result = false;

		if (publicationId <= 0) {
			throw new EntitiesExceptionHandler("Publication ID must be greater than 0");

		} else {

			result = true;
		}

		return result;
	}

}
