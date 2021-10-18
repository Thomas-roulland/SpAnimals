package edu.supavenir.spanimals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.AnimalRepository;

@Controller
public class SpaLController {
	// LUCAS [↓] 
	@Autowired
	private AnimalRepository Repo;
	
	@GetMapping("fiche")
	private String FicheAction(){
		
	return "fiche";
}
	public void ListAnimal() {
		Repo.findAll();
	}
	
	public List<Animal> SearchAnimal(String recherche) {
		if (Repo.findAll().contains(recherche))
			
			return 	null; //PAS ENCORE AJOUT 
		else
			return Repo.findAll();
	}
	// (info pour m'aider) Liste recherche / filtre & onglets desc (chien chat ect ... )
	// LUCAS [↑]
}
