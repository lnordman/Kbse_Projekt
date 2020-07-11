package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.rs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BenutzerRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nordm
 */
@Stateless //TODO: muss noch entfernt werden 
@Path("de.hsos.kbse.entity.benutzer")
public class BenutzerFacadeREST {

    public BenutzerFacadeREST() {
    }

    @Inject
    BenutzerRepository benutzerRepo;

    @POST
//    @Override
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void create(Benutzer entity) {
        benutzerRepo.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Benutzer entity) {
        benutzerRepo.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        benutzerRepo.remove(benutzerRepo.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Benutzer find(@PathParam("id") Long id) {
        return benutzerRepo.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Benutzer> findAll() {
        return benutzerRepo.findAll();
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Benutzer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return benutzerRepo.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(benutzerRepo.count());
    }

    @POST
    @Path("benutzer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createBenutzer(
            @QueryParam("name") String name,
            @QueryParam("vorname") String vorname,
            @QueryParam("email") String email,
            @QueryParam("telefon") String telefon,
            @QueryParam("ort") String ort,
            @QueryParam("straße") String straße,
            @QueryParam("plz") Integer plz) {
        try {
            Benutzer benutzer = new Benutzer(name, vorname, email, telefon, ort, straße, plz);
            benutzerRepo.create(benutzer);
//      JsonbConfig config = new JsonbConfig().withPropertyNamingStrategy(
//  PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES);
//       JsonObject object = Json.createObjectBuilder().build();
//Jsonb jsonb = JsonbBuilder.create(config);
//String jsonPerson = jsonb.toJson(benutzer);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        } catch (NullPointerException | IllegalArgumentException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

//    @Override
//    public Response createBenutzer(String name, String vorname, String email, String telefon, String ort, String straße, Integer plz) {
//        try {
//            Benutzer benutzer = new Benutzer(name, vorname, email, telefon, ort, straße, plz);
//            em.persist(benutzer);
//            return Response
//                    .status(Response.Status.FOUND)
//                    .build();
//        } catch (NullPointerException | IllegalArgumentException ex) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
}
