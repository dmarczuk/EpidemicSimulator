package com.example.epidemicsimulator.model;

import lombok.Builder;

@Builder
public class Simulation {
    private String name;
    private int amountPopulation;
    private int amountSickPeople;
    private int markerOfInfections; //number of people who one sick person infect
    private int markerOfDeaths; //number how much sick people die
    private int daysFromInfectToGetWell; //
    private int dayFromInfectToDie;
    private int daysOfSimulation;

    public int getDaysOfSimulation() {
        return daysOfSimulation;
    }
}
