package com.wego.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "CARPARK_AVAILABILITY")
public class CarparkAvailabilityEntity {
    @Id
    @Column(name = "carpark_number")
    private String carparkNumber;

    private LocalDateTime timestamp;

    @Column(name = "total_lots")
    private int totalLots;

    @Column(name = "lot_type")
    private String lotType;

    @Column(name = "lot_available")
    private int lotsAvailable;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public String getCarparkNumber() {
        return carparkNumber;
    }

    public void setCarparkNumber(String carparkNumber) {
        this.carparkNumber = carparkNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public int getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(int lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
