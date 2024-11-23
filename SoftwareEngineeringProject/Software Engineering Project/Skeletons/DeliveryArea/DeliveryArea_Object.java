public class DeliveryArea {

    private String eircode;
    private String area_id;
    private String area_name;
    private DeliveryDocket delivery_docket;

    public DeliveryArea() {}

    public DeliveryArea(String eircode, String area_id, String area_name, DeliveryDocket dv) {
        this.eircode = eircode;
        this.area_id = area_id;
        this.area_name = area_name;
        this.delivery_docket = dv;
    }

    public String getArea_name() {

    }

    public String getArea_id() {

    }

    public String getEircode() {

    }

    public DeliveryDocket getDelivery_docket() {

    }

    public void setArea_name() {

    }

    public void setArea_id() {

    }

    public void setEircode() {

    }

    public void setDelivery_docket() {

    }

    public boolean verifyEircode() {

    }

    public boolean verifyArea_id() {

    }

    public boolean verifyAreaName() {

    }
}
