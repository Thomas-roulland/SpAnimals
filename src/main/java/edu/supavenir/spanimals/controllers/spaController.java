package edu.supavenir.spanimals.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.supavenir.spanimals.conf.EmailSenderServices;
import edu.supavenir.spanimals.models.Adoptant;
import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.models.Race;
import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.adoptantRepository;
import edu.supavenir.spanimals.repositories.animalRepository;
import edu.supavenir.spanimals.repositories.especeRepository;
import edu.supavenir.spanimals.repositories.raceRepository;
import edu.supavenir.spanimals.repositories.refugeRepository;
import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
public class spaController {


    @Autowired
    private especeRepository repo;

    @Autowired
    private refugeRepository repoR;

    @Autowired
    private animalRepository AnimRepo;

    @Autowired
    private adoptantRepository RepoAdop;
    
    @Autowired
    private raceRepository RepoRace;

    @Autowired
	private VueJS vue;
    
    @Autowired
	private EmailSenderServices service;

        @ModelAttribute(name = "vue")
        private VueJS getVue() {
            return this.vue;
        }
	
	@GetMapping("/")
	public String index(ModelMap model) {
		
		return "index";
	}
	
	@GetMapping("/contact")
	public String contatc() {
		return "contact";
	}
	
	@PostMapping("/contact")
	public void sendContactAction() {
		service.sendSimpleEmail("spanimals.project@gmail.com", "Test", "Contact pour adoption");
	}
	
	
	
	@GetMapping("/mentionsLegales")
	public String mentions() {
		return "mentions";
	}
	
	@GetMapping("/missions")
	public String mission() {
		return "informations";
	}
	
	@GetMapping("/boardLeCrabeVampire")
	public String indexDash(ModelMap model) {
		List<Animal> animal = AnimRepo.findAll();
		model.addAttribute("animaux", animal.size());
		return "dashboard";
	}
	
	@GetMapping("/animal/{id}")
	private String FicheAnimalAction(@PathVariable int id, Model model) {
		Optional<Animal> animal = AnimRepo.findById(id);
		model.addAttribute("animalTest", animal.get());
		return "ficheAnimal";
	}
	

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

		List<Animal> animaux = AnimRepo.findAll();
		model.addAttribute("animaux", animaux);

		List<Refuge> refuges = repoR.findAll();
		model.addAttribute("refuges", refuges);

		List<Race> race = RepoRace.findAll();
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
		if (user.getName() == "admin" && user.getPassword() == "adminLeCrabe") {
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
