package com.example.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
@Getter
@Setter
@EqualsAndHashCode
public class LightChooseLuminaireResponse {

    HashMap<Integer, HashMap<Double,Double>> lightFluxAtAmountOfLamps;

}
