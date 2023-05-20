package com.example.protectiveequpment.createpowertransformer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForRequestPowerTransformer {
    private long id;
    private String modelOfTransformer;
    private double fullPowerOfTransformer;
    private double shortCircuitVoltage;
    private double idleLossesOfTransformer;
    private double highSideVoltage;
    private double lowSideVoltage;
    private double shortCircuitLosses;
    private double idleCurrent;
}
