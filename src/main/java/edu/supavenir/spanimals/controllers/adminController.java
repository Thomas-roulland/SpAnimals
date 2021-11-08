package edu.supavenir.spanimals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.models.Race;
import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.especeRepository;
import edu.supavenir.spanimals.repositories.raceRepository;
import edu.supavenir.spanimals.repositories.refugeRepository;

@RequestMapping("/admin")
@Controller
public class adminController {

	@Autowired
	private especeRepository repoE;
	@Autowired
	private refugeRepository repoR;
	@Autowired
	private raceRepository repor;

	@GetMapping("/add/espece")
	private String addEspece() {
		return "formAddEspece";
	}

	@GetMapping("/modifier/espece/{id}")
	private String AdminEspeceAction(Model model) {
		List<Espece> especes = repoE.findAll();
		model.addAttribute("especes", especes);
		return "formModifierEspece";
	}

	@GetMapping("/add/refuge")
	private String addRefuge() {
		return "formAddRefuge";
	}

	@GetMapping("/modifier/refuge/{id}")
	private String AdminRefugeAction(Model model) {
		List<Refuge> refuges = repoR.findAll();
		model.addAttribute("refuges", refuges);
		return "formModifierRefuge";
	}

	@GetMapping("/add/race")
	private String addRace(Model model) {
		List<Race> race = repor.findAll();
		model.addAttribute("race", race);
		return "formAddRace";
	}

	@GetMapping("/modifier/race/{id}")
	private String AdminRaceAction(Model model) {
		List<Race> race = repor.findAll();
		model.addAttribute("refuges", race);
		return "formModifierRace";
	}
}
