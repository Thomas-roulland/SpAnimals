package edu.supavenir.spanimals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.supavenir.spanimals.models.Adoptant;

@Repository
public interface AdoptantRepository extends JpaRepository<Adoptant, Integer> {

}
