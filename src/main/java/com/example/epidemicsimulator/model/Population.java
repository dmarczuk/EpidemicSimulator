package com.example.epidemicsimulator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

//@Builder
@Entity
public class Population {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long numberOfInfectedPeople;
    private long numberOfHealthyPeople;
    private long numberOfDiedPeople;
    private long numberOfRecoveredPeople;

    public Population() {
    }

    public long getNumberOfInfectedPeople() {
        return numberOfInfectedPeople;
    }

    public void setNumberOfInfectedPeople(long numberOfInfectedPeople) {
        this.numberOfInfectedPeople = numberOfInfectedPeople;
    }

    public long getNumberOfHealthyPeople() {
        return numberOfHealthyPeople;
    }

    public void setNumberOfHealthyPeople(long numberOfHealthyPeople) {
        this.numberOfHealthyPeople = numberOfHealthyPeople;
    }

    public long getNumberOfDiedPeople() {
        return numberOfDiedPeople;
    }

    public void setNumberOfDiedPeople(long numberOfDiedPeople) {
        this.numberOfDiedPeople = numberOfDiedPeople;
    }

    public long getNumberOfRecoveredPeople() {
        return numberOfRecoveredPeople;
    }

    public void setNumberOfRecoveredPeople(long numberOfRecoveredPeople) {
        this.numberOfRecoveredPeople = numberOfRecoveredPeople;
    }
}