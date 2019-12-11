package com.wego.webservices.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarparkAvailability {

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("carpark_data")
    private List<CarparkData> carparkData;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<CarparkData> getCarparkData() {
        return carparkData;
    }

    public void setCarparkData(List<CarparkData> carparkData) {
        this.carparkData = carparkData;
    }
}
