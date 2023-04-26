package com.example.ligthinginformation.createligthinformation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForRequestCreateNewLighting {

    private long lightingId;
    private String modelOfLuminaire;
    private String modelOfLamp;
    private int amountOfLampsInOneLuminaire;
    private double lightFluxOneLamp;
    private double activePowerOneLamp;
}
