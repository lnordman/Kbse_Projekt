/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.rs;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.PersonalRepository;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;




/**
 *
 * @author nordm
 */
@Stateless
@Path("de.hsos.kbse.entity.stelle")
public class StelleFacadeREST {

    @Inject
    StellenRepository stellenRepo;
    @Inject
    PersonalRepository personalRepo;

    @Inject 
    Jsonb jsonb ;
    @Context
    UriInfo uriInfo;
    

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Stelle entity) {
        stellenRepo.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Stelle entity) {
        stellenRepo.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        stellenRepo.remove(stellenRepo.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Stelle find(@PathParam("id") Long id) {
        return stellenRepo.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAll() {
        Collection<Stelle> all = stellenRepo.findAll();
        if(all.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(jsonb.toJson(all)).build();
    }

    
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Stelle> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return stellenRepo.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(stellenRepo.count());
    }

    @POST
    @Path("stelle/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createStelle(
            @QueryParam("bezeichnung") String bezeichnung,
            @QueryParam("beschreibung") String beschreibung,
            @QueryParam("ort") String ort,
            @QueryParam("datum")  String datum,
            @PathParam("id") Long id) {
        try {
            Personal personal = personalRepo.find(id);
            DateFormat formatter = DateFormat.getDateTimeInstance();
            Date d  = formatter.parse( datum );//"24.12.2007 16:59:12"

             Stelle stelle = new Stelle(bezeichnung,d, beschreibung, ort);
            stelle.setPersonal(personal);    
//            personal.getStelle().add(stelle);
            stellenRepo.create(stelle);
//            personalRepo.edit(personal);
            return Response.ok(jsonb.toJson(stelle)).build();
//            return Response.ok().build();
        } catch (NullPointerException | IllegalArgumentException | ParseException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
