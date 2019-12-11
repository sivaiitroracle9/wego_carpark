package com.wego.webservices.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarparkData {
    @JsonProperty("carpark_info")
    private List<CarparkInfo> carparkInfo;

    @JsonProperty("carpark_number")
    private String carparkNumber;

    @JsonProperty("update_datetime")
    private String updateTime;

    public List<CarparkInfo> getCarparkInfo() {
        return carparkInfo;
    }

    public void setCarparkInfo(List<CarparkInfo> carparkInfo) {
        this.carparkInfo = carparkInfo;
    }

    public String getCarparkNumber() {
        return carparkNumber;
    }

    public void setCarparkNumber(String carparkNumber) {
        this.carparkNumber = carparkNumber;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
