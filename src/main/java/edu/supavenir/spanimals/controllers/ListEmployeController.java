package edu.supavenir.spanimals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RaceRepository;

@RequestMapping("/employe")
@RestController
public class ListEmployeController {
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
}
