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
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "stellenModel")
@ApplicationScoped //WARUUUUM?!
public class StellenBoundary implements Serializable {

    @Inject
    private StellenController stellenController;

    @Inject
    private PersonalRepository persoRepository;

    private Stelle stelle = new Stelle();
    private Personal aktivesPersonal = new Personal();

    public void init() {

        this.aktivesPersonal = SessionService.getPersonaler();

        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.boundary.StellenBoundary.init()");
        System.out.println("Personaler: " + this.aktivesPersonal.toString());
        System.out.println("Personaler: " + this.aktivesPersonal.getId());
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

    public String createStelleWithPersonaler() {

        stelle.setDatum(new Date());

        System.out.println("Aktiver Personaler_ID: " + this.aktivesPersonal.getId());

        stelle.setPersonal(this.aktivesPersonal);
        stellenController.createStelle(stelle);

        System.out.println(stelle.toString());
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.boundary.StellenBoundary.createStelleWithPersonaler()");
        System.out.println(this.stelle.toString());

        return "PersonalerStart";
    }

}
