package com.example.epidemicsimulator.model;

public class DyingPerson {
    private int daysToDie;

    public int getDaysToDie() {
        return daysToDie;
    }

    public void setDaysToDie(int daysToDie) {
        this.daysToDie = daysToDie;
    }

    public DyingPerson(int daysToDie) {
        this.daysToDie = daysToDie;
    }
}
