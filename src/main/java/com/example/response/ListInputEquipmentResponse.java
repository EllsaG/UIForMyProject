package com.example.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListInputEquipmentResponse {
    private long id;
    private long fullInformationId;
    private long startInformId;
    private String name;
    private double power;
    private double powerOfGroup;
    private int amount;
    private double ki;
    private double cosf;
    private double tgf;
    private double avgDailyActivePower;
    private double avgDailyReactivePower;


}
