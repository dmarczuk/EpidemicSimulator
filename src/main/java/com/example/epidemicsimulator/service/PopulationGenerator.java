package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.model.InfectedPerson;
import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PopulationGenerator {

    public Population generatePopulation(Population population, Simulation simulation, List<InfectedPerson> listOfInfectedPeople) {
        createListOfInfectedPeople(simulation.getMarkerOfDeaths(), population.numberOfHealthyPeople(), listOfInfectedPeople);
        listOfInfectedPeople.stream()
                .filter(p -> p.);


        return Population.builder().build();
    }

    private void createListOfInfectedPeople(double markerOfDeaths, int numberOfHealthyPeople, List<InfectedPerson> listOfInfectedPeople) {
    }

    public Population generateInitialPopulation(Simulation simulation) {

        return Population.builder()
                .numberOfInfectedPeople(simulation.getNumberOfInfectedPeople())
                .numberOfHealthyPeople(simulation.getNumberOfPopulation() - simulation.getNumberOfInfectedPeople())
                .numberOfDiedPeople(0)
                .numberOfRecoveredPeople(0).build();
    }
}
