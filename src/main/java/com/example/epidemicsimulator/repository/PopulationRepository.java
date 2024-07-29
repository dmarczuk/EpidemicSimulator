package com.example.epidemicsimulator.repository;

import com.example.epidemicsimulator.model.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends JpaRepository<Population, String> {
}
