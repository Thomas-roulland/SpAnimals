package edu.supavenir.spanimals.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import edu.supavenir.spanimals.models.Adoptant;
import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.AdoptantRepository;
import edu.supavenir.spanimals.repositories.AnimalRepository;

@RequestMapping("/employe")
@RestController

public class SpaLEmployéController {

    @Autowired
    private AdoptantRepository RepoAdop;
    @Autowired
    private AnimalRepository AnimRepo;

    // REGARDER LA FICHE ADOPTANT
    @GetMapping("/adoptant/{id}")
    private Optional<Adoptant> FicheAdoptant(@PathVariable int id, Adoptant adoptant) {
	return RepoAdop.findById(id);
    }

    // REGARDER LA FICHE ANIMAL
    @GetMapping("/animal/{id}")
    private Optional<Animal> FicheAnimalAction(@PathVariable int id) {
	return AnimRepo.findById(id);
    }

    // Regarder tous les animaux

    @GetMapping("/animal")
    public List<Animal> affichageAnimal() {
	return AnimRepo.findAll();
    }

    // AJOUTER UN ADOPTANT
    @PostMapping()
    private Adoptant AjouteAdoptant(@RequestBody Adoptant adoptant) {
	RepoAdop.saveAndFlush(adoptant);
	return adoptant;
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

    @PostMapping("/adoptant/edit/{id}")
    private RedirectView editAdoptant(@PathVariable int id, Adoptant adoptant) {
	Adoptant AdoptBdd = RepoAdop.getById(id);
	AdoptBdd.setAdoption(adoptant.getAdoption());
	AdoptBdd.setComplementad(adoptant.getComplementad());
	AdoptBdd.setCp(adoptant.getCp());
	AdoptBdd.setDatenaiss(adoptant.getDatenaiss());
	AdoptBdd.setId(adoptant.getId());
	AdoptBdd.setInfocomplementaires(adoptant.getInfocomplementaires());
	AdoptBdd.setLockFlag(adoptant.getLockFlag());
	AdoptBdd.setMail(adoptant.getMail());
	AdoptBdd.setNom(adoptant.getNom());
	AdoptBdd.setNumeroetrue(adoptant.getNumeroetrue());
	AdoptBdd.setPrenom(adoptant.getPrenom());
	AdoptBdd.setTelfixe(adoptant.getTelfixe());
	AdoptBdd.setTelmobile(adoptant.getTelmobile());
	AdoptBdd.setVille(adoptant.getVille());
	RepoAdop.saveAndFlush(AdoptBdd);
	return new RedirectView("/Adoptant/");
    }

    // AJOUTER UN ANIMAL

    @PostMapping("/animal/add")
    private @ResponseBody String ajouteAnimal(Animal animal) {
	AnimRepo.save(animal);
	return animal.toString();
    }

}
