package com.example.response;

public class ListInputEquipmentResponse {
    private long id;
    private long fullInformationId;
    private long startInformId;
    private String name;
    private double power;
    private double powerOfGroup;
    private int amount;
    private double ki;
    private double cosf;
    private double tgf;
    private double avgDailyActivePower;
    private double avgDailyReactivePower;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFullInformationId() {
        return fullInformationId;
    }

    public void setFullInformationId(long fullInformationId) {
        this.fullInformationId = fullInformationId;
    }

    public long getStartInformId() {
        return startInformId;
    }

    public void setStartInformId(long startInformId) {
        this.startInformId = startInformId;
    }

    public double getPowerOfGroup() {
        return powerOfGroup;
    }

    public void setPowerOfGroup(double powerOfGroup) {
        this.powerOfGroup = powerOfGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getKi() {
        return ki;
    }

    public void setKi(double ki) {
        this.ki = ki;
    }

    public double getCosf() {
        return cosf;
    }

    public void setCosf(double cosf) {
        this.cosf = cosf;
    }

    public double getTgf() {
        return tgf;
    }

    public void setTgf(double tgf) {
        this.tgf = tgf;
    }

    public double getAvgDailyActivePower() {
        return avgDailyActivePower;
    }

    public void setAvgDailyActivePower(double avgDailyActivePower) {
        this.avgDailyActivePower = avgDailyActivePower;
    }

    public double getAvgDailyReactivePower() {
        return avgDailyReactivePower;
    }

    public void setAvgDailyReactivePower(double avgDailyReactivePower) {
        this.avgDailyReactivePower = avgDailyReactivePower;
    }
}
