package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.model.InfectedPerson;
import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class SimulationService {

    private PopulationGenerator generator;

    public List<Population> startGeneratePopulation(Simulation simulation) {
        List<Population> listOfIterationsOfPopulation = new ArrayList<>();
        Population initialPopulation = generator.generateInitialPopulation(simulation);
        //List<InfectedPerson> infectedPeople = generator.generateListOfInfectedPeople(simulation)
        Population population = initialPopulation;
        List<InfectedPerson> setOfInfectedPeople = new LinkedList<>();
        for (int i = 0; i < simulation.getDaysOfSimulation(); i++) {
            population = generator.generatePopulation(population, simulation, setOfInfectedPeople);
            listOfIterationsOfPopulation.add(population);
        }
        return listOfIterationsOfPopulation;
    }
}
