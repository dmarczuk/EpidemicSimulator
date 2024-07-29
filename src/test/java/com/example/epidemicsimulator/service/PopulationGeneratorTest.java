package com.example.epidemicsimulator.service;

import com.example.epidemicsimulator.dto.PopulationDto;
import com.example.epidemicsimulator.dto.SimulationDto;
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
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1.8)
                .markerOfDeaths(0.002)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(10).build();

        //when
        PopulationDto initialPopulation = populationGenerator.generateInitialPopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(initialPopulation.getNumberOfHealthyPeople()).isEqualTo(99_900),
                () -> assertThat(initialPopulation.getNumberOfDiedPeople()).isEqualTo(0),
                () -> assertThat(initialPopulation.getNumberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(initialPopulation.getNumberOfInfectedPeople()).isEqualTo(100)
        );
    }

    @Test
    public void should_be_correct_after_1_iteration() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(2)
                .markerOfDeaths(0.01)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(1).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(populations.get(0).getNumberOfHealthyPeople()).isEqualTo(99_700),
                () -> assertThat(populations.get(0).getNumberOfDiedPeople()).isEqualTo(0),
                () -> assertThat(populations.get(0).getNumberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(populations.get(0).getNumberOfInfectedPeople()).isEqualTo(300)
        );
    }

    @Test
    public void should_have_correct_balance_after_1_iteration() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(2)
                .markerOfDeaths(0.01)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(1).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertThat(
                        populations.get(0).getNumberOfDiedPeople() +
                                populations.get(0).getNumberOfInfectedPeople() +
                                populations.get(0).getNumberOfRecoveredPeople() +
                                populations.get(0).getNumberOfHealthyPeople())
                        .isEqualTo(testEpidemic.getNumberOfPopulation()
        );
    }


    @Test
    public void should_be_correct_after_2_iteration() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(2)
                .markerOfDeaths(0.01)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(2).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(populations.get(1).getNumberOfHealthyPeople()).isEqualTo(99_100),
                () -> assertThat(populations.get(1).getNumberOfDiedPeople()).isEqualTo(0),
                () -> assertThat(populations.get(1).getNumberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(populations.get(1).getNumberOfInfectedPeople()).isEqualTo(900)
        );
    }

    @Test
    public void should_have_correct_balance_after_2_iteration() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(2)
                .markerOfDeaths(0.01)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(2).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertThat(
                        populations.get(1).getNumberOfDiedPeople() +
                                populations.get(1).getNumberOfInfectedPeople() +
                                populations.get(1).getNumberOfRecoveredPeople() +
                                populations.get(1).getNumberOfHealthyPeople())
                        .isEqualTo(testEpidemic.getNumberOfPopulation()
        );
    }

    @Test
    public void should_be_correct_after_7_iteration() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1)
                .markerOfDeaths(0.01)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(6)
                .daysOfSimulation(7).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(populations.get(6).getNumberOfHealthyPeople()).isEqualTo(87200),
                () -> assertThat(populations.get(6).getNumberOfDiedPeople()).isEqualTo(1),
                () -> assertThat(populations.get(6).getNumberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(populations.get(6).getNumberOfInfectedPeople()).isEqualTo(12799)
        );
    }

    @Test
    public void should_have_correct_balance_after_7_iteration() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1)
                .markerOfDeaths(0.01)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(6)
                .daysOfSimulation(7).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertThat(
                        populations.get(6).getNumberOfDiedPeople() +
                                populations.get(6).getNumberOfInfectedPeople() +
                                populations.get(6).getNumberOfRecoveredPeople() +
                                populations.get(6).getNumberOfHealthyPeople())
                        .isEqualTo(testEpidemic.getNumberOfPopulation()
        );
    }

    @Test
    public void should_be_correct_after_7_iteration_with_high_marker_of_infections() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(2)
                .markerOfDeaths(0.01)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(8)
                .daysOfSimulation(7).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertAll(
                () -> assertThat(populations.get(6).getNumberOfHealthyPeople()).isEqualTo(0),
                () -> assertThat(populations.get(6).getNumberOfDiedPeople()).isEqualTo(0),
                () -> assertThat(populations.get(6).getNumberOfRecoveredPeople()).isEqualTo(0),
                () -> assertThat(populations.get(6).getNumberOfInfectedPeople()).isEqualTo(100000)
        );
    }
    @Test
    public void should_have_correct_balance_after_10_iterations() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1.8)
                .markerOfDeaths(0.002)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(10).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertThat(
                  populations.get(9).getNumberOfDiedPeople() +
                        populations.get(9).getNumberOfInfectedPeople() +
                        populations.get(9).getNumberOfRecoveredPeople() +
                        populations.get(9).getNumberOfHealthyPeople()
        ).isEqualTo(testEpidemic.getNumberOfPopulation());
    }

    @Test
    public void should_have_correct_balance_after_20_iterations() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1)
                .markerOfDeaths(0.002)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(20).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertThat(
                populations.get(19).getNumberOfDiedPeople() +
                        populations.get(19).getNumberOfInfectedPeople() +
                        populations.get(19).getNumberOfRecoveredPeople() +
                        populations.get(19).getNumberOfHealthyPeople()
        ).isEqualTo(testEpidemic.getNumberOfPopulation());
    }

    @Test
    public void should_have_correct_balance_after_40_iterations() {
        //given
        SimulationDto testEpidemic = SimulationDto.builder()
                .name("Test epidemic")
                .numberOfPopulation(100_000)
                .numberOfInfectedPeople(100)
                .markerOfInfections(1)
                .markerOfDeaths(0.002)
                .daysFromInfectToRecover(14)
                .daysFromInfectToDie(7)
                .daysOfSimulation(40).build();

        //when
        List<PopulationDto> populations = simulationService.startGeneratePopulation(testEpidemic);

        //then
        assertThat(
                populations.get(39).getNumberOfDiedPeople() +
                        populations.get(39).getNumberOfInfectedPeople() +
                        populations.get(39).getNumberOfRecoveredPeople() +
                        populations.get(39).getNumberOfHealthyPeople()
        ).isEqualTo(testEpidemic.getNumberOfPopulation());
    }

}