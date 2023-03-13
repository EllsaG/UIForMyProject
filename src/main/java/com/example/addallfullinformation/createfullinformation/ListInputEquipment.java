package com.example.addallfullinformation.createfullinformation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListInputEquipment {
    private Long numberOfBusbar;
    private Long numberOfEquipment;
    private Integer amountOfEquipment;

    public Long getNumberOfBusbar() {
        return numberOfBusbar;
    }

    public void setNumberOfBusbar(Long numberOfBusbar) {
        this.numberOfBusbar = numberOfBusbar;
    }

    public Long getNumberOfEquipment() {
        return numberOfEquipment;
    }

    public void setNumberOfEquipment(Long numberOfEquipment) {
        this.numberOfEquipment = numberOfEquipment;
    }

    public Integer getAmountOfEquipment() {
        return amountOfEquipment;
    }

    public void setAmountOfEquipment(Integer amountOfEquipment) {
        this.amountOfEquipment = amountOfEquipment;
    }
}
