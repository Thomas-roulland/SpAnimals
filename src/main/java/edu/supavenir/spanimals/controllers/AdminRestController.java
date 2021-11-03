package edu.supavenir.spanimals.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.models.Race;
import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RaceRepository;
import edu.supavenir.spanimals.repositories.RefugeRepository;

@RequestMapping("/admin")
@RestController
public class AdminRestController {
    @Autowired
    private EspeceRepository repoE;
    @Autowired
    private RefugeRepository repoR;
    @Autowired
    private RaceRepository repor;

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
    private @ResponseBody String modifierEspece(@PathVariable int id, Espece espece) {
	repoE.save(espece);
	return espece.toString();
    }
    
    
    
    @PostMapping("add/refuge")
    private @ResponseBody String ajouteRefuge(Refuge refuge) {
	repoR.saveAndFlush(refuge);
	return refuge.toString();
    }
    
    @PostMapping("/modifier/Refuge/{id}")
    private String AdminRefugeAction(Refuge refuge) {
    	repoR.save(refuge);
		return refuge.toString();
    }
    
    @DeleteMapping("/delete/Refuge/{id}")
    public Refuge deleteRefugeAction(@PathVariable int id) {
	Optional<Refuge> refuge = repoR.findById(id);
	if (refuge.isPresent()) {
	    Refuge model = refuge.get();
	    repoE.deleteById(id);
	    return model;
	}
	return null;
    }
    
    @PostMapping("add/Race")
    private @ResponseBody String ajouteRace(Race race) {
	repor.saveAndFlush(race);
	return race.toString();
    }
    
    @PostMapping("/modifier/Race/{id}")
    private String AdminRaceAction(Race race) {
    	repoR.save(race);
		return race.toString();
    }
    
    @DeleteMapping("/delete/Race/{id}")
    public Race deleteRaceAction(@PathVariable int id) {
	Optional<Race> race = repor.findById(id);
	if (race.isPresent()) {
	    Race model = race.get();
	    repoE.deleteById(id);
	    return model;
	}
	return null;
    }
}
