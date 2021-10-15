package edu.supavenir.spanimals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.repositories.AnimalRepository;
import edu.supavenir.spanimals.repositories.EspeceRepository;


@Controller
public class SpaController {

	@Autowired
	private EspeceRepository repo;
	
	@GetMapping("recherche")
	private  String ContactAction(Model model) {
		List<Espece> especes = repo.findAll();
		model.addAttribute("especes", especes);
		return "recherche";
	}
	
	
}
