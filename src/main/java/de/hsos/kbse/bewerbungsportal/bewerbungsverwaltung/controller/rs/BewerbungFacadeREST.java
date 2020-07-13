/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.controller.rs;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BewerberRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.PersonalRepository;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.repository.BewerbungRepository;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
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
    Jsonb jsonb;
    
    @Inject
    PersonalRepository personalRepository;
    @Inject
    StellenRepository stelleRepository;
    @Inject
    BewerberRepository bewerberRepository;
    @Inject
    BewerbungRepository bewerbungRepository;
    
    public BewerbungFacadeREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Bewerbung entity) {
        bewerbungRepository.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Bewerbung entity) {
        bewerbungRepository.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        bewerbungRepository.remove(bewerbungRepository.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bewerbung find(@PathParam("id") Long id) {
        return bewerbungRepository.find(id);
    }

    @GET

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bewerbung> findAll() {
        return bewerbungRepository.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bewerbung> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return bewerbungRepository.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(bewerbungRepository.count());
    }

    @POST
    @Path("bewerbung")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createBewerbung(
            @QueryParam("zeitstempel") String zeitstempel,
            @QueryParam("status") String status,
            @QueryParam("stellen_id") Long stellen_id,
            @QueryParam("bewerber_id") Long bewerber_id,
            @QueryParam("personal_id") Long personal_id
    ) {
        try {
//            @QueryParam("zeitstempel") Date zeitstempel,
            Personal personal = personalRepository.find(personal_id);
            Bewerber bewerber = bewerberRepository.find(bewerber_id);
            Stelle stelle = stelleRepository.find(stellen_id);
            DateFormat formatter = DateFormat.getDateTimeInstance();
            Date stempel  = formatter.parse( zeitstempel );//"24.12.2007 16:59:12"
            
            Bewerbung bewerbung = new Bewerbung(stempel, status, personal,stelle, bewerber);

            bewerbungRepository.create(bewerbung);
            return Response
                   .ok(jsonb.toJson(bewerbung))
                    .build();
        } catch (NullPointerException | IllegalArgumentException|ParseException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
