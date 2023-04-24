package com.example.addligthinginformation.createligthinformation;

import com.example.response.LightingCreateNewResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForInsertInTableViewLightingInfo {

    List<LightingCreateNewResponse> lightInformationList;

}