package com.example.powertransformer.createpowertransformer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PowerTransformerForResponse {
    private long id;
    private String modelOfTransformer;
    private double fullPowerOfTransformer;
    private double coefOfTransformerLoad;
    private double shortCircuitVoltage;
    private double idleLossesOfTransformer;
    private double highSideVoltage;
    private double lowSideVoltage;
    private double shortCircuitLosses;
    private double idleCurrent;
}
