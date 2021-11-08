package edu.supavenir.spanimals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.supavenir.spanimals.models.Espece;

public interface especeRepository extends JpaRepository<Espece, Integer> {

}
