package com.example.epidemicsimulator.model;

public class InfectedPerson {
    private int daysToRecover;

    public int getDaysToRecover() {
        return daysToRecover;
    }

    public void setDaysToRecover(int daysToRecover) {
        this.daysToRecover = daysToRecover;
    }

    public InfectedPerson(int daysToRecover) {
        this.daysToRecover = daysToRecover;
    }
}
