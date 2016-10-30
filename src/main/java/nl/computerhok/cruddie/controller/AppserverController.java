package nl.computerhok.cruddie.controller;

import nl.computerhok.cruddie.entity.Appserver;
import nl.computerhok.cruddie.repositories.AppserverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appserver")
public class AppserverController {

    @Autowired
    AppserverRepository repository;

    @RequestMapping("/{appserverid}")
    public String showAppserverForm(@PathVariable("appserverid") Appserver appserver, Model model) {

        model.addAttribute("appserver", appserver);
        return "appserverForm";
    }

    @RequestMapping("/")
    public String list(Model model, Pageable pageable) {
        model.addAttribute("appservers", repository.findAll(pageable));
        return "appservers";
    }
}