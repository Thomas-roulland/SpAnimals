package edu.supavenir.spanimals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.supavenir.spanimals.models.Race;

public interface raceRepository extends JpaRepository<Race, Integer> {

}
