package controllers;

import entities.Med;
import repositories.interfaces.IRepositories;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("meds")
public class MedControl {
    @Inject
    private IRepositories repo;

    @GET
    @Path("search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchMedByName(@PathParam("name") String name) {
        List<Med> meds;

        try {
            meds = repo.searchMedByName(name);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(meds)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedByID(@PathParam("id") int id) {
        Med med;
        try {
            med = repo.getMedById(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (med == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Med does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(med)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMed(Med med) {
        LocalDate Expiration_date = LocalDate.now();
        boolean created;
        try {
            created = repo.addMed(med);
        } catch (ServerErrorException ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }

        if (!created) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Med cannot be created!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Med created successfully!")
                .build();
    }

    @GET
    @Path("/{removebyid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMedByID(@PathParam("removebyid") int id){
        boolean removeByID;
        try {
            removeByID = repo.removeMedById(id);
        } catch (ServerErrorException ex){
            return Response.serverError().entity(ex.getMessage()).build().
        }

        if (!removeByID){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Med cannot be removed!")
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Med successfully removed!")
                .build();
    }
}
