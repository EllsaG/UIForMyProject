package com.example.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightingCreateNewResponse {

    private long id;
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
    private double cosF;
    private double tgF;


}
