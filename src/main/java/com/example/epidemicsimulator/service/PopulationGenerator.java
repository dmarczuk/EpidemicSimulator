package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;

import java.util.ArrayList;
import java.util.List;

public class PopulationGenerator {

    public List<Population> startGeneratePopulation(Simulation simulation) {
        List<Population> listOfIterationsOfPopulation = new ArrayList<>();
        Population initialPopulation = generateInitialPopulation(simulation);
        Population population = initialPopulation;
        for (int i = 0; i < simulation.getDaysOfSimulation(); i++) {
            population = generatePopulation(population);
            listOfIterationsOfPopulation.add(population);
        }
        return listOfIterationsOfPopulation;
    }

    private Population generatePopulation(Population population) {
        return Population.builder().build();
    }

    public Population generateInitialPopulation(Simulation simulation) {

        return Population.builder()
                .numberOfInfectedPeople(simulation.getNumberOfInfectedPeople())
                .numberOfHealthyPeople(simulation.getNumberOfPopulation() - simulation.getNumberOfInfectedPeople())
                .numberOfDiedPeople(0)
                .numberOfRecoveredPeople(0).build();
    }
}
