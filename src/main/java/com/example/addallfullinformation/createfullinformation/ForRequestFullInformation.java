package com.example.addallfullinformation.createfullinformation;


import java.util.List;

public class ForRequestFullInformation {
    private Long id;
    private String nameOfBusbar;

    private List<ListInputEquipment> numbersAndAmountOfEquipments;

    public ForRequestFullInformation(Long id, String nameOfBusbar, List<ListInputEquipment> numbersAndAmountOfEquipments) {
        this.id = id;
        this.nameOfBusbar = nameOfBusbar;
        this.numbersAndAmountOfEquipments = numbersAndAmountOfEquipments;
    }

    public ForRequestFullInformation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfBusbar() {
        return nameOfBusbar;
    }

    public void setNameOfBusbar(String nameOfBusbar) {
        this.nameOfBusbar = nameOfBusbar;
    }

    public List<ListInputEquipment> getNumbersAndAmountOfEquipments() {
        return numbersAndAmountOfEquipments;
    }

    public void setNumbersAndAmountOfEquipments(List<ListInputEquipment> numbersAndAmountOfEquipments) {
        this.numbersAndAmountOfEquipments = numbersAndAmountOfEquipments;
    }

    @Override
    public String toString() {
        return "ForRequestFullInformation{" +
                "id=" + id +
                ", nameOfBusbar='" + nameOfBusbar + '\'' +
                ", numbersAndAmountOfEquipments=" + numbersAndAmountOfEquipments +
                '}';
    }
}
