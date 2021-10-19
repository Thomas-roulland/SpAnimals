package edu.supavenir.spanimals.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jdi.connect.spi.Connection;

import edu.supavenir.spanimals.models.Espece;
import edu.supavenir.spanimals.models.Refuge;
import edu.supavenir.spanimals.repositories.EspeceRepository;
import edu.supavenir.spanimals.repositories.RefugeRepository;

@Controller
public class SpaController {

    @Autowired
    private EspeceRepository repo;

    @Autowired
    private RefugeRepository repoR;

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
<<<<<<< HEAD

}
=======
<<<<<<< HEAD

    @GetMapping("/login")
    private String LoginAction() {
	return "login";
    }

    @PostMapping("connecter")
    private @ResponseBody String addLogin(User user) {
	if (user.getName() == "user" && user.getPassword() == "user") {
	    return "success";
	} else {
	    return "marche pas";
	}
    }
}
=======
}
>>>>>>> 841303e52d685e7f69fc3617c4c4553e275ddc3b
>>>>>>> d256ccf1bf280848c0558c590d11bf89c876af14
