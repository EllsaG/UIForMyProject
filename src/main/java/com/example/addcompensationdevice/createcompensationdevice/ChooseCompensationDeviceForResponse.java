package com.example.addcompensationdevice.createcompensationdevice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseCompensationDeviceForResponse {
    private long id;
    private double minPowerOfCompensatingDevice;
    private double maxPowerOfCompensatingDevice;
}
