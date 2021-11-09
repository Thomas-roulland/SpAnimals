package edu.supavenir.spanimals.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Adoptant;
import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.adoptantRepository;
import edu.supavenir.spanimals.repositories.especeRepository;
import edu.supavenir.spanimals.repositories.raceRepository;
import edu.supavenir.spanimals.repositories.refugeRepository;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
@RequestMapping("/employe")
@Controller
public class employeController {
	
	@Autowired
	private VueJS vue;
    @Autowired
    private adoptantRepository RepoAdop;
    @Autowired
    private raceRepository repor;
    @Autowired
    private refugeRepository repoR;
    @Autowired
    private especeRepository repoE;
    
	  @ModelAttribute(name = "vue")
	    private VueJS getVue() {
		return this.vue;
	    }
	  
    // AJOUTER UN ANIMAL
    @GetMapping("/add/animal")
    private String addAnimal(Model model) {
    	vue.addData("refuge",repoR.findAll());
    	vue.addData("race",repor.findAll());
    	vue.addData("especes",repoE.findAll());
		vue.addData("newAnimal",new Animal());
		vue.addDataRaw("dialog", "{visible:false, mode:0}");
		vue.addMethod("validate", "if(this.dialog.mode==0){\r\n"+Http.post("/employe/add/animal", "this.newAnimal","this.animal.push(response.data);")+"}");
		vue.addData("valid",true);
		vue.addDataRaw("Rules", "[\r\n"
				+ "					    v => !!v || 'champ obligatoire',\r\n"
				+ "					  ]");
		vue.addDataRaw("Alerte", "[\r\n"
				+ "			        { value: false,  sos: 'Non' },\r\n"
				+ "			        { value: true,  sos: 'Oui' }\r\n"
				+ "			      ]");
		vue.addDataRaw("Adopte", "[\r\n"
				+ "				    { value: false,  adopte: 'Non' },\r\n"
				+ "				    { value: true,  adopte: 'Oui' }\r\n"
				+ "				  ]");
		vue.addDataRaw("Sexe", "[\r\n"
				+ "					'male',\r\n"
				+ "					'femelle'\r\n"
				+ "				]");
		vue.addData("checkbox", false);
		vue.addMethod("reset", "this.$refs.form.reset()");
		vue.addMethod("resetValidation", "this.$refs.form.resetValidation();");
	return "formAddAni";
    }

    @GetMapping("/add/adoptant")
    public String addAdoptant() {
    	vue.addData("newAdoptant",new Adoptant());
		vue.addDataRaw("dialog", "{visible:false, mode:0}");
		vue.addDataRaw("CpRules", "[\r\n"
				+ "			        v => !!v || 'champ obligatoire',\r\n"
				+ "			        v => (v && v.length <= 5 && v.length >4) || 'le Code Postal doit être égal à 5 caractères',\r\n"
				+ "			      ]");
		vue.addDataRaw("TelRules","[\r\n"
				+ "				        v => !!v || 'champ obligatoire',\r\n"
				+ "				        v => (v && v.length <= 10 && v.length >9) || 'le numéro de téléphone doit être égal à 10 caractères',\r\n"
				+ "				      ]");
		vue.addDataRaw("emailRules", "[\r\n"
				+ "			        v => !!v || 'champ obligatoire',\r\n"
				+ "			        v => /.+@.+\\..+/.test(v) || 'E-mail doit être valide ',\r\n"
				+ "			      ]");
		vue.addData("valid",true);
		vue.addDataRaw("Rules", "[\r\n"
				+ "					    v => !!v || 'champ obligatoire',\r\n"
				+ "					  ]");
		vue.addData("checkbox", false);
		vue.addMethod("validate", "if(this.dialog.mode==0){\r\n"+Http.post("/employe/add/adoptant", "this.newAdoptant","this.adoptant.push(response.data);")+"}");
		vue.addMethod("reset", "this.$refs.form.reset()");
		vue.addMethod("resetValidation", "this.$refs.form.resetValidation();");
	return "formAddAdop";
    }

    @GetMapping("/edit/adoptant/{id}")
    public String ActionEditFormAdoptant(Model model, @PathVariable int id) {

	Optional<Adoptant> Adoptant = RepoAdop.findById(id);
	model.addAttribute("Adoptant", Adoptant);
	return "formEditAdop";
    }
}
