package com.example.epidemicsimulator.dto;

import lombok.Builder;

@Builder
public class PopulationDto {

    private long numberOfInfectedPeople;
    private long numberOfHealthyPeople;
    private long numberOfDiedPeople;
    private long numberOfRecoveredPeople;

    public long getNumberOfInfectedPeople() {
        return numberOfInfectedPeople;
    }

    public long getNumberOfHealthyPeople() {
        return numberOfHealthyPeople;
    }

    public long getNumberOfDiedPeople() {
        return numberOfDiedPeople;
    }

    public long getNumberOfRecoveredPeople() {
        return numberOfRecoveredPeople;
    }
}
