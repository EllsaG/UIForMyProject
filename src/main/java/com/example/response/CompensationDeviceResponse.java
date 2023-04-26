package com.example.response;

import com.example.compensationdevice.createcompensationdevice.ChooseCompensationDeviceForResponse;
import com.example.compensationdevice.createcompensationdevice.CompensationDeviceForResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompensationDeviceResponse {

    List<CompensationDeviceForResponse> compensationDeviceList;
    List<ChooseCompensationDeviceForResponse> forChooseCompensationDeviceList;


}
