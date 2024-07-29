package com.example.epidemicsimulator.model;

import lombok.Builder;

@Builder
public class Simulation {
    private String name;
    private int numberOfPopulation;
    private int numberOfInfectedPeople;
    private double markerOfInfections; //number of people who one sick person infect
    private double markerOfDeaths; // quotient of number of deaths and number sick people
    private int daysFromInfectToGetWell; //
    private int daysFromInfectToDie;
    private int daysOfSimulation;

    public String getName() {
        return name;
    }

    public int getNumberOfPopulation() {
        return numberOfPopulation;
    }

    public int getNumberOfInfectedPeople() {
        return numberOfInfectedPeople;
    }

    public double getMarkerOfInfections() {
        return markerOfInfections;
    }

    public double getMarkerOfDeaths() {
        return markerOfDeaths;
    }

    public int getDaysFromInfectToGetWell() {
        return daysFromInfectToGetWell;
    }

    public int getDaysFromInfectToDie() {
        return daysFromInfectToDie;
    }

    public int getDaysOfSimulation() {
        return daysOfSimulation;
    }
}
