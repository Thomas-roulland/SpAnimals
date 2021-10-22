package edu.supavenir.spanimals.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.supavenir.spanimals.models.Adoptant;
import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.AdoptantRepository;
import edu.supavenir.spanimals.repositories.AnimalRepository;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RaceRepository;

@RequestMapping("/employe")
@RestController

public class RestEmployeController {

    @Autowired
    private AdoptantRepository RepoAdop;
    @Autowired
    private AnimalRepository AnimRepo;

    @Autowired
    private EspeceRepository repoE;

    @Autowired
    private RaceRepository repoR;

    @GetMapping("/race")
    public String AffichageRace() {
	return repoR.findAll().toString();
    }

    @GetMapping("/espece")
    public String AffichageEspece() {
	return repoE.findAll().toString();
    }

    @GetMapping("/adoptant") // VOIR TOUS LES ADOPTANT [DONE]
    public List<Adoptant> affichageAdoptant() {
	return RepoAdop.findAll();
    }

    // Regarder tous les animaux

    @GetMapping("/animal") // VOIR TOUS LES ANIMAUX [DONE]
    public List<Animal> affichageAnimal() {
	return AnimRepo.findAll();
    }

    // REGARDER LA FICHE ADOPTANT [DONE]
    @GetMapping("/adoptant/{id}")
    private Optional<Adoptant> FicheAdoptant(@PathVariable int id) {
	return RepoAdop.findById(id);
    }

    // REGARDER LA FICHE ANIMAL [DONE]
    @GetMapping("/animal/{id}")
    private Optional<Animal> FicheAnimalAction(@PathVariable int id) {
	return AnimRepo.findById(id);
    }

    // AJOUTER UN ADOPTANT [DONE]
    @PostMapping("adoptant/add")
    private @ResponseBody String AjouteAdoptant(Adoptant adoptant) {
	RepoAdop.saveAndFlush(adoptant);
	return adoptant.toString();
    }

    // AJOUTER UN ANIMAL [DONE]
    @PostMapping("animal/add")
    private @ResponseBody String ajouteAnimal(Animal animal) {
	AnimRepo.save(animal);
	return animal.toString();
    }

    // MODIFIER UN ADOPTANT
    @PutMapping("{id}")
    private Adoptant EditAdoptant(@PathVariable int id, Adoptant adoptant) {
	if (RepoAdop.existsById(id)) {
	    RepoAdop.save(adoptant);
	    return adoptant;
	}
	return null;
    }

}
