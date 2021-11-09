package edu.supavenir.spanimals.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Race;
import edu.supavenir.spanimals.repositories.RaceRepository;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;

@Controller
@RequestMapping("/crud/races")
public class CrudRace {

	@Autowired
	private VueJS vue;

	@Autowired
	private RaceRepository repo;

	@ModelAttribute(name = "vue")
	private VueJS getVue() {
		return this.vue;
	}

	@GetMapping
	public String indexAction() {
		Map<String, String> headers = new HashMap<>();

		vue.addData("mini", true);
		vue.addData("draw", true);
		vue.addData("races", repo.findAll());
		vue.addDataRaw("headers",
				"[{text:'ID', value:'id'},{text:'Descriptif', value:'descriptif'},{text:'libelle', value:'libelle'},{text:'Lock_flag', value:'lock_flag'},{text:'Prerequis', value:'prerequis'},{text:'idEspece', value:'espece.libelle'}, { text: 'Actions', value: 'actions', sortable: false }]");
		vue.addData("dialog", false);
		vue.addData("dialogDelete", false);
		vue.addComputed("formTitle", " return this.editedIndex === -1 ? 'Nouvelle race' : 'modifier race'");
		vue.addData("editedIndex", -1);
		vue.addData("editedItem", new Race());
		vue.addData("DefaultItem", new Race());

		vue.addMethod("close", "this.dialog=false; editedIndex=-1;");
		vue.addMethod("save", "if (this.editedIndex > -1) {\r\n"
				+ "          Object.assign(this.orgas[this.editedIndex], this.editedItem)\r\n" + "        } else {\r\n"
				+ "          this.race.push(this.editedItem)\r\n" + "        }\r\n" + "        this.close()");
		vue.addMethod("closeDelete",
				"  this.dialogDelete = false\r\n" + "        this.$nextTick(() => {\r\n"
						+ "          this.editedItem = Object.assign({}, this.defaultItem)\r\n"
						+ "          this.editedIndex = -1\r\n" + "        })");
		vue.addMethod("deleteItemConfirm",
				"let self=this;" + Http.delete("'/admin/delete/race/'+this.editedItem.id", "this.closeDelete()"));

		vue.addMethod("deleteItem", "this.editedItem=item;\r\n" + "        this.dialogDelete = true", "item");

		vue.addMethod("editItem", "this.editedIndex = this.race.indexOf(item)\r\n"
				+ "        this.editedItem = Object.assign({}, item)\r\n" + "        this.dialog = true");
		vue.addWatcher("dialog", " val || this.close()");
		vue.addWatcher("dialogDelete", " val || this.closeDelete()");

		return "crudrace";

	}
}