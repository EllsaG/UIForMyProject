package com.example.compensationdevice.createcompensationdevice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompensationDeviceForResponse {
    private long id;
    private String modelOfCompensationDevice;
    private double reactivePowerOfCompensationDevice;
}
