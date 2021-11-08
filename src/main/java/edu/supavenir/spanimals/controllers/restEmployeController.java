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

import edu.supavenir.spanimals.models.Adoptant;
import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.models.Race;
import edu.supavenir.spanimals.repositories.adoptantRepository;
import edu.supavenir.spanimals.repositories.animalRepository;
import edu.supavenir.spanimals.repositories.especeRepository;
import edu.supavenir.spanimals.repositories.raceRepository;

@RequestMapping("/employe")
@RestController

public class restEmployeController {

	@Autowired
	private adoptantRepository RepoAdop;

	@Autowired
	private animalRepository AnimRepo;

	@Autowired
	private especeRepository repoE;

	@Autowired
	private raceRepository repoR;

	@GetMapping("/race")
	public List<Race> AffichageRace() {
		return repoR.findAll();
	}

	@GetMapping("/espece")
	public List<Espece> AffichageEspece() {
		return repoE.findAll();
	}

	// VOIR TOUS LES ADOPTANT [DONE]
	@GetMapping("/adoptant")
	public List<Adoptant> affichageAdoptant() {
		return RepoAdop.findAll();
	}

	// VOIR TOUS LES ANIMAUX [DONE]
	@GetMapping("/animal")
	public List<Animal> affichageAnimal() {
		return AnimRepo.findAll();
	}

	// REGARDER LA FICHE ADOPTANT [DONE]
	@GetMapping("/adoptant/{id}")
	private Optional<Adoptant> FicheAdoptant(@PathVariable int id) {
		return RepoAdop.findById(id);
	}

	// REGARDER LA FICHE ANIMAL [DONE]
	

	// AJOUTER UN ADOPTANT [DONE]
<<<<<<< HEAD
	@PostMapping("/add/adoptant")
=======
	@PostMapping("add/adoptant")
>>>>>>> 0122bde548877454242a547ee7b16e935669d003
	private @ResponseBody String AjouteAdoptant(@RequestBody Adoptant adoptant) {
		RepoAdop.saveAndFlush(adoptant);
		return adoptant.toString();
	}

	// AJOUTER UN ANIMAL [DONE]
	@PostMapping("/add/animal")
	private @ResponseBody String ajouteAnimal(@RequestBody Animal animal) {
		AnimRepo.saveAndFlush(animal);
<<<<<<< HEAD
		return animal.toString();
=======
		return animal.toString();	

<<<<<<< HEAD
=======
=======

		AnimRepo.saveAndFlush(animal);
		return animal.toString();	
>>>>>>> b3a4cc2d5724a39537f93c0f6017db393a4b78fc
>>>>>>> 0122bde548877454242a547ee7b16e935669d003
>>>>>>> 0646e078a4e9f7ee0268eb2afc762cdd9dcf284a
	}

	// MODIFIER UN ADOPTANT
	@PostMapping("{id}")
	private Adoptant EditAdoptant(@PathVariable int id,@RequestBody Adoptant adoptant) {
		if (RepoAdop.existsById(id)) {
			RepoAdop.save(adoptant);
			return adoptant;
		}
		return null;
	}

}
