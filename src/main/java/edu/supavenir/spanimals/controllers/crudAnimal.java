package edu.supavenir.spanimals.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Animal;
import edu.supavenir.spanimals.repositories.animalRepository;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JsArray;

@Controller
@RequestMapping("/crud/animaux")
public class crudAnimal {

	
	  @Autowired
	    private VueJS vue;
	  
	  @Autowired
	    private animalRepository repo;

	  @ModelAttribute(name = "vue")
	    private VueJS getVue() {
		return this.vue;
	    }
	  
	  
	  @GetMapping
	    public String indexAction() {
		Map<String, String> headers = new HashMap<>();

		vue.addData("mini", true);
		vue.addData("draw", true);
		vue.addData("animal", repo.findAll());
		vue.addDataRaw("headers",
			"[{text:'ID', value:'id'},{text:'Adopte', value:'adopte'},{text:'Couleur', value:'couleur'},{text:'Description', value:'description'},{text:'Date Naissance', value:'dnaissance'},{text:'Frais', value:'frais'}"
			+ ",{text:'Lock_Flag', value:'Lock_flag'} ,{text:'Nom', value:'nom'}, ,{text:'Sexe', value:'sexe'},{text:'Similaire', value:'similaire'}, ,{text:'Sos', value:'sos'}, ,{text:'Espece', value:'espece.libelle'},"
			+ ",{text:'Race', value:'race.libelle'}, ,{text:'Refuge', value:'refuge.nom'}, ,{text:'Image', value:'img'}]");
		vue.addData("dialog", false);
		vue.addData("dialogDelete", false);
		vue.addData("editedIndex", -1);
		vue.addData("editedIndex", "{name: '',\r\n"
				+ "        calories: 0,\r\n"
				+ "        fat: 0,\r\n"
				+ "        carbs: 0,\r\n"
				+ "        protein: 0,}");
		vue.addData("defaultItem", " {name: '',\r\n"
				+ "        calories: 0,\r\n"
				+ "        fat: 0,\r\n"
				+ "        carbs: 0,\r\n"
				+ "        protein: 0,}");
		
		
		vue.addComputed("formTitle",
			" return this.editedIndex === -1 ? 'Nouvelle animal' : 'modifier animal'");
		vue.addData("editedIndex", -1);
		vue.addMethod("remove",
			"let self=this;" + Http.delete("'/admin/delete/animal/'+animal.id", JsArray.remove("self.animal", "animal"), "animal"));
		vue.addData("editedItem", new Animal());
		vue.addMethod("close", "this.dialog=false; editedIndex=-1;");
		vue.addMethod("save", "if (this.editedIndex > -1) {\r\n"
			+ "          Object.assign(this.orgas[this.editedIndex], this.editedItem)\r\n" + "        } else {\r\n"
			+ "          this.animal.push(this.editedItem)\r\n" + "        }\r\n" + "        this.close()");
		vue.addMethod("closeDelete",
			"  this.dialogDelete = false\r\n" + "        this.$nextTick(() => {\r\n"
				+ "          this.editedItem = Object.assign({}, this.defaultItem)\r\n"
				+ "          this.editedIndex = -1\r\n" + "        })");
		vue.addMethod("deleteItemConfirm",
			" this.animal.splice(this.editedIndex, 1)\r\n" + "        this.closeDelete()");
		vue.addMethod("deleteItem(item)", "this.editedIndex = this.animal.indexOf(item)\r\n"
				+ "        this.editedItem = Object.assign({}, item)\r\n"
				+ "        this.dialogDelete = true");

		return "crudanimal";
	    }
}
