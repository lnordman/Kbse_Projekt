/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.repository.BewerbungRepository;
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
@Stateless
@Path("de.hsos.kbse.entity.bewerbung")
public class BewerbungFacadeREST {

    @Inject
    BewerbungRepository bewerbungsRepo;

    public BewerbungFacadeREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Bewerbung entity) {
        bewerbungsRepo.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Bewerbung entity) {
        bewerbungsRepo.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        bewerbungsRepo.remove(bewerbungsRepo.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bewerbung find(@PathParam("id") Long id) {
        return bewerbungsRepo.find(id);
    }

    @GET

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bewerbung> findAll() {
        return bewerbungsRepo.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bewerbung> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return bewerbungsRepo.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(bewerbungsRepo.count());
    }

    @POST
    @Path("bewerbung")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createBewerbung(
            //            @QueryParam("status") String status,
            @QueryParam("stellen_id") Long stellen_id,
            @QueryParam("bewerber_id") Long bewerber_id,
            @QueryParam("personal_id") Long personal_id
    ) {
        try {
//            @QueryParam("zeitstempel") Date zeitstempel,
            Bewerbung bewerbung = new Bewerbung();
//            bewerbung.setPersonal(unit.getPersonal().find(personal_id));
//            bewerbung.setBewerber(unit.getBewerber().find(bewerber_id));
//            bewerbung.setStellen(unit.getStelle().find(stellen_id));
            bewerbungsRepo.create(bewerbung);
            return Response
                    .status(Response.Status.OK)
                    .build();
        } catch (NullPointerException | IllegalArgumentException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
