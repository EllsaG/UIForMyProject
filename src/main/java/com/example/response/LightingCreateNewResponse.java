package com.example.response;

public class LightingCreateNewResponse {

    private Long id;
    private String modelOfLuminaire;
    private String modelOfLamp;
    private int amountOfLuminaires;
    private int amountOfLampsInOneLuminaire;
    private double powerOfOneLamp;
    private double lightFluxOfOneLamp;
    private double distanceBetweenRowsOfLamps;
    private double distanceBetweenWallAndFirstRowOfLamps;
    private int amountLuminairesPerLength;
    private int amountLuminairesPerWidth;
    private double activePower;
    private double reactivePower;
    private double fullPower;
    private double electricCurrent;
    private double electricCurrentOfOneRowOfLuminaire;
    private Double cosF;
    private Double tgF;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelOfLuminaire() {
        return modelOfLuminaire;
    }

    public void setModelOfLuminaire(String modelOfLuminaire) {
        this.modelOfLuminaire = modelOfLuminaire;
    }

    public String getModelOfLamp() {
        return modelOfLamp;
    }

    public void setModelOfLamp(String modelOfLamp) {
        this.modelOfLamp = modelOfLamp;
    }

    public int getAmountOfLuminaires() {
        return amountOfLuminaires;
    }

    public void setAmountOfLuminaires(int amountOfLuminaires) {
        this.amountOfLuminaires = amountOfLuminaires;
    }

    public int getAmountOfLampsInOneLuminaire() {
        return amountOfLampsInOneLuminaire;
    }

    public void setAmountOfLampsInOneLuminaire(int amountOfLampsInOneLuminaire) {
        this.amountOfLampsInOneLuminaire = amountOfLampsInOneLuminaire;
    }

    public double getPowerOfOneLamp() {
        return powerOfOneLamp;
    }

    public void setPowerOfOneLamp(double powerOfOneLamp) {
        this.powerOfOneLamp = powerOfOneLamp;
    }

    public double getLightFluxOfOneLamp() {
        return lightFluxOfOneLamp;
    }

    public void setLightFluxOfOneLamp(double lightFluxOfOneLamp) {
        this.lightFluxOfOneLamp = lightFluxOfOneLamp;
    }

    public double getDistanceBetweenRowsOfLamps() {
        return distanceBetweenRowsOfLamps;
    }

    public void setDistanceBetweenRowsOfLamps(double distanceBetweenRowsOfLamps) {
        this.distanceBetweenRowsOfLamps = distanceBetweenRowsOfLamps;
    }

    public double getDistanceBetweenWallAndFirstRowOfLamps() {
        return distanceBetweenWallAndFirstRowOfLamps;
    }

    public void setDistanceBetweenWallAndFirstRowOfLamps(double distanceBetweenWallAndFirstRowOfLamps) {
        this.distanceBetweenWallAndFirstRowOfLamps = distanceBetweenWallAndFirstRowOfLamps;
    }

    public int getAmountLuminairesPerLength() {
        return amountLuminairesPerLength;
    }

    public void setAmountLuminairesPerLength(int amountLuminairesPerLength) {
        this.amountLuminairesPerLength = amountLuminairesPerLength;
    }

    public int getAmountLuminairesPerWidth() {
        return amountLuminairesPerWidth;
    }

    public void setAmountLuminairesPerWidth(int amountLuminairesPerWidth) {
        this.amountLuminairesPerWidth = amountLuminairesPerWidth;
    }

    public double getActivePower() {
        return activePower;
    }

    public void setActivePower(double activePower) {
        this.activePower = activePower;
    }

    public double getReactivePower() {
        return reactivePower;
    }

    public void setReactivePower(double reactivePower) {
        this.reactivePower = reactivePower;
    }

    public double getFullPower() {
        return fullPower;
    }

    public void setFullPower(double fullPower) {
        this.fullPower = fullPower;
    }

    public double getElectricCurrent() {
        return electricCurrent;
    }

    public void setElectricCurrent(double electricCurrent) {
        this.electricCurrent = electricCurrent;
    }

    public double getElectricCurrentOfOneRowOfLuminaire() {
        return electricCurrentOfOneRowOfLuminaire;
    }

    public void setElectricCurrentOfOneRowOfLuminaire(double electricCurrentOfOneRowOfLuminaire) {
        this.electricCurrentOfOneRowOfLuminaire = electricCurrentOfOneRowOfLuminaire;
    }

    public Double getCosF() {
        return cosF;
    }

    public void setCosF(Double cosF) {
        this.cosF = cosF;
    }

    public Double getTgF() {
        return tgF;
    }

    public void setTgF(Double tgF) {
        this.tgF = tgF;
    }
}
