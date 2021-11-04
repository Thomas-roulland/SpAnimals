package edu.supavenir.spanimals.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Adoptant;
import edu.supavenir.spanimals.repositories.AdoptantRepository;
import edu.supavenir.spanimals.repositories.AnimalRepository;
@RequestMapping("/employe")
@Controller
public class employeController {

    @Autowired
    private AnimalRepository AnimRepo;
    @Autowired
    private AdoptantRepository RepoAdop;

    // AJOUTER UN ANIMAL
    @GetMapping("/add/animal")
    public String addAnimal() {
	return "FormAddAni";
    }

    @GetMapping("/add/adoptant")
    public String addAdoptant() {
	return "FormAddAdop";
    }

    @GetMapping("/edit/adoptant/{id}")
    public String ActionEditFormAdoptant(Model model, @PathVariable int id) {

	Optional<Adoptant> Adoptant = RepoAdop.findById(id);
	model.addAttribute("Adoptant", Adoptant);
	return "FormEditAdop";
    }
}
