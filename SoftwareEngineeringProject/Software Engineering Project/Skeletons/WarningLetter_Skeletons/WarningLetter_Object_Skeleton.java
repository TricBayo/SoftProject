// package warning_letter;

// Import the monthly invoice class - multilevel inheritance 
// Warning Letter inherits both Monthly Invoice and Customer Profile methods//
//public class WarningLetter extends MonthlyInvoice {

// --------------- Attributes of WarningLetter class --------------- //

//  boolean outstandingOrdersWitheld = false;
//  boolean arrearsPaymentFullfilled = false;
//  double amountInDebt;

// --------------- Constructor --------------- //

    // Constructor for WarningLetter
//    public WarningLetter(String invoiceID, double invoiceAmount, String cusProfId, 
//                         String name, String postCode, String phoneNumber, String email, 
//                         int monthlyPaymentStatus, String invoiceDeadline, 
//                         boolean outstandingOrdersWitheld, boolean arrearsPaymentFullfilled, 
//                         double amountInDebt) {
//        
        // Call the constructor of MonthlyInvoice to initialize inherited attributes
//        super(invoiceID, invoiceAmount, cusProfId, name, postCode, phoneNumber, email, 
//              monthlyPaymentStatus, invoiceDeadline); 
        
        // Initialize WarningLetter specific attributes
//        this.outstandingOrdersWitheld = outstandingOrdersWitheld;
//        this.arrearsPaymentFullfilled = arrearsPaymentFullfilled;
//        this.amountInDebt = amountInDebt;
//    }

// --------------- Getters and Setters for WarningLetter specific attributes --------------- //

//    public boolean isOutstandingOrdersWitheld() {
//        return outstandingOrdersWitheld;
//    }

//    public void setOutstandingOrdersWitheld(boolean outstandingOrdersWitheld) {
//        this.outstandingOrdersWitheld = outstandingOrdersWitheld;
//    }

//    public boolean isArrearsPaymentFullfilled() {
//        return arrearsPaymentFullfilled;
//    }

//    public void setArrearsPaymentFullfilled(boolean arrearsPaymentFullfilled) {
//        this.arrearsPaymentFullfilled = arrearsPaymentFullfilled;
//    }

//   public double getAmountInDebt() {
//        return amountInDebt;
//    }

//    public void setAmountInDebt(double amountInDebt) {
//        this.amountInDebt = amountInDebt;
//    }

// --------------- Print Warning Letter details method --------------- //

//    public String printWarningLetterDetails() {
//        return "Customer ID: " + getCusProfId() + "\n" +
//               "Customer Name: " + getName() + "\n" +
//               "Customer Postcode: " + getPostcode() + "\n" +
//               "Customer Phone Number: " + getPhoneNumber() + "\n" +
//               "Customer Email: " + getEmail() + "\n" +
//               "Invoice ID: " + getInvoiceID() + "\n" +
//               "Invoice Amount: " + getInvoiceAmount() + "\n" +
//               "Invoice Deadline: " + getInvoiceDeadline() + "\n" +
//               "Monthly Payment Status: " + getMonthlyPaymentStatus() + "\n" +
//               "Outstanding Orders Withheld: " + outstandingOrdersWitheld + "\n" +
//               "Arrears Payment Fulfilled: " + arrearsPaymentFullfilled + "\n" +
//               "Amount in Debt: " + amountInDebt;
//    }

// ------------------------------- Validation Methods (if needed) -------------------------------- //

// WarningLetter validation methods

//    public static void validateAmountInDebt(double amountInDebt) throws ExceptionHandler {
//        
//    }

// not sure about validation of booleans here 

//}


//}
