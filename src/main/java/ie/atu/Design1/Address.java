package ie.atu.Design1;

public class Address extends Student {

    private double address_id;
    private String postal_code;
    private String county;
    private String street;

    public Address() {
        super();
        address_id = 0;
        postal_code = "";
        county = "";
        street = "";
    }

    public double getAddress_id() {
        return address_id;
    }

    public void setAddress_id(double address_id) {
        this.address_id = address_id;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
