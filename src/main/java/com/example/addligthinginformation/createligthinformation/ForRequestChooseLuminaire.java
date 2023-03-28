package com.example.addligthinginformation.createligthinformation;



public class ForRequestChooseLuminaire {
    private double heightProductionHall;
    private double widthProductionHall;
    private double lengthProductionHall;

    public ForRequestChooseLuminaire(double heightProductionHall, double widthProductionHall, double lengthProductionHall) {
        this.heightProductionHall = heightProductionHall;
        this.widthProductionHall = widthProductionHall;
        this.lengthProductionHall = lengthProductionHall;
    }

    public ForRequestChooseLuminaire() {
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
