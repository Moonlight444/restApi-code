package com.geo.code.geocodeapi;

import lombok.Data;

@Data
public class GeoCode {

    private String country;
    private String state;
    private String city;
    private String postcode;

    public GeoCode(String country, String state, String city, String postcode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.postcode = postcode;
    }
}
