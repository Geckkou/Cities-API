package com.github.alexandre.citiesapi.states.repository;

import com.github.alexandre.citiesapi.states.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}