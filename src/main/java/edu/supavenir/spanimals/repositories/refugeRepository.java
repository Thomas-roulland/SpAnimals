package edu.supavenir.spanimals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.supavenir.spanimals.models.Race;
import edu.supavenir.spanimals.models.Refuge;

public interface refugeRepository extends JpaRepository<Refuge, Integer> {

	void save(Race race);

}
