package edu.supavenir.spanimals.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.AnimalRepository;

// LUCAS [↓]
@RestController
public class SpaLController {

    @Autowired
    private AnimalRepository Repo;

    @GetMapping("/rest/animal")
    public List<Animal> AllAnimal() {

	return Repo.findAll();
    }

    @GetMapping("/animal/{id}")
    private Optional<Animal> FicheAnimalAction(@PathVariable int id, Animal animal) {
	return Repo.findById(id);
    }

// LUCAS [↑]
}