package edu.supavenir.spanimals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.supavenir.spanimals.models.Espece;

public interface EspeceRepository extends JpaRepository<Espece, Integer> {

}
