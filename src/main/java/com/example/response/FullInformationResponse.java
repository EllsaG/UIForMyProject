package com.example.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class FullInformationResponse {
    private Long id;
    private String nameOfBusbar;
    private Integer amount;
    private Double avgDailyActivePower;
    private Double avgDailyReactivePower;
    private Integer effectiveAmountOfEquipment;
    private Double coefficientMax;
    private Double maxActivePower;
    private Double maxReactivePower;
    private Double maxFullPower;
    private Double maxElectricCurrent;
    private Double power;
    private Double powerOfGroup;
    private Double cosF;
    private Double tgF;
    private Double ki;
    private Double module;
    private List<ListInputEquipmentResponse> fullStartInformId;


}
