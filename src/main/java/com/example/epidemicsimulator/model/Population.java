package com.example.epidemicsimulator.model;

import lombok.Builder;

@Builder
public record Population(
        long numberOfInfectedPeople,
        long numberOfHealthyPeople,
        long numberOfDiedPeople,
        long numberOfRecoveredPeople) {
}
