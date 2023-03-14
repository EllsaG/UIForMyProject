package com.example.addallfullinformation.createfullinformation;

public class ListInputEquipment {
    private Long numberOfBusbar;
    private Long numbersOfEquipment;
    private Integer amountOfEquipments;

    public ListInputEquipment(Long numberOfBusbar, Long numbersOfEquipment, Integer amountOfEquipments) {
        this.numberOfBusbar = numberOfBusbar;
        this.numbersOfEquipment = numbersOfEquipment;
        this.amountOfEquipments = amountOfEquipments;
    }

    public ListInputEquipment() {
    }

    public Long getNumberOfBusbar() {
        return numberOfBusbar;
    }

    public void setNumberOfBusbar(Long numberOfBusbar) {
        this.numberOfBusbar = numberOfBusbar;
    }

    public Long getNumbersOfEquipment() {
        return numbersOfEquipment;
    }

    public void setNumbersOfEquipment(Long numbersOfEquipment) {
        this.numbersOfEquipment = numbersOfEquipment;
    }

    public Integer getAmountOfEquipments() {
        return amountOfEquipments;
    }

    public void setAmountOfEquipments(Integer amountOfEquipments) {
        this.amountOfEquipments = amountOfEquipments;
    }

    @Override
    public String toString() {
        return "ListInputEquipment{" +
                "numberOfBusbar=" + numberOfBusbar +
                ", numberOfEquipment=" + numbersOfEquipment +
                ", amountOfEquipment=" + amountOfEquipments +
                '}';
    }
}
