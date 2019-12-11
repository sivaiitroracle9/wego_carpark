package com.wego.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NearestCarpark {

    private String address;
    private double latitude;
    private double longitude;
    @JsonProperty("total_lots")
    private int totalLots;
    @JsonProperty("available_lots")
    private int availableLots;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public int getAvailableLots() {
        return availableLots;
    }

    public void setAvailableLots(int availableLots) {
        this.availableLots = availableLots;
    }
}
