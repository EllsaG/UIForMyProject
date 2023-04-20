package com.example.response;

import com.example.addcompensationdevice.createcompensationdevice.ChooseCompensationDeviceForResponse;
import com.example.addcompensationdevice.createcompensationdevice.CompensationDeviceForResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompensationDeviceResponse {

    List<CompensationDeviceForResponse> compensationDeviceList;
    List<ChooseCompensationDeviceForResponse> forChooseCompensationDeviceList;


}
