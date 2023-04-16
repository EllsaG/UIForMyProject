package com.example.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartInformationResponse {
    private long startInformId;
    private String name;
    private double power;
    private int amount;
    private double ki;
    private double cosf;
    private double tgf;
    private double avgDailyActivePower;
    private double avgDailyReactivePower;

}
