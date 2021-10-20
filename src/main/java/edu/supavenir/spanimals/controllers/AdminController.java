package edu.supavenir.spanimals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.repositories.EspeceRepository;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private EspeceRepository repoE;

    @GetMapping("/add/espece")
    private String addEspece() {
	return "FormAddEspece";
    }

    @GetMapping("/modifier/espece/{1}")
    private String AdminEspeceAction(Model model) {
	List<Espece> especes = repoE.findAll();
	model.addAttribute("especes", especes);
	return "FormModifierEspece";
    }
}
