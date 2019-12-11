package com.wego.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CarparkAvailability implements Serializable {
    private LocalDateTime timestamp;
    private String carparkNumber;
    private String totalLots;
    private String lotType;
    private String lotsAvailable;
    private LocalDateTime updateTime;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getCarparkNumber() {
        return carparkNumber;
    }

    public void setCarparkNumber(String carparkNumber) {
        this.carparkNumber = carparkNumber;
    }

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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (object != null && !(object instanceof CarparkAvailability)) {
            return this.carparkNumber.equals(((CarparkAvailability) object).carparkNumber);
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return this.carparkNumber.hashCode();
    }
}
