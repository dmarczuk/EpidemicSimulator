package com.example.epidemicsimulator.model;

import jakarta.persistence.*;

@Entity
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private int numberOfPopulation;
    private int numberOfInfectedPeople;
    private double markerOfInfections; //number of people who one sick person infect
    private double markerOfDeaths; // quotient of number of deaths and number sick people
    private int daysFromInfectToRecover; //
    private int daysFromInfectToDie;
    private int daysOfSimulation;

    public Simulation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPopulation() {
        return numberOfPopulation;
    }

    public void setNumberOfPopulation(int numberOfPopulation) {
        this.numberOfPopulation = numberOfPopulation;
    }

    public int getNumberOfInfectedPeople() {
        return numberOfInfectedPeople;
    }

    public void setNumberOfInfectedPeople(int numberOfInfectedPeople) {
        this.numberOfInfectedPeople = numberOfInfectedPeople;
    }

    public double getMarkerOfInfections() {
        return markerOfInfections;
    }

    public void setMarkerOfInfections(double markerOfInfections) {
        this.markerOfInfections = markerOfInfections;
    }

    public double getMarkerOfDeaths() {
        return markerOfDeaths;
    }

    public void setMarkerOfDeaths(double markerOfDeaths) {
        this.markerOfDeaths = markerOfDeaths;
    }

    public int getDaysFromInfectToRecover() {
        return daysFromInfectToRecover;
    }

    public void setDaysFromInfectToRecover(int daysFromInfectToRecover) {
        this.daysFromInfectToRecover = daysFromInfectToRecover;
    }

    public int getDaysFromInfectToDie() {
        return daysFromInfectToDie;
    }

    public void setDaysFromInfectToDie(int daysFromInfectToDie) {
        this.daysFromInfectToDie = daysFromInfectToDie;
    }

    public int getDaysOfSimulation() {
        return daysOfSimulation;
    }

    public void setDaysOfSimulation(int daysOfSimulation) {
        this.daysOfSimulation = daysOfSimulation;
    }
}
