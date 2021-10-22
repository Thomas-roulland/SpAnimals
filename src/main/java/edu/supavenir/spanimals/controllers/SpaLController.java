package edu.supavenir.spanimals.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.AdoptantRepository;
import edu.supavenir.spanimals.repositories.AnimalRepository;

// LUCAS [↓]
@Controller
public class SpaLController {

    @Autowired
    private AnimalRepository Repo;
    @Autowired
    private AdoptantRepository RepoAdop;

    @GetMapping("/animal")
    public List<Animal> ListAnimal() {

	return Repo.findAll();
    }

    @GetMapping("/animal/{id}")
    private Optional<Animal> FicheAnimalAction(@PathVariable int id, Animal animal) {
	return Repo.findById(id);
    }

    @GetMapping("/employe/adoptant/edit/{id}")
    public String RedirecteditAction(Model model, @PathVariable int id) {
	return "EditAdop";
    }
// LUCAS [↑]
}