package nl.computerhok.cruddie.api.controller;

import nl.computerhok.cruddie.entity.Appserver;
import nl.computerhok.cruddie.repository.AppserverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class AppserverController {
    private final static Logger LOG = LoggerFactory.getLogger(AppserverController.class);
    public final static String PATH = "/v1/appserver";

    @Autowired
    AppserverRepository appserverRepository;

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public Iterable<Appserver> list(Pageable pageable) {
        return appserverRepository.findAll(pageable);
    }

    @RequestMapping(value = PATH + "/{id}", method = RequestMethod.GET)
    public Appserver read(@PathVariable("id") long id) {
        return appserverRepository.findOne(id);
    }

    @RequestMapping(value = PATH + "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        appserverRepository.delete(id);
        LOG.warn("deleted appserver, id=" + id);
    }

    @RequestMapping(value = PATH, method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void create(@RequestBody Appserver appserver, HttpServletRequest request, HttpServletResponse response) {
        Appserver savedAs = appserverRepository.save(appserver);
        LOG.warn("saved " + savedAs);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{appserver}").expand(requestUrl, appserver.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
}
