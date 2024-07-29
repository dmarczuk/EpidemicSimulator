package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.model.DyingPerson;
import com.example.epidemicsimulator.model.InfectedPerson;
import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PopulationGenerator {

    public Population generatePopulation(Population population, Simulation simulation, List<InfectedPerson> listOfInfectedPeople, List<DyingPerson> listOfDyingPeople) {
        createListOfInfectedAndDyingPeople(simulation.getMarkerOfDeaths(), population.numberOfHealthyPeople(), listOfInfectedPeople, listOfDyingPeople);
        long countDiedPeople = listOfDyingPeople.stream()
                .filter(p -> p.getDaysToDie() == 0)
                .count();
        long countRecoveredPeople = listOfInfectedPeople.stream()
                .filter(p -> p.getDaysToRecover() == 0)
                .count();
        updateListOfInfectedAndDyingPeople();
        int numberOfInfectedPeople = (int) (population.numberOfInfectedPeople() * simulation.getMarkerOfInfections());
        int numberOfDyingPeople = (int) (numberOfInfectedPeople * simulation.getMarkerOfDeaths());
        int numberOfInfectedNotDyingPeople = numberOfInfectedPeople - numberOfDyingPeople;
        addDyingPeopleToList(listOfDyingPeople, numberOfDyingPeople);
        addInfectedPeopleToList(listOfInfectedPeople, numberOfInfectedNotDyingPeople);


        return Population.builder()
                .numberOfInfectedPeople(population.numberOfInfectedPeople() + numberOfInfectedPeople)
                .numberOfHealthyPeople(population.numberOfHealthyPeople() - numberOfInfectedPeople)
                .numberOfDiedPeople(population.numberOfDiedPeople() + countDiedPeople)
                .numberOfRecoveredPeople(population.numberOfRecoveredPeople() + countRecoveredPeople)
                .build();
    }

    private void updateListOfInfectedAndDyingPeople() {
    }

    private void addInfectedPeopleToList(List<InfectedPerson> listOfInfectedPeople, int numberOfInfectedNotDyingPeople) {

    }

    private void addDyingPeopleToList(List<DyingPerson> listOfDyingPeople, int numberOfDyingPeople) {

    }


    private void createListOfInfectedAndDyingPeople(double markerOfDeaths, long numberOfHealthyPeople, List<InfectedPerson> listOfInfectedPeople, List<DyingPerson> listOfDyingPeople) {
    }

    public Population generateInitialPopulation(Simulation simulation) {

        return Population.builder()
                .numberOfInfectedPeople(simulation.getNumberOfInfectedPeople())
                .numberOfHealthyPeople(simulation.getNumberOfPopulation() - simulation.getNumberOfInfectedPeople())
                .numberOfDiedPeople(0)
                .numberOfRecoveredPeople(0).build();
    }
}
