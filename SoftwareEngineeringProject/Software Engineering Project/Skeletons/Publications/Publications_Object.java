public class Publication {

    private String editionID;
    private Datetime editionDate;
    private double price;

    public Publication() {

    }

    public Publication(String editionID, Date editionDate, double price) {
        this.editionDate = editionDate;
        this.editionID = editionID;
        this.price = price;
    }

    //getters and setters

    public Datetime getEditionDate() {
        return editionDate;
    }

    public double getPrice() {
        return price;
    }

    public String getEditionID() {
        return editionID;
    }

    public void setEditionDate(Datetime editionDate) {
        this.editionDate = editionDate;
    }

    public void setEditionID(String editionID) {
        this.editionID = editionID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean validateEditionId() {

    }

    public boolean validateEditionDate() {

    }

    public void verifyPrice() {

    }

}

