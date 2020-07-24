/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.StellenController;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.PersonalRepository;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.entity.service.SessionService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "stellenModel")
@ApplicationScoped
public class StellenBoundary implements Serializable {

    @Inject
    private StellenController stellenController;

    @Inject
    private PersonalRepository persoRepository;

    private Stelle stelle;
    private Personal aktivesPersonal;
    private List<Stelle> alleStellen;

    public void init() {
        stelle = new Stelle();
        aktivesPersonal = new Personal();
        this.aktivesPersonal = SessionService.getPersonaler();
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.boundary.StellenBoundary.init()");
        System.out.println("Personaler: " + this.aktivesPersonal.toString());
        System.out.println("Personaler: " + this.aktivesPersonal.getId());
    }

    public void init2() {
        stelle = new Stelle();
        aktivesPersonal = new Personal();
        this.alleStellen = this.stellenController.getAlleStellen();
    }

    public StellenController getStellenController() {
        return stellenController;
    }

    public void setStellenController(StellenController stellenController) {
        this.stellenController = stellenController;
    }

    public Personal getPersonaler() {
        return aktivesPersonal;
    }

    public void setPersonaler(Personal personaler) {
        this.aktivesPersonal = personaler;
    }

    public Stelle getStelle() {
        return stelle;
    }

    public void setStelle(Stelle stelle) {
        this.stelle = stelle;
    }

    public List<Stelle> getAlleStellen() {
        return alleStellen;
    }

    public void setAlleStellen(List<Stelle> alleStellen) {
        this.alleStellen = alleStellen;
    }

    public String createStelleWithPersonaler() {

        System.out.println("Aktiver Personaler_ID: " + this.aktivesPersonal.getId());

        stelle.setPersonal(this.aktivesPersonal);
        stelle.setDatum(new Date());

        System.out.println("1. Stelle_ID: " + stelle.getId());
        
        stellenController.createStelle(stelle);

        System.out.println("2. Stelle_ID: " + stelle.getId());

        System.out.println(stelle.toString());
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.boundary.StellenBoundary.createStelleWithPersonaler()");
        System.out.println(this.stelle.toString());

        stelle = new Stelle();
        return "PersonalerStart";
    }

}
