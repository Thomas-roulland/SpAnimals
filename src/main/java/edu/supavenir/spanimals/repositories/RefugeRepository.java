package edu.supavenir.spanimals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.supavenir.spanimals.models.Refuge;

public interface RefugeRepository extends JpaRepository<Refuge, Integer> {

}
