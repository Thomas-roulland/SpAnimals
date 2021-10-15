package edu.supavenir.spanimals.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

	@GetMapping("contact")
	private String ContactAction() {
		return "contact";
	}
}
