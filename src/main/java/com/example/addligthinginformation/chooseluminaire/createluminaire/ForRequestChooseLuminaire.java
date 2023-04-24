package com.example.addligthinginformation.chooseluminaire.createluminaire;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForRequestChooseLuminaire {

    private long lightingId;
    private double heightProductionHall;
    private double widthProductionHall;
    private double lengthProductionHall;

}
