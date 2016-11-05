package nl.computerhok.cruddie.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nl.computerhok.cruddie.entity.Appservergroup;
import nl.computerhok.cruddie.repository.AppservergroupRepository;
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
import java.net.URISyntaxException;

@Component
@Path("/appservergroup/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppservergroupController {
    private static final Logger LOG = LoggerFactory.getLogger(AppservergroupController.class);
    public static final String RESOURCE_PATH = "/appservergroup/v1";
    private AppservergroupRepository appservergroupRepository;

    @Autowired
    public AppservergroupController(AppservergroupRepository appservergroupRepository) {
        this.appservergroupRepository = appservergroupRepository;
    }

    @GET
    @ApiOperation(value = "returns a list of appservergroups")
    public Iterable<Appservergroup> list() {
        return appservergroupRepository.findAll();
    }

    @DELETE
    @Path("{id}")
    @ApiOperation(value = "delete the appservergroup")
    public Response delete(@ApiParam(value = "the id of the appservergroup to delete") @PathParam("id") long id) {
        appservergroupRepository.delete(id);
        LOG.warn("deleted appservergroup with id=" + id);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "get one appservergroup")
    public Appservergroup get(@ApiParam(value = "the id of the appservergroup") @PathParam("id") long id) {
        return appservergroupRepository.findOne(id);
    }

    @POST
    public Response save(@ApiParam(value = "the appservergroup to save", required = true) Appservergroup ag) throws URISyntaxException {
        try {
            LOG.info("trying to save " + ag);
            Appservergroup savedAg = appservergroupRepository.save(ag);
            if (savedAg == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("failed to insert ").build();
            }
            LOG.warn("saved " + savedAg);
            return Response.created(new URI(RESOURCE_PATH + "/" + savedAg.getId())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
