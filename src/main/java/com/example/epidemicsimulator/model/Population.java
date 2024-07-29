package com.example.epidemicsimulator.model;

import lombok.Builder;

@Builder
public record Population(
        int numberOfInfectedPeople,
        int numberOfHealthyPeople,
        int numberOfDiedPeople,
        int numberOfRecoveredPeople) {
}
