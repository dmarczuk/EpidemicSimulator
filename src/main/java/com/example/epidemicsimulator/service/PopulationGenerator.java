package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;

import java.util.ArrayList;
import java.util.List;

public class PopulationGenerator {

    public List<Population> startGeneratePopulation(Simulation simulation) {
        List<Population> listOfIterationsOfPopulation = new ArrayList<>();
        Population population = generateInitialPopulation();
        for (int i = 0; i < simulation.getDaysOfSimulation(); i++) {
            listOfIterationsOfPopulation.add(population);
            population = generatePopulation(population);

        }
        return listOfIterationsOfPopulation;

    }

    private Population generatePopulation(Population population) {
        return Population.builder().build();
    }

    private Population generateInitialPopulation() {
        return Population.builder().build();
    }
}
