package edu.supavenir.spanimals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.supavenir.spanimals.models.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

	List<Animal> findBySos(boolean SOS);

}
