package edu.supavenir.spanimals.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.supavenir.spanimals.models.Espece;
<<<<<<< HEAD
import edu.supavenir.spanimals.repositories.EspeceRepository;
=======
import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RefugeRepository;
>>>>>>> e6598dbe0dac642a6297864319b103df100291f3

@Controller
public class SpaController {

    @Autowired
    private EspeceRepository repo;

<<<<<<< HEAD
=======
    @Autowired
    private RefugeRepository repoR;

>>>>>>> e6598dbe0dac642a6297864319b103df100291f3
    @GetMapping("recherche")
    private String ContactAction(Model model) {
	List<Espece> especes = repo.findAll();
	model.addAttribute("especes", especes);
<<<<<<< HEAD
	return "recherche";

    }

}
=======

	List<Refuge> refuges = repoR.findAll();
	model.addAttribute("refuges", refuges);
	return "recherche";
    }

    @PostMapping("search")
    public @ResponseBody String addAction(HttpServletRequest request) throws SQLException {

	Map<String, String[]> map = request.getParameterMap();
	String s = "";
	String test = "";
	String test1 = "";
	Connection connection = null;

	for (Map.Entry<String, String[]> entry : map.entrySet()) {
	    s += ("Key = " + entry.getKey() + ", Value = " + entry.getValue()[0]) + "<br>";
	    if (entry.getValue() != null) {
		test += entry.getKey() + "= :" + entry.getKey();
	    }

	}
	test1 = "SELECT * FROM Animal WHERE " + test + ";";
	return test1;
    }
}
>>>>>>> e6598dbe0dac642a6297864319b103df100291f3
