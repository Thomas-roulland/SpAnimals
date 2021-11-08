package edu.supavenir.spanimals.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.repositories.AdoptantRepository;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import io.github.jeemv.springboot.vuejs.utilities.JsArray;

@Controller
@RequestMapping("/crud/adoptant")
public class crudAdoptant {

	
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
			+ ",{text:'Mail', value:'mail'},{text:'nom', value:'nom'},{text:'numeroetrue', value:'numeroetrue'} ,{text:'prenom', value:'prenom'},{text:'telfixe', value:'telfixe'},{text:'telmobile', value:'telmobile'},,{text:'ville', value:'ville'}]");
		vue.addData("dialog", false);
		vue.addData("dialogDelete", false);
		vue.addComputed("formTitle",
			" return this.editedIndex === -1 ? 'Nouvelle espece' : 'modifier adoptant'");
		vue.addData("editedIndex", -1);
		vue.addMethod("remove",
			"let self=this;" + Http.delete("'/admin/delete/adoptant/'+adoptant.id", JsArray.remove("self.adoptant", "adoptant"), "adoptant"));
		vue.addData("editedItem", new Espece());
		vue.addMethod("close", "this.dialog=false; editedIndex=-1;");
		vue.addMethod("save", "if (this.editedIndex > -1) {\r\n"
			+ "          Object.assign(this.orgas[this.editedIndex], this.editedItem)\r\n" + "        } else {\r\n"
			+ "          this.adoptant.push(this.editedItem)\r\n" + "        }\r\n" + "        this.close()");
		vue.addMethod("closeDelete",
			"  this.dialogDelete = false\r\n" + "        this.$nextTick(() => {\r\n"
				+ "          this.editedItem = Object.assign({}, this.defaultItem)\r\n"
				+ "          this.editedIndex = -1\r\n" + "        })");
		vue.addMethod("deleteItemConfirm",
			" this.adoptant.splice(this.editedIndex, 1)\r\n" + "        this.closeDelete()");

		return "crudAdoptant";
	    }
}
