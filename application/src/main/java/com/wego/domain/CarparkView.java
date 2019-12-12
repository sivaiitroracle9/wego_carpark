package com.wego.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CarparkView implements Serializable {
    private String id;
    private String address;
    @JsonProperty("x_coordinate")
    private double xcoordinate;
    @JsonProperty("y_coordinate")
    private double ycoordinate;
    private double latitude;
    private double longitude;
    @JsonProperty("carpark_type")
    private String carParkType;

    public CarparkView(String id,
                       String address,
                       double xcoordinate,
                       double ycoordinate,
                       double latitude,
                       double longitude,
                       String carParkType) {
        this.id = id;
        this.address = address;
        this.xcoordinate = xcoordinate;
        this.ycoordinate = ycoordinate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.carParkType = carParkType;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getXcoordinate() {
        return xcoordinate;
    }

    public double getYcoordinate() {
        return ycoordinate;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCarParkType() {
        return carParkType;
    }
}
