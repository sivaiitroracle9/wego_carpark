package com.wego.db.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CARPARK")
public class CarparkEntity {

    @Id
    @Column(name = "carpark_number")
    private String carparkNumber;
    @NotBlank
    private String address;
    @NotBlank
    @Column(name = "x_coordinate")
    private double xcoordinate;
    @NotBlank
    @Column(name = "y_coordinate")
    private double ycoordinate;
    @NotBlank
    private double latitude;
    @NotBlank
    private double longitude;
    @NotBlank
    @Column(name = "carpark_type")
    private String carParkType;
    @NotBlank
    @Column(name = "type_of_parking_system")
    private String typeOfParkingSystem;
    @NotBlank
    @Column(name = "short_term_parking")
    private String shortTermParking;
    @NotBlank
    @Column(name = "free_parking")
    private String freeParking;
    @NotBlank
    @Column(name = "night_parking")
    private String nightParking;
    @NotBlank
    @Column(name = "carpark_decks")
    private String carparkDecks;
    @NotBlank
    @Column(name = "gantry_height")
    private double gantryHeight;
    @NotBlank
    @Column(name = "carpark_basement")
    private String carparkBasement;

    public String getCarparkNumber() {
        return carparkNumber;
    }

    public void setCarparkNumber(String carparkNumber) {
        this.carparkNumber = carparkNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getXcoordinate() {
        return xcoordinate;
    }

    public void setXcoordinate(double xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    public double getYcoordinate() {
        return ycoordinate;
    }

    public void setYcoordinate(double ycoordinate) {
        this.ycoordinate = ycoordinate;
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

    public String getCarParkType() {
        return carParkType;
    }

    public void setCarParkType(String carParkType) {
        this.carParkType = carParkType;
    }

    public String getTypeOfParkingSystem() {
        return typeOfParkingSystem;
    }

    public void setTypeOfParkingSystem(String typeOfParkingSystem) {
        this.typeOfParkingSystem = typeOfParkingSystem;
    }

    public String getShortTermParking() {
        return shortTermParking;
    }

    public void setShortTermParking(String shortTermParking) {
        this.shortTermParking = shortTermParking;
    }

    public String getFreeParking() {
        return freeParking;
    }

    public void setFreeParking(String freeParking) {
        this.freeParking = freeParking;
    }

    public String getNightParking() {
        return nightParking;
    }

    public void setNightParking(String nightParking) {
        this.nightParking = nightParking;
    }

    public String getCarparkDecks() {
        return carparkDecks;
    }

    public void setCarparkDecks(String carparkDecks) {
        this.carparkDecks = carparkDecks;
    }

    public double getGantryHeight() {
        return gantryHeight;
    }

    public void setGantryHeight(double gantryHeight) {
        this.gantryHeight = gantryHeight;
    }

    public String getCarparkBasement() {
        return carparkBasement;
    }

    public void setCarparkBasement(String carparkBasement) {
        this.carparkBasement = carparkBasement;
    }
}
