package com.example.response;

import com.example.powertransformer.createpowertransformer.ChoosePowerTransformerForResponse;
import com.example.powertransformer.createpowertransformer.PowerTransformerForResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PowerTransformerResponse {

    List<PowerTransformerForResponse> powerTransformersList;
    List <ChoosePowerTransformerForResponse> forChooseTransformersList;


}
