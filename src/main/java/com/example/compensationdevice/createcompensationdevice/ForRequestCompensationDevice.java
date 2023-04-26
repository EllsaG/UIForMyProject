package com.example.compensationdevice.createcompensationdevice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForRequestCompensationDevice {

    long id;
    String nameOfCompensationDevice;
    double powerOfCompensatingDevice;
}
