package nl.computerhok.cruddie.controller;

import nl.computerhok.cruddie.entity.Appservergroup;
import nl.computerhok.cruddie.repositories.AppservergroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.core.MediaType;

@Controller
@RequestMapping("/appservergroup")
public class AppservergroupController {

    @Autowired
    AppservergroupRepository repository;

    @RequestMapping("/{appservergroupid}")
    public String showAppservergroupForm(@PathVariable("appservergroupid") Appservergroup appservergroup, Model model) {
        model.addAttribute("appservergroup", appservergroup);
        return "appservergroupForm";
    }

    @RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public String showAppservergroups(Model model, Pageable pageable) {
        model.addAttribute("appservergroups", repository.findAll(pageable));
        return "appservergroups";
    }
}