package edu.supavenir.spanimals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import edu.supavenir.spanimals.repositories.AdoptantRepository;
import edu.supavenir.spanimals.repositories.AnimalRepository;

@Controller
public class employeController {

    @Autowired
    private AnimalRepository AnimRepo;
    @Autowired
    private AdoptantRepository RepoAdop;

    // AJOUTER UN ANIMAL
    @GetMapping("/employe/animal/add")
    public String addAnimal() {
	return "addAni";
    }

    @GetMapping("/employe/adoptant/add")
    public String addAdoptant() {
	return "addAdop";
    }
}
