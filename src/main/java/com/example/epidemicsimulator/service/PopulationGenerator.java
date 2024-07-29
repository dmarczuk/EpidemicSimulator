package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.model.DyingPerson;
import com.example.epidemicsimulator.model.InfectedPerson;
import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;

import java.util.LinkedList;
import java.util.List;

public class PopulationGenerator {

    public Population generatePopulation(Population population, Simulation simulation, List<InfectedPerson> listOfInfectedPeople, List<DyingPerson> listOfDyingPeople) {
        //createListOfInfectedAndDyingPeople(simulation.getMarkerOfDeaths(), population.numberOfHealthyPeople(), listOfInfectedPeople, listOfDyingPeople);
        long countDiedPeople = listOfDyingPeople.stream()
                .filter(p -> p.getDaysToDie() == 0)
                .count();
        long countRecoveredPeople = listOfInfectedPeople.stream()
                .filter(p -> p.getDaysToRecover() == 0)
                .count();
        updateListOfInfectedAndDyingPeople(listOfInfectedPeople, listOfDyingPeople);
        int numberOfInfectedPeople = (int) (population.numberOfInfectedPeople() * simulation.getMarkerOfInfections());
        int numberOfDyingPeople = (int) (numberOfInfectedPeople * simulation.getMarkerOfDeaths());
        int numberOfInfectedNotDyingPeople = numberOfInfectedPeople - numberOfDyingPeople;
        addDyingPeopleToList(listOfDyingPeople, numberOfDyingPeople, simulation.getDaysFromInfectToDie());
        addInfectedPeopleToList(listOfInfectedPeople, numberOfInfectedNotDyingPeople, simulation.getDaysFromInfectToRecover());


        return Population.builder()
                .numberOfInfectedPeople(listOfInfectedPeople.size() + listOfDyingPeople.size())
                .numberOfHealthyPeople(population.numberOfHealthyPeople() - numberOfInfectedPeople)
                .numberOfDiedPeople(population.numberOfDiedPeople() + countDiedPeople)
                .numberOfRecoveredPeople(population.numberOfRecoveredPeople() + countRecoveredPeople)
                .build();
    }

    private void updateListOfInfectedAndDyingPeople(List<InfectedPerson> listOfInfectedPeople, List<DyingPerson> listOfDyingPeople) {
        listOfDyingPeople.removeIf(p -> p.getDaysToDie() == 0);
        listOfDyingPeople.forEach(p -> p.setDaysToDie(p.getDaysToDie() - 1));
        listOfInfectedPeople.removeIf(p -> p.getDaysToRecover() == 0);
        listOfInfectedPeople.forEach(p -> p.setDaysToRecover(p.getDaysToRecover() - 1));
    }

    public List<InfectedPerson> addInfectedPeopleToList(List<InfectedPerson> listOfInfectedPeople, int numberOfInfectedNotDyingPeople, int days) {
        for (int i = 0; i < numberOfInfectedNotDyingPeople; i++) {
            listOfInfectedPeople.add(new InfectedPerson(days));
        }
        return listOfInfectedPeople;
    }

    public List<DyingPerson> addDyingPeopleToList(List<DyingPerson> listOfDyingPeople, int numberOfDyingPeople, int days) {
        for (int i = 0; i < numberOfDyingPeople; i++) {
            listOfDyingPeople.add(new DyingPerson(days));
        }
        return listOfDyingPeople;
    }

    public Population generateInitialPopulation(Simulation simulation) {

        return Population.builder()
                .numberOfInfectedPeople(simulation.getNumberOfInfectedPeople())
                .numberOfHealthyPeople(simulation.getNumberOfPopulation() - simulation.getNumberOfInfectedPeople())
                .numberOfDiedPeople(0)
                .numberOfRecoveredPeople(0).build();
    }
}
