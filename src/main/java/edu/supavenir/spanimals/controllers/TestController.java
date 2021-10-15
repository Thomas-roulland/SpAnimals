package edu.supavenir.spanimals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.AnimalRepository;

@Controller
public class TestController {
	
	@Autowired
	private AnimalRepository repo;
	
	@GetMapping("add/animal")
	public @ResponseBody String addAnimal() {
		Animal medor=new Animal();
		medor.setNom("Medor");
		medor.setCouleur("Blanc/marron");
		medor.setDescription("agressif");
		if(repo.save(medor) != null) {
			return medor+"ajouté en enfer";
		}
		return "Pb d'ajout";
	}
}
