/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.rs;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BewerberRepository;
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

/**
 *
 * @author nordm
 */
@Stateless
@Path("de.hsos.kbse.entity.bewerber")
public class BewerberFacadeREST {

    public BewerberFacadeREST() {

    }

    @Inject
    BewerberRepository bewerberRepo;
    @Inject
    Jsonb jsonb;

    @POST
    @Path("bewerber")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createBewerber(
            @QueryParam("name") String name,
            @QueryParam("vorname") String vorname,
            @QueryParam("email") String email,
            @QueryParam("password") String password,
            @QueryParam("telefon") String telefon,
            @QueryParam("ort") String ort,
            @QueryParam("straße") String straße,
            @QueryParam("plz") Integer plz,
            @QueryParam("portait_pfad") String portait_pfad) {
        try {
            Login login = new Login(email, password);
//            Bewerber bewerber = new Bewerber(name, vorname, email, telefon, ort, straße, plz, portait_pfad);
              Bewerber bewerber = new Bewerber(name, vorname, telefon, straße, ort, plz, portait_pfad, portait_pfad, login);
            bewerberRepo.create(bewerber);
            return Response.ok(jsonb.toJson(bewerber)).build();
        } catch (NullPointerException | IllegalArgumentException | JsonbException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Bewerber entity) {
        bewerberRepo.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Bewerber entity) {
        bewerberRepo.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        bewerberRepo.remove(bewerberRepo.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bewerber find(@PathParam("id") Long id) {
        return bewerberRepo.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAll() {
        return Response.ok(jsonb.toJson(bewerberRepo.findAll())).build();
    }

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
