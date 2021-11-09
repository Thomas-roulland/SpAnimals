package edu.supavenir.spanimals.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/refuge")
	public List<Refuge> indexAction() {
		return repoR.findAll();
	}

	@GetMapping("/espece")
	private List<Espece> listEspece() {
		return repoE.findAll();
	}

	@GetMapping("/race")
	private List<Race> listRace() {
		return repor.findAll();
	}
	
	@GetMapping("/race/{id}")
	private Optional<Race> listRace(@PathVariable int id) {
		return repor.findById(id);
	}

	@PostMapping("/add/espece")
	private @ResponseBody String ajouteEspece(@RequestBody Espece espece) {
		repoE.saveAndFlush(espece);
		return espece.toString();
	}

	

	@PostMapping("/modifier/espece/{id}")
	private @ResponseBody String modifierEspece(@PathVariable int id, @RequestBody Espece espece) {
		repoE.save(espece);
		return espece.toString();
	}

	@PostMapping("/add/refuge")
	private @ResponseBody String ajouteRefuge(@RequestBody Refuge refuge) {
		repoR.saveAndFlush(refuge);
		return refuge.toString();
	}

	@PostMapping("/modifier/refuge/{id}")
	private String AdminRefugeAction(@RequestBody Refuge refuge) {
		repoR.save(refuge);
		return refuge.toString();
	}

	

	@PostMapping("add/race")
	private @ResponseBody String ajouteRace(@RequestBody Race race) {
		repor.saveAndFlush(race);
		return race.toString();
	}

	@PostMapping("/modifier/race/{id}")
	private String AdminRaceAction(@RequestBody Race race) {
		repoR.save(race);
		return race.toString();
	}

	@DeleteMapping("/delete/race/{id}")
	public Race deleteRaceAction(@PathVariable int id) {
		Optional<Race> opt = repor.findById(id);
		if (opt.isPresent()) {
			Race race = opt.get();
			repor.delete(race);
			return null;
		}
		return null;
	}
	@DeleteMapping("/delete/refuge/{id}")
	public Refuge deleteRefugeAction(@PathVariable int id) {
		Optional<Refuge> refuge = repoR.findById(id);
		if (refuge.isPresent()) {
			Refuge model = refuge.get();
			repoE.deleteById(id);
			return model;
		}
		return null;
	}
	@DeleteMapping("/delete/espece/{id}")
	public Espece deleteEspeceAction(@PathVariable int id) {
		Optional<Espece> opt = repoE.findById(id);
		if (opt.isPresent()) {
			Espece model = opt.get();
			repoE.delete(opt.get());
			return model;
		}
		return null;

	}
	@DeleteMapping("/delete/animal/{id}")
	public Race deleteAnimalAction(@PathVariable int id) {
		Optional<Race> opt = repor.findById(id);
		if (opt.isPresent()) {
			Race race = opt.get();
			repor.delete(race);
			return null;
		}
		return null;
	}
	@DeleteMapping("/delete/adoptant/{id}")
	public Refuge deleteAdoptantAction(@PathVariable int id) {
		Optional<Refuge> refuge = repoR.findById(id);
		if (refuge.isPresent()) {
			Refuge model = refuge.get();
			repoE.deleteById(id);
			return model;
		}
		return null;
	}
}
