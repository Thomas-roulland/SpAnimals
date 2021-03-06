package edu.supavenir.spanimals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.animalRepository;

@RestController
public class spaRestController {

    @Autowired
    private animalRepository repo;

    @GetMapping("/sos")
    public List<Animal> SosAnimal() {
	return repo.findBySos(true);
    }
}
