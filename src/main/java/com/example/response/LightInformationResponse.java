package com.example.response;

import java.util.HashMap;

public class LightInformationResponse {

    HashMap<Integer, HashMap<Double,Double>> lightFluxAtAmountOfLamps;

    public LightInformationResponse() {
    }

    public HashMap<Integer, HashMap<Double, Double>> getLightFluxAtAmountOfLamps() {
        return lightFluxAtAmountOfLamps;
    }

    public void setLightFluxAtAmountOfLamps(HashMap<Integer, HashMap<Double, Double>> lightFluxAtAmountOfLamps) {
        this.lightFluxAtAmountOfLamps = lightFluxAtAmountOfLamps;
    }
}
