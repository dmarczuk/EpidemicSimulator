package com.example.epidemicsimulator.model;

public record Population(
        int numberOfInfectedPeople,
        int numberOfHealthyPeople,
        int numberOfDiedPeople,
        int numberOfRecoveredPeople) {
}
