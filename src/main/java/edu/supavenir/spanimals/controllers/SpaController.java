package edu.supavenir.spanimals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

<<<<<<< HEAD
import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.AnimalRepository;

@Controller
public class SpaController {
	
	@GetMapping("contact")
	private String ContactAction() {
		return "contact";
=======
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
>>>>>>> d9dae66a8fc10234dafe707f4e70779e2be4e47b
	}
	
	
}
