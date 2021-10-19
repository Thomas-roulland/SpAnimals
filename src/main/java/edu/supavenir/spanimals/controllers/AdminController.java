package edu.supavenir.spanimals.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("/add/espece")
    private String addEspece() {
	return "FormAddEspece";
    }

    @GetMapping("/modifier/espece/{1}")
    private String modifierEspece() {
	return "FormModifierEspece";
    }
}
