package nl.computerhok.cruddie.api.controller;

import nl.computerhok.cruddie.entity.Appservergroup;
import nl.computerhok.cruddie.repository.AppservergroupRepository;
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
public class AppservergroupController {
    private final static Logger LOG = LoggerFactory.getLogger(AppservergroupController.class);
    public final static String PATH = "/v1/appservergroup";

    @Autowired
    AppservergroupRepository appservergroupRepository;

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public Iterable<Appservergroup> list(Pageable pageable) {
        return appservergroupRepository.findAll(pageable);
    }

    @RequestMapping(value = PATH + "/{id}", method = RequestMethod.GET)
    public Appservergroup read(@PathVariable("id") long id) {
        return appservergroupRepository.findOne(id);
    }

    @RequestMapping(value = PATH + "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        appservergroupRepository.delete(id);
        LOG.warn("deleted appservergroup, id=" + id);
    }

    @RequestMapping(value = PATH, method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void create(@RequestBody Appservergroup appservergroup, HttpServletRequest request, HttpServletResponse response) {
        Appservergroup savedAg = appservergroupRepository.save(appservergroup);
        LOG.warn("saved " + savedAg);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{appservergroup}").expand(requestUrl, appservergroup.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
}
