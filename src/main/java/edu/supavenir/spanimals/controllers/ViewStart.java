package edu.supavenir.spanimals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.github.jeemv.springboot.vuejs.VueJS;

@Controller
public class ViewStart {

	@Autowired
	private VueJS vue;

        @ModelAttribute(name = "vue")
        private VueJS getVue() {
            return this.vue;
        }
	
	@GetMapping("/")
	public String index(ModelMap model) {
		
		return "index";
	}
}
