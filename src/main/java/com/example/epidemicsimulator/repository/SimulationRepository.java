package com.example.epidemicsimulator.repository;

import com.example.epidemicsimulator.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, String> {
}
