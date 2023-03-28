package com.example.addligthinginformation.createligthinformation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForRequestCreateNewLighting {

    private String modelOfLuminaire;
    private String modelOfLamp;
    private int amountOfLampsInOneLuminaire;
    private double lightFluxOneLamp;
    private double activePowerOneLamp;

    public ForRequestCreateNewLighting(String modelOfLuminaire, String modelOfLamp, int amountOfLampsInOneLuminaire,
                                       double lightFluxOneLamp, double activePowerOneLamp) {
        this.modelOfLuminaire = modelOfLuminaire;
        this.modelOfLamp = modelOfLamp;
        this.amountOfLampsInOneLuminaire = amountOfLampsInOneLuminaire;
        this.lightFluxOneLamp = lightFluxOneLamp;
        this.activePowerOneLamp = activePowerOneLamp;
    }

    public ForRequestCreateNewLighting() {
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

    public int getAmountOfLampsInOneLuminaire() {
        return amountOfLampsInOneLuminaire;
    }

    public void setAmountOfLampsInOneLuminaire(int amountOfLampsInOneLuminaire) {
        this.amountOfLampsInOneLuminaire = amountOfLampsInOneLuminaire;
    }

    public double getLightFluxOneLamp() {
        return lightFluxOneLamp;
    }

    public void setLightFluxOneLamp(double lightFluxOneLamp) {
        this.lightFluxOneLamp = lightFluxOneLamp;
    }

    public double getActivePowerOneLamp() {
        return activePowerOneLamp;
    }

    public void setActivePowerOneLamp(double activePowerOneLamp) {
        this.activePowerOneLamp = activePowerOneLamp;
    }
}
