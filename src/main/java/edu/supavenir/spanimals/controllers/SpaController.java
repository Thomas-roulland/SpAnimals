package edu.supavenir.spanimals.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.supavenir.spanimals.models.Adoptant;
import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.models.Race;
import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.AdoptantRepository;
import edu.supavenir.spanimals.repositories.AnimalRepository;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RaceRepository;
import edu.supavenir.spanimals.repositories.RefugeRepository;
import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
public class SpaController {

    @Autowired
    private EspeceRepository repo;

    @Autowired
    private RefugeRepository repoR;

    @Autowired
    private AnimalRepository Repo;

    @Autowired
    private AdoptantRepository RepoAdop;

    
    @Autowired
    private RaceRepository RepoRace;

   
    

    @GetMapping("/employe")
    public String AfficheAdoptant(Model model) {

	List<Adoptant> adoptant = RepoAdop.findAll();
	model.addAttribute("adoptant", adoptant);
	return "employe";
    }

    @GetMapping("/animal")
    private String ContactAction(Model model) {
	List<Espece> especes = repo.findAll();
	model.addAttribute("especes", especes);
	
	List<Animal> animaux = Repo.findAll();
	model.addAttribute("animaux", animaux);

	List<Refuge> refuges = repoR.findAll();
	model.addAttribute("refuges", refuges);
	
	List<Race> race= RepoRace.findAll();
	model.addAttribute("race", race);
	return "animal";
    }

    @PostMapping("search")
    public @ResponseBody String addAction(HttpServletRequest request) throws SQLException {

	Map<String, String[]> map = request.getParameterMap();
	String s = "";
	String test = "";
	String test1 = "";

	for (Map.Entry<String, String[]> entry : map.entrySet()) {
	    s += ("Key = " + entry.getKey() + ", Value = " + entry.getValue()[0]) + "<br>";
	    if (entry.getValue() != null) {
		test += entry.getKey() + "= :" + entry.getKey();
	    }

	}
	test1 = "SELECT * FROM Animal WHERE " + test + ";";
	return test1;
    }

    @GetMapping("/login")
    private String LoginAction() {
	return "login";
    }

    @GetMapping("/hello")
    private String HelloAction() {
	return "hello";
    }

    @PostMapping("connecter")
    private @ResponseBody String addLogin(User user) {
	if (user.getName() == "user" && user.getPassword() == "user") {
	    return "success";
	} else {
	    return "marche pas";
	}

    }

    @PostMapping("logout")
    private @ResponseBody String addLogout(HttpServletRequest request) {
	HttpSession httpSession = request.getSession();
	httpSession.invalidate();
	return "home";
    }
}
