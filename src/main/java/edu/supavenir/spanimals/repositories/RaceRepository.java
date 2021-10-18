package edu.supavenir.spanimals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.supavenir.spanimals.models.Race;

public interface RaceRepository extends JpaRepository<Race, Integer> {

}
