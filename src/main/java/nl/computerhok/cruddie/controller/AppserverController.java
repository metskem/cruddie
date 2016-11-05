package nl.computerhok.cruddie.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nl.computerhok.cruddie.entity.Appserver;
import nl.computerhok.cruddie.repository.AppserverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Component
@Path("/appserver/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppserverController {
    private static final Logger LOG = LoggerFactory.getLogger(AppserverController.class);
    public static final String RESOURCE_PATH = "/appserver/v1";
    private AppserverRepository appserverRepository;

    @Autowired
    public AppserverController(AppserverRepository appserverRepository) {
        this.appserverRepository = appserverRepository;
    }

    @GET
    @ApiOperation(value = "returns a list of appservers")
    public Iterable<Appserver> list() {
        return appserverRepository.findAll();
    }

    @DELETE
    @Path("{id}")
    @ApiOperation(value = "delete the appserver")
    public Response delete(@ApiParam(value = "the id of the appserver to delete") @PathParam("id") long id) {
        appserverRepository.delete(id);
        LOG.warn("deleted appserver with id=" + id);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "get one appserver")
    public Appserver get(@ApiParam(value = "the id of the appserver") @PathParam("id") long id) {
        return appserverRepository.findOne(id);
    }

    @POST
    @ApiOperation(value = "insert one appserver")
    public Response save(@ApiParam(value = "the appserver to save", required = true) Appserver as) {
        try {
            LOG.info("trying to save " + as);
            Appserver savedAs = appserverRepository.save(as);
            if (savedAs == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("failed to insert ").build();
            }
            LOG.warn("saved " + savedAs);
            return Response.created(new URI(RESOURCE_PATH + "/" + savedAs.getId())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
