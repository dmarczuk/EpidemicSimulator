package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.dto.PopulationDto;
import com.example.epidemicsimulator.dto.SimulationDto;
import com.example.epidemicsimulator.model.DyingPerson;
import com.example.epidemicsimulator.model.InfectedPerson;
import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PopulationGenerator {

    public PopulationDto generatePopulation(PopulationDto populationDto, SimulationDto simulationDto, List<InfectedPerson> listOfInfectedPeople, List<DyingPerson> listOfDyingPeople) {
        //createListOfInfectedAndDyingPeople(simulationDto.getMarkerOfDeaths(), population.numberOfHealthyPeople(), listOfInfectedPeople, listOfDyingPeople);
        long countDiedPeople = listOfDyingPeople.stream()
                .filter(p -> p.getDaysToDie() == 0)
                .count();
        long countRecoveredPeople = listOfInfectedPeople.stream()
                .filter(p -> p.getDaysToRecover() == 0)
                .count();
        updateListOfInfectedAndDyingPeople(listOfInfectedPeople, listOfDyingPeople);
        int numberOfInfectedPeople = (int) (populationDto.getNumberOfInfectedPeople() * simulationDto.getMarkerOfInfections());
        if (numberOfInfectedPeople > populationDto.getNumberOfHealthyPeople()) {
            numberOfInfectedPeople = (int) populationDto.getNumberOfHealthyPeople();
        }
        int numberOfDyingPeople = (int) (numberOfInfectedPeople * simulationDto.getMarkerOfDeaths());
        int numberOfInfectedNotDyingPeople = numberOfInfectedPeople - numberOfDyingPeople;
        addDyingPeopleToList(listOfDyingPeople, numberOfDyingPeople, simulationDto.getDaysFromInfectToDie());
        addInfectedPeopleToList(listOfInfectedPeople, numberOfInfectedNotDyingPeople, simulationDto.getDaysFromInfectToRecover());


        return PopulationDto.builder()
                .numberOfInfectedPeople(listOfInfectedPeople.size() + listOfDyingPeople.size())
                .numberOfHealthyPeople(populationDto.getNumberOfHealthyPeople() - numberOfInfectedPeople)
                .numberOfDiedPeople(populationDto.getNumberOfDiedPeople() + countDiedPeople)
                .numberOfRecoveredPeople(populationDto.getNumberOfRecoveredPeople() + countRecoveredPeople)
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

    public PopulationDto generateInitialPopulation(SimulationDto simulationDto) {

        return PopulationDto.builder()
                .numberOfInfectedPeople(simulationDto.getNumberOfInfectedPeople())
                .numberOfHealthyPeople(simulationDto.getNumberOfPopulation() - simulationDto.getNumberOfInfectedPeople())
                .numberOfDiedPeople(0)
                .numberOfRecoveredPeople(0).build();
    }
}
