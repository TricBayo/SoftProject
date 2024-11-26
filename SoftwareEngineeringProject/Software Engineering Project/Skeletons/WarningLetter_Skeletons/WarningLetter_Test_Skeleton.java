
// package warning_letter;


// public class WarningLetterAttributesTests {


// ------------- Attibutes/Acceptance Criterias being checked ------------- //
//   	
//verify valid invoice format is created
//verify warning letter is being generated for customers who's unpaid invoices are ==3
//verify warning letter contains correct customer details 
//verify when warning letter is generated, any outstanding orders for customer are marked as withheld 
//verify when customer pays the arrears, withheld orders are being moved back to order book
//verify that warning letter contains correct code for the area customer is from
//verify that any unpaid amounts on the letter are displayed as double
	
	
// ----------------------------------- Skeletons of Validation Test Methods ------------------------------------ //
//
//  public boolean validateAmountInDebt(double amountInDebt){
//      returns true if amountInDebt is a double 
//	   	Otherwise, it returns false and throws new RuntimeException("Please, enter the correct amount for in debt");
//}
//
//  public boolean validateInvoiceID(String invoiceID){
//      returns true if invoiceID is in the correct range: 1 to 1000 
//	   	Otherwise, it returns false and throws new RuntimeException("Please, enter the correct format for Monthly Invoice ID");
//  }
//
//  public boolean validateInvoiceAmount(double invoiceAmount){
//      returns true if invoiceAmount is a double
//	   	Otherwise, it returns false and throws new RuntimeException("Please, enter the correct amount to pay for Monthly Invoice");
//  }
//
//	 public boolean validateCustomerID(String customerID) {
//	     validateCustomerID returns true if the customerID is in the correct format: 2 letters and 10 digits.
//	   	 Otherwise, it returns false and throws new RuntimeException("Please, enter the correct format for Customer Profile ID");
//	 }
//	
//
//	
//	 public boolean validateName(String name) {
//	     validateName returns true if the name is a String with 3 to 20 characters.
//	     Otherwise, it returns false and throws new RuntimeException("Please, enter the correct format for Customer Profile Name");
//	 }
//	
//	
//
//	 public boolean validatePostcode(String postcode) {
//	     validatePostcode returns true if the postcode follows the format letter N followed by NN LLNN.
//	     Otherwise, it returns false and throws new RuntimeException("Please, enter the correct format for Postcode");
//	 }
//	
//	
//	
//	 public boolean validatePhoneNumber(String phoneNumber) {
//	     validatePhoneNumber returns true if the phone number follows the format +353 00 000 0000.
//	     Otherwise, it returns false and throws new RuntimeException("Please, enter the correct format for Phone Number");
//	 }
//	
//	
//	
//	 public boolean validateEmail(String email) {
//	     validateEmail returns true if the email is in the correct format with a valid domain server.
//	     Otherwise, it returns false and throws new RuntimeException("Please, enter the correct format for Email");
//	 }
//
//	
//	
//	 public boolean validateMonthlyPaymentStatus(int monthsDelayed) {
//	     validateMonthlyPaymentStatus returns true if the payment delay is less than three months.
//	     Otherwise, it returns false and throws new RuntimeException("Please, enter the correct format for Nubers of Months");
//	 }
//
//	 public boolean validateInvoiceDeadline(String invoiceDeadline) {
//	     returns true if the invoice deadline is in format DD/MM/YYYY
//	     Otherwise, it returns false and throws new RuntimeException("Please, enter the correct format for deadline date");
//	 }

// not sure about validating booleans ?????
   	 

// }
