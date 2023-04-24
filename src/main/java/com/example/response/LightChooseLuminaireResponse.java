package com.example.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightChooseLuminaireResponse {

    long id;
    int amountOfLampsInOneLuminaire;
    double minLightFluxForChooseLuminaire;
    double maxLightFluxForChooseLuminaire;

}
