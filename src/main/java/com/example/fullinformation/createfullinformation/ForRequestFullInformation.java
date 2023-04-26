package com.example.fullinformation.createfullinformation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForRequestFullInformation {

    private long id;
    private String nameOfBusbar;
    private List<ListInputEquipment> numbersAndAmountOfEquipments;

}
