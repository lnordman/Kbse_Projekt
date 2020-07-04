/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.PersonalRepository;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.JsonbException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Stateless
@Path("de.hsos.kbse.entity.personal")
public class PersonalFacadeREST {

    public PersonalFacadeREST() {
    }

    @Inject
    PersonalRepository personalRepo;

    @POST
    @Path("personal")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createPersonal(
            @QueryParam("name") String name,
            @QueryParam("vorname") String vorname,
            @QueryParam("email") String email,
            @QueryParam("telefon") String telefon,
            @QueryParam("ort") String ort,
            @QueryParam("straße") String straße,
            @QueryParam("plz") Integer plz,
            @QueryParam("durchwahl") String durchwahl,
            @QueryParam("bueroNr") String bueroNr) {
        try {
            Personal personal = new Personal(name, vorname, email, telefon, ort, straße, plz, durchwahl, bueroNr);

            personalRepo.create(personal);
            return Response
                    .status(Response.Status.FOUND)
                    .build();
        } catch (NullPointerException | IllegalArgumentException | JsonbException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST

    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Personal entity) {
        personalRepo.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Personal entity) {
        personalRepo.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        personalRepo.remove(personalRepo.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Personal find(@PathParam("id") Long id) {
        return personalRepo.find(id);
    }

    @GET

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Personal> findAll() {
        return personalRepo.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Personal> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return personalRepo.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(personalRepo.count());
    }

}
