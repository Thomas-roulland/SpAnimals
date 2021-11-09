package edu.supavenir.spanimals.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.RefugeRepository;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JsArray;

@Controller
@RequestMapping("/crud/refuge")
public class CrudRefuge {

	@Autowired
	private VueJS vue;

	@Autowired
	private RefugeRepository repo;

	@ModelAttribute(name = "vue")
	private VueJS getVue() {
		return this.vue;
	}

	@GetMapping
	public String indexAction() {
		Map<String, String> headers = new HashMap<>();

		vue.addData("mini", true);
		vue.addData("draw", true);
		vue.addData("refuge", repo.findAll());
		vue.addDataRaw("headers",
				"[{text:'ID', value:'id'},{text:'Complement Adresse', value:'complementad'},{text:'Code Postal', value:'cp'},{text:'Description', value:'description'},{text:'Localisation', value:'localisation'}"
						+ ",{text:'lock_flag', value:'lock_flag'}, ,{text:'nom', value:'nom'},{text:'Numero et Rue', value:'numeroetrue'},{text:'Telephone', value:'tel'},{text:'ville', value:'ville'}, { text: 'Actions', value: 'actions', sortable: false }]");
		vue.addData("dialog", false);
		vue.addData("dialogDelete", false);
		vue.addComputed("formTitle", " return this.editedIndex === -1 ? 'Nouvelle refuge' : 'modifier refuge'");
		vue.addData("editedIndex", -1);
		vue.addData("editedItem", new Refuge());
		vue.addMethod("close", "this.dialog=false; editedIndex=-1;");
		vue.addMethod("save", "if (this.editedIndex > -1) {\r\n"
				+ "          Object.assign(this.orgas[this.editedIndex], this.editedItem)\r\n" + "        } else {\r\n"
				+ "          this.refuge.push(this.editedItem)\r\n" + "        }\r\n" + "        this.close()");
		vue.addMethod("closeDelete",
				"  this.dialogDelete = false;\r\n" + "        this.$nextTick(() => {\r\n"
						+ "          this.editedItem = Object.assign({}, this.defaultItem)\r\n"
						+ "          this.editedIndex = -1\r\n" + "        })");
		vue.addMethod("deleteItemConfirm",
				"let self=this;" + Http.delete("'/admin/delete/refuge/'+this.editedItem.id", "this.closeDelete()"));

		vue.addMethod("deleteItem", "this.editedItem=item;\r\n" + "        this.dialogDelete = true", "item");

		vue.addMethod("editItem", "this.editedIndex = this.race.indexOf(item)\r\n"
				+ "        this.editedItem = Object.assign({}, item)\r\n" + "        this.dialog = true");
		vue.addWatcher("dialog", " val || this.close()");
		vue.addWatcher("dialogDelete", " val || this.closeDelete()");

		return "crudrefuge";
	}
}
