package com.example.addallfullinformation.createfullinformation;

public class ListInputEquipment {
    private long numberOfBusbar;
    private long numbersOfEquipment;
    private int amountOfEquipments;

    public ListInputEquipment(long numberOfBusbar, long numbersOfEquipment, int amountOfEquipments) {
        this.numberOfBusbar = numberOfBusbar;
        this.numbersOfEquipment = numbersOfEquipment;
        this.amountOfEquipments = amountOfEquipments;
    }

    public ListInputEquipment() {
    }

    public long getNumberOfBusbar() {
        return numberOfBusbar;
    }

    public void setNumberOfBusbar(long numberOfBusbar) {
        this.numberOfBusbar = numberOfBusbar;
    }

    public long getNumbersOfEquipment() {
        return numbersOfEquipment;
    }

    public void setNumbersOfEquipment(long numbersOfEquipment) {
        this.numbersOfEquipment = numbersOfEquipment;
    }

    public int getAmountOfEquipments() {
        return amountOfEquipments;
    }

    public void setAmountOfEquipments(int amountOfEquipments) {
        this.amountOfEquipments = amountOfEquipments;
    }


}
