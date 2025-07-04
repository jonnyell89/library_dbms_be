package com.example.library_dbms_be.dtos;

public class AddressResponseDTO {
    private Long addressId;
    private String line1;
    private String line2;
    private String city;
    private String county;
    private String postcode;

    public AddressResponseDTO() {};

    public AddressResponseDTO(Long addressId, String line1, String line2, String city, String county, String postcode) {
        this.addressId = addressId;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "AddressResponseDTO{" +
                "addressId=" + addressId +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
