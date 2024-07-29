package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.model.DyingPerson;
import com.example.epidemicsimulator.model.InfectedPerson;
import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@AllArgsConstructor
@Component
public class SimulationService {

    private PopulationGenerator generator;

    public List<Population> startGeneratePopulation(Simulation simulation) {
        List<Population> listOfIterationsOfPopulation = new ArrayList<>();
        Population population = generator.generateInitialPopulation(simulation);
       // Population population = initialPopulation;
        int numberOfDyingPerson = (int) (simulation.getNumberOfInfectedPeople() * simulation.getMarkerOfDeaths());
        int numberOfPersonToRecover = simulation.getNumberOfInfectedPeople() - numberOfDyingPerson;
        List<InfectedPerson> listOfInfectedPeople = generator.addInfectedPeopleToList(new LinkedList<>(), numberOfPersonToRecover, simulation.getDaysFromInfectToRecover());
        List<DyingPerson> listOfDyingPeople = generator.addDyingPeopleToList(new LinkedList<>(), numberOfDyingPerson, simulation.getDaysFromInfectToDie());
        for (int i = 0; i < simulation.getDaysOfSimulation(); i++) {
            population = generator.generatePopulation(population, simulation, listOfInfectedPeople, listOfDyingPeople);
            listOfIterationsOfPopulation.add(population);
        }
        return listOfIterationsOfPopulation;
    }
}
