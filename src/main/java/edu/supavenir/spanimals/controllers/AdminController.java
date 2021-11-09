package edu.supavenir.spanimals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.models.Race;
import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RaceRepository;
import edu.supavenir.spanimals.repositories.RefugeRepository;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private VueJS vue;

	@Autowired
	private EspeceRepository repoE;
	@Autowired
	private RefugeRepository repoR;
	@Autowired
	private RaceRepository repor;

	@GetMapping("/add/espece")
	private String addEspece() {
		vue.addData("newEspece", new Espece());
		vue.addDataRaw("dialog", "{visible:false, mode:0}");
		vue.addMethod("validate", "if(this.dialog.mode==0){\r\n"
				+ Http.post("/admin/add/espece", "this.newEspece", "this.espece.push(response.data);") + "}");
		vue.addData("valid", true);
		vue.addDataRaw("Rules",
				"[\r\n" + "					    v => !!v || 'champ obligatoire',\r\n" + "					  ]");
		vue.addData("checkbox", false);
		vue.addMethod("reset", "this.$refs.form.reset()");
		vue.addMethod("resetValidation", "this.$refs.form.resetValidation();");
		return "formAddEspece";
	}

	@ModelAttribute(name = "vue")
	private VueJS getVue() {
		return this.vue;
	}

	@GetMapping("/modifier/espece/{id}")
	private String AdminEspeceAction(Model model) {
		List<Espece> especes = repoE.findAll();
		model.addAttribute("especes", especes);
		return "formModifierEspece";
	}

	@GetMapping("/add/refuge")
	private String addRefuge() {
		vue.addData("newRefuge", new Refuge());
		vue.addDataRaw("dialog", "{visible:false, mode:0}");
		vue.addMethod("validate", "if(this.dialog.mode==0){\r\n"
				+ Http.post("/admin/add/refuge", "this.newRefuge", "this.refuge.push(response.data);") + "}");
		vue.addData("valid", true);
		vue.addDataRaw("Rules",
				"[\r\n" + "					    v => !!v || 'champ obligatoire',\r\n" + "					  ]");
		vue.addData("checkbox", false);
		vue.addMethod("reset", "this.$refs.form.reset()");
		vue.addMethod("resetValidation", "this.$refs.form.resetValidation();");
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
		vue.addData("especes", repoE.findAll());
		vue.addData("newRace", new Race());
		vue.addDataRaw("dialog", "{visible:false, mode:0}");
		vue.addMethod("validate", "if(this.dialog.mode==0){\r\n"
				+ Http.post("/admin/add/race", "this.newRace", "this.race.push(response.data);") + "}");
		vue.addData("valid", true);
		vue.addDataRaw("Rules",
				"[\r\n" + "	 v => !!v || 'champ obligatoire',\r\n" + "	  ]");
		vue.addData("checkbox", false);
		vue.addMethod("reset", "this.$refs.form.reset()");
		vue.addMethod("resetValidation", "this.$refs.form.resetValidation();");
		return "formAddRace";
	}

	@GetMapping("/modifier/race/{id}")
	private String AdminRaceAction(Model model) {
		List<Race> race = repor.findAll();
		model.addAttribute("refuges", race);
		return "formModifierRace";
	}
}
