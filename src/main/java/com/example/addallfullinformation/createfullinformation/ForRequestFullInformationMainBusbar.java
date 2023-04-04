package com.example.addallfullinformation.createfullinformation;

import java.util.List;

public class ForRequestFullInformationMainBusbar {
    private long id;
    private String nameOfBusbar;
    private List<Long> numbersBusbarsIncludedInMain;

    public ForRequestFullInformationMainBusbar(long id, String nameOfBusbar, List<Long> numbersBusbarsIncludedInMain) {
        this.id = id;
        this.nameOfBusbar = nameOfBusbar;
        this.numbersBusbarsIncludedInMain = numbersBusbarsIncludedInMain;
    }

    public ForRequestFullInformationMainBusbar() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfBusbar() {
        return nameOfBusbar;
    }

    public void setNameOfBusbar(String nameOfBusbar) {
        this.nameOfBusbar = nameOfBusbar;
    }


    public List<Long> getNumbersBusbarsIncludedInMain() {
        return numbersBusbarsIncludedInMain;
    }

    public void setNumbersBusbarsIncludedInMain(List<Long> numbersBusbarsIncludedInMain) {
        this.numbersBusbarsIncludedInMain = numbersBusbarsIncludedInMain;
    }

}
