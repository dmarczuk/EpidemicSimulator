package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.model.Population;
import com.example.epidemicsimulator.model.Simulation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PopulationGeneratorTest {

    PopulationGenerator populationGenerator = new PopulationGenerator(); // bean SPRINGA !!! zmienić
    SimulationService simulationService = new SimulationService(populationGenerator);
    @Test
    public void should_create_correct_initial_population() {
        //given
        Simulation testEpidemic = Simulation.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1.8)
                .markerOfDeaths(0.002)
                .daysFromInfectToGetWell(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(10).build();

        //when
        Population initialPopulation = populationGenerator.generateInitialPopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(initialPopulation.numberOfHealthyPeople()).isEqualTo(99_900),
                () -> assertThat(initialPopulation.numberOfDiedPeople()).isEqualTo(0),
                () -> assertThat(initialPopulation.numberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(initialPopulation.numberOfInfectedPeople()).isEqualTo(100)
        );
    }

    @Test
    public void should_have_correct_after_1_iteration() {
        //given
        Simulation testEpidemic = Simulation.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(2)
                .markerOfDeaths(0.01)
                .daysFromInfectToGetWell(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(1).build();

        //when
        List<Population> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(populations.get(0).numberOfHealthyPeople()).isEqualTo(99_700),
                () -> assertThat(populations.get(0).numberOfDiedPeople()).isEqualTo(0),
                () -> assertThat(populations.get(0).numberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(populations.get(0).numberOfInfectedPeople()).isEqualTo(300)
        );
    }


    @Test
    public void should_be_correct_after_2_iteration() {
        //given
        Simulation testEpidemic = Simulation.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(2)
                .markerOfDeaths(0.01)
                .daysFromInfectToGetWell(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(2).build();

        //when
        List<Population> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(populations.get(1).numberOfHealthyPeople()).isEqualTo(99_100),
                () -> assertThat(populations.get(1).numberOfDiedPeople()).isEqualTo(0),
                () -> assertThat(populations.get(1).numberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(populations.get(1).numberOfInfectedPeople()).isEqualTo(900)
        );
    }

    @Test
    public void should_be_correct_after_7_iteration() {
        //given
        Simulation testEpidemic = Simulation.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1)
                .markerOfDeaths(0.01)
                .daysFromInfectToGetWell(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(7).build();

        //when
        List<Population> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(populations.get(6).numberOfHealthyPeople()).isEqualTo(87200),
                () -> assertThat(populations.get(6).numberOfDiedPeople()).isEqualTo(1),
                () -> assertThat(populations.get(6).numberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(populations.get(6).numberOfInfectedPeople()).isEqualTo(12800)
        );
    }

    @Test
    public void should_be_correct_after_7_iteration_with_high_marker_of_infections() {
        //given
        Simulation testEpidemic = Simulation.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(2)
                .markerOfDeaths(0.01)
                .daysFromInfectToGetWell(14)
                .daysFromInfectToDie(8)
                .daysOfSimulation(7).build();

        //when
        List<Population> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(populations.get(6).numberOfHealthyPeople()).isEqualTo(0),
                () -> assertThat(populations.get(6).numberOfDiedPeople()).isEqualTo(0),
                () -> assertThat(populations.get(6).numberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(populations.get(6).numberOfInfectedPeople()).isEqualTo(100000)
        );
    }
    @Test
    public void should_be_correct_after_10_iterations() {
        //given
        Simulation testEpidemic = Simulation.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1.8)
                .markerOfDeaths(0.002)
                .daysFromInfectToGetWell(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(10).build();

        //when
        List<Population> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertThat(
                  populations.get(9).numberOfDiedPeople() +
                        populations.get(9).numberOfInfectedPeople() +
                        populations.get(9).numberOfRecoveredPeople() +
                        populations.get(9).numberOfHealthyPeople()
        ).isEqualTo(testEpidemic.getNumberOfPopulation());
    }

}