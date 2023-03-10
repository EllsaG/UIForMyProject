package com.example.addallstartinfo.createstartinformation;

public class ForRequestStartInformation {
    private Long startInformId;
    private String name;
    private double power;
    private int amount;
    private double ki;
    private double cosf;
    private double tgf;

    public ForRequestStartInformation(Long startInformId, String name, double power, int amount, double ki, double cosf, double tgf) {
        this.startInformId = startInformId;
        this.name = name;
        this.power = power;
        this.amount = amount;
        this.ki = ki;
        this.cosf = cosf;
        this.tgf = tgf;
    }



    public ForRequestStartInformation() {
    }

    public Long getStartInformId() {
        return startInformId;
    }

    public void setStartInformId(Long startInformId) {
        this.startInformId = startInformId;
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

    @Override
    public String toString() {
        return "ForRequest{" +
                "startInformId=" + startInformId +
                ", name='" + name + '\'' +
                ", power=" + power +
                ", amount=" + amount +
                ", ki=" + ki +
                ", cosf=" + cosf +
                ", tgf=" + tgf +
                '}';
    }
}
