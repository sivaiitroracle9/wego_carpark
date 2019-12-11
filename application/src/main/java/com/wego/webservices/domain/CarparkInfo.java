package com.wego.webservices.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarparkInfo {

    @JsonProperty("total_lots")
    private String totalLots;

    @JsonProperty("lot_type")
    private String lotType;

    @JsonProperty("lots_available")
    private String lotsAvailable;

    public String getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(String totalLots) {
        this.totalLots = totalLots;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public String getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(String lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }
}
