package edu.supavenir.spanimals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.AnimalRepository;

@RestController
public class SosController {
	
	@Autowired
	private AnimalRepository repo;
	
	@GetMapping("/soss")
	public Animal SosAnimal(){
		
		
		repo.findBySos(animal.getSos());
		
		if(==true) {
			return animal;
		}
		return null; 
	}
}
