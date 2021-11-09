package edu.supavenir.spanimals.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Adoptant;
import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.repositories.AdoptantRepository;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JsArray;

@Controller
@RequestMapping("/crud/adoptant")
public class CrudAdoptant {

	@Autowired
	private VueJS vue;

	@Autowired
	private AdoptantRepository repo;

	@ModelAttribute(name = "vue")
	private VueJS getVue() {
		return this.vue;
	}

	@GetMapping
	public String indexAction() {
		Map<String, String> headers = new HashMap<>();

		vue.addData("mini", true);
		vue.addData("draw", true);
		vue.addData("adoptant", repo.findAll());
		vue.addDataRaw("headers",
				"[{text:'ID', value:'id'},{text:'Complement Adresse', value:'complementad'},{text:'Code Postal', value:'cp'},{text:'Date Naissance', value:'datenaiss'},{text:'Infos', value:'infoscomplementaires'},{text:'Lock_Flag', value:'lock_flag'}"
						+ ",{text:'Mail', value:'mail'},{text:'nom', value:'nom'},{text:'numeroetrue', value:'numeroetrue'},{text:'prenom', value:'prenom'},{text:'telfixe', value:'telfixe'},{text:'telmobile', value:'telmobile'},{text:'ville', value:'ville'},{text: 'Actions', value: 'actions', sortable: false }]");
		vue.addData("dialog", false);
		vue.addData("dialogDelete", false);
		vue.addComputed("formTitle", " return this.editedIndex === -1 ? 'Nouvelle adoptant' : 'modifier adoptant';");
		vue.addData("editedIndex", -1);
		vue.addData("editedItem", new Adoptant());
		vue.addMethod("close", "this.dialog=false; editedIndex=-1;");
		vue.addMethod("save", "if (this.editedIndex > -1) {\r\n"
				+ "          Object.assign(this.adoptant[this.editedIndex], this.editedItem)\r\n" + Http.put("'/admin/modifier/adoptant/'+this.editedItem.id", "this.closeDelete()")+ "        } else {\r\n"
				+ "          this.adoptant.push(this.editedItem)\r\n" + "        }\r\n" + "        this.close()");
		vue.addMethod("closeDelete",
				"  this.dialogDelete = false;\r\n" + "        this.$nextTick(() => {\r\n"
						+ "          this.editedItem = Object.assign({}, this.defaultItem);\r\n"
						+ "          this.editedIndex = -1;\r\n" + "})");
		vue.addMethod("deleteItemConfirm",
				"let self=this;" + Http.delete("'/admin/delete/adoptant/'+this.editedItem.id", "this.closeDelete();"));

		vue.addMethod("deleteItem", "this.editedItem=item;\r\n" + "        this.dialogDelete = true;", "item");

		vue.addMethod("editItem", "this.editedIndex = this.adoptant.indexOf(item);\r\n"
				+ "        this.editedItem = Object.assign({}, item);\r\n" 
				+ " this.dialog = true;","item");
		vue.addWatcher("dialog", " val || this.close();" );
		vue.addWatcher("dialogDelete", " val || this.closeDelete();");

		return "crudAdoptant";
	}
}
