package edu.supavenir.spanimals.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.AnimalRepository;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RefugeRepository;

@Controller
public class SpaController {

    @Autowired
    private EspeceRepository repo;
    @Autowired
    private RefugeRepository repoR;
    @Autowired
    private AnimalRepository repoA;

    @GetMapping("recherche")
    private String ContactAction(Model model) {
	List<Espece> especes = repo.findAll();
	model.addAttribute("especes", especes);
	List<Refuge> refuges = repoR.findAll();
	model.addAttribute("refuges", refuges);
	return "recherche";
    }

    @PostMapping("search")
    public @ResponseBody String addAction(HttpServletRequest request) throws SQLException {

	Map<String, String[]> map = request.getParameterMap();
	String s = "";
	String test = "";
	Connection connection = null;

	for (Map.Entry<String, String[]> entry : map.entrySet()) {
	    s += ("Key = " + entry.getKey() + ", Value = " + entry.getValue()[0]) + "<br>";
	    if (entry.getValue() != null) {
		test = entry.getKey() + "= :" + entry.getKey() + "";
	    }

	}
	PreparedStatement requete = connection.prepareStatement("SELECT * FROM Animal WHERE ");
	return s;

    }
}