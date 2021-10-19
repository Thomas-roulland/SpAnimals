package edu.supavenir.spanimals.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.repositories.EspeceRepository;

@RequestMapping("/admin")
@RestController
public class AdminRestController {
    @Autowired
    private EspeceRepository repoE;

    @GetMapping("/list/espece")
    private String listEspece() {
	return repoE.findAll().toString();
    }

    @PostMapping("/add/espece")
    private @ResponseBody String ajouteEspece(Espece espece) {
	repoE.saveAndFlush(espece);
	return espece.toString();
    }

    @DeleteMapping("/delete/espece/{id}")
    public Espece deleteAction(@PathVariable int id) {
	Optional<Espece> opt = repoE.findById(id);
	if (opt.isPresent()) {
	    Espece model = opt.get();
	    repoE.delete(opt.get());
	    return model;
	}
	return null;

    }

    @PostMapping("/modifier/espece/{id}")
    private @ResponseBody String modifieEspece(Espece espece) {
	repoE.saveAndFlush(espece);
	return espece.toString();
    }

}
