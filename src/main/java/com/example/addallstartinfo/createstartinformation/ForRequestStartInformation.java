package com.example.addallstartinfo.createstartinformation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForRequestStartInformation {

    private long startInformId;
    private String name;
    private double power;
    private int amount;
    private double ki;
    private double cosf;
    private double tgf;

}
