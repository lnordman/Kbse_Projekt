/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.rs;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BewerberRepository;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.bind.JsonbException;
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
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author nordm
 */
@Stateless
@Path("de.hsos.kbse.entity.bewerber")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BewerberFacadeREST implements Serializable {

    public BewerberFacadeREST() {

    }

    @Inject
    BewerberRepository bewerberRepo;
    @Inject
    Jsonb jsonb;
    @Context
    UriInfo uriInfo;

    /**
     *
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAll() {
        return Response.ok(jsonb.toJson(bewerberRepo.findAll())).build();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        return Response.ok(bewerberRepo.find(id)).build();
    }

    /**
     *
     * @param name
     * @param vorname
     * @param email
     * @param password
     * @param telefon
     * @param ort
     * @param straße
     * @param plz
     * @param unterlagen_pfad
     * @return
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createBewerber(
            @QueryParam("name") String name,
            @QueryParam("vorname") String vorname,
            @QueryParam("email") String email,
            @QueryParam("password") String password,
            @QueryParam("telefon") String telefon,
            @QueryParam("ort") String ort,
            @QueryParam("straße") String straße,
            @QueryParam("plz") Integer plz,
            @QueryParam("unterlagen_pfad") String unterlagen_pfad){
        try {
            Login login = new Login(email, password);
            Bewerber bewerber = new Bewerber(name, vorname, telefon, straße, ort, plz, unterlagen_pfad, login);
            bewerberRepo.create(bewerber);

//            System.out.println("Created order " + bewerber.getId());
//            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
//            builder.path(Integer.toString(Math.toIntExact(bewerber.getId())));
//            return Response.created(builder.build()).build();

//  URI bewerberUri = uriInfo.getBaseUriBuilder()
//          .path(BewerberFacadeREST.class)
//          .path(Integer.toString(Math.toIntExact(bewerber.getId()))).build();
   

return Response.created(URI.create("/customers/" + bewerber.getId())).build();

//            return Response.created(builder.build()).build();
        } catch (NullPointerException | IllegalArgumentException | JsonbException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    /**
     *
     * @param id
     * @param unterlagen_pfad
     * @return
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateBewerberPfad(
            @PathParam("id") Long id,
            @QueryParam("unterlagen_pfad") String unterlagen_pfad) {
        Bewerber bewerber = bewerberRepo.find(id);
        bewerber.setUnterlagen_pfad(unterlagen_pfad);
        bewerberRepo.edit(bewerber);
        URI bewerberUri = uriInfo.getBaseUriBuilder().path(BewerberFacadeREST.class).build();
        return Response.ok(bewerberUri).build();
    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response remove(@PathParam("id") Long id) {
        try {
            Bewerber bewerber = bewerberRepo.find(id);
            bewerberRepo.remove(bewerber);
            URI bewerberUri = uriInfo.getBaseUriBuilder().path(BewerberFacadeREST.class).build(bewerber.getId());
//            return Response.ok(jsonb.toJson(bewerber)).build();
            Link link = Link.fromUri(bewerberUri).rel("dies").type("application/json").param("method", "get").build("localhost", "8080");
            return Response.ok().links(link).build();
        } catch (NullPointerException | IllegalArgumentException | JsonbException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

//    /**
//     *
//     * @param entity
//     */
//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Bewerber entity) {
//        bewerberRepo.create(entity);
//    }
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Long id) {
//        bewerberRepo.remove(bewerberRepo.find(id));
//    }
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Bewerber find(@PathParam("id") Long id) {
//        return bewerberRepo.find(id);
//    }
//        @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Long id, Bewerber entity) {
//        bewerberRepo.edit(entity);
//    }
//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response findAll() {
//        return Response.ok(jsonb.toJson(bewerberRepo.findAll())).build();
//    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bewerber> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return bewerberRepo.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(bewerberRepo.count());
    }

}
