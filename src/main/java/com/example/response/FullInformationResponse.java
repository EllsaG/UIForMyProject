package com.example.response;

import java.util.List;

public class FullInformationResponse {
    private Long id;
    private String nameOfBusbar;
    private Integer amount;
    private Double avgDailyActivePower;
    private Double avgDailyReactivePower;
    private Integer effectiveAmountOfEquipment;
    private Double coefficientMax;
    private Double maxActivePower;
    private Double maxReactivePower;
    private Double maxFullPower;
    private Double maxElectricCurrent;
    private Double powerOfGroup;
    private Double cosF;
    private Double tgF;
    private Double ki;
    private Double module;
    private List<ListInputEquipmentResponse> fullStartInformId;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getAvgDailyActivePower() {
        return avgDailyActivePower;
    }

    public void setAvgDailyActivePower(Double avgDailyActivePower) {
        this.avgDailyActivePower = avgDailyActivePower;
    }

    public Double getAvgDailyReactivePower() {
        return avgDailyReactivePower;
    }

    public void setAvgDailyReactivePower(Double avgDailyReactivePower) {
        this.avgDailyReactivePower = avgDailyReactivePower;
    }

    public Integer getEffectiveAmountOfEquipment() {
        return effectiveAmountOfEquipment;
    }

    public void setEffectiveAmountOfEquipment(Integer effectiveAmountOfEquipment) {
        this.effectiveAmountOfEquipment = effectiveAmountOfEquipment;
    }

    public Double getCoefficientMax() {
        return coefficientMax;
    }

    public void setCoefficientMax(Double coefficientMax) {
        this.coefficientMax = coefficientMax;
    }

    public Double getMaxActivePower() {
        return maxActivePower;
    }

    public void setMaxActivePower(Double maxActivePower) {
        this.maxActivePower = maxActivePower;
    }

    public Double getMaxReactivePower() {
        return maxReactivePower;
    }

    public void setMaxReactivePower(Double maxReactivePower) {
        this.maxReactivePower = maxReactivePower;
    }

    public Double getMaxFullPower() {
        return maxFullPower;
    }

    public void setMaxFullPower(Double maxFullPower) {
        this.maxFullPower = maxFullPower;
    }

    public Double getMaxElectricCurrent() {
        return maxElectricCurrent;
    }

    public void setMaxElectricCurrent(Double maxElectricCurrent) {
        this.maxElectricCurrent = maxElectricCurrent;
    }

    public Double getPowerOfGroup() {
        return powerOfGroup;
    }

    public void setPowerOfGroup(Double powerOfGroup) {
        this.powerOfGroup = powerOfGroup;
    }

    public Double getCosF() {
        return cosF;
    }

    public void setCosF(Double cosF) {
        this.cosF = cosF;
    }

    public Double getTgF() {
        return tgF;
    }

    public void setTgF(Double tgF) {
        this.tgF = tgF;
    }

    public Double getKi() {
        return ki;
    }

    public void setKi(Double ki) {
        this.ki = ki;
    }

    public Double getModule() {
        return module;
    }

    public void setModule(Double module) {
        this.module = module;
    }

    public List<ListInputEquipmentResponse> getFullStartInformId() {
        return fullStartInformId;
    }

    public void setFullStartInformId(List<ListInputEquipmentResponse> fullStartInformId) {
        this.fullStartInformId = fullStartInformId;
    }
}
