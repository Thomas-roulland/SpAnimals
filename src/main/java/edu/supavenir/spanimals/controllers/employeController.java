package edu.supavenir.spanimals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import edu.supavenir.spanimals.repositories.AnimalRepository;

@Controller
public class employeController {

    @Autowired
    private AnimalRepository AnimRepo;

    // AJOUTER UN ANIMAL
    @GetMapping("/employe/animal/add")
    public String addAnimal() {
	return "addAni";
    }
}
