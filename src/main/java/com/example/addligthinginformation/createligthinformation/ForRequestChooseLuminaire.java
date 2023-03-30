package com.example.addligthinginformation.createligthinformation;



public class ForRequestChooseLuminaire {
    private long lightingId;
    private double heightProductionHall;
    private double widthProductionHall;
    private double lengthProductionHall;

    public ForRequestChooseLuminaire(long lightingId, double heightProductionHall, double widthProductionHall, double lengthProductionHall) {
        this.lightingId = lightingId;
        this.heightProductionHall = heightProductionHall;
        this.widthProductionHall = widthProductionHall;
        this.lengthProductionHall = lengthProductionHall;
    }

    public ForRequestChooseLuminaire() {
    }

    public long getLightingId() {
        return lightingId;
    }

    public void setLightingId(long lightingId) {
        this.lightingId = lightingId;
    }

    public double getHeightProductionHall() {
        return heightProductionHall;
    }

    public void setHeightProductionHall(double heightProductionHall) {
        this.heightProductionHall = heightProductionHall;
    }

    public double getWidthProductionHall() {
        return widthProductionHall;
    }

    public void setWidthProductionHall(double widthProductionHall) {
        this.widthProductionHall = widthProductionHall;
    }

    public double getLengthProductionHall() {
        return lengthProductionHall;
    }

    public void setLengthProductionHall(double lengthProductionHall) {
        this.lengthProductionHall = lengthProductionHall;
    }
}
