package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.dto.PopulationDto;
import com.example.epidemicsimulator.dto.SimulationDto;
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

    public List<PopulationDto> startGeneratePopulation(SimulationDto simulationDto) {
        List<PopulationDto> listOfIterationsOfPopulation = new ArrayList<>();
        PopulationDto populationDto = generator.generateInitialPopulation(simulationDto);
       // Population population = initialPopulation;
        int numberOfDyingPerson = (int) (simulationDto.getNumberOfInfectedPeople() * simulationDto.getMarkerOfDeaths());
        int numberOfPersonToRecover = simulationDto.getNumberOfInfectedPeople() - numberOfDyingPerson;
        List<InfectedPerson> listOfInfectedPeople = generator.addInfectedPeopleToList(new LinkedList<>(), numberOfPersonToRecover, simulationDto.getDaysFromInfectToRecover());
        List<DyingPerson> listOfDyingPeople = generator.addDyingPeopleToList(new LinkedList<>(), numberOfDyingPerson, simulationDto.getDaysFromInfectToDie());
        for (int i = 0; i < simulationDto.getDaysOfSimulation(); i++) {
            populationDto = generator.generatePopulation(populationDto, simulationDto, listOfInfectedPeople, listOfDyingPeople);
            listOfIterationsOfPopulation.add(populationDto);
        }
        return listOfIterationsOfPopulation;
    }
}
