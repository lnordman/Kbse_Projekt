/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.StellenController;

import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.entity.service.SessionService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Philipp Markmann
 * @author Robin Schmidt
 * @version 3
 */
@Named(value = "stellenModel")
@ApplicationScoped
public class StellenBoundary implements Serializable {

    @Inject
    private StellenController stellenController;

    private Stelle stelle;
    private Personal aktivesPersonal;
    private List<Stelle> alleStellen;

    /**
     * Nachdem ein Objekt erzeugt wurde und alle Injektionen durchgeführt
     * wurden, werden die Variablen (s.o.) Initalisierungen
     */
    public void init() {
        stelle = new Stelle();
        aktivesPersonal = new Personal();
        this.aktivesPersonal = SessionService.getPersonaler();

    }

    /**
     * Zustäzliche Init Funktion mit für eine andere WebPage
     */
    public void init2() {
        stelle = new Stelle();
        aktivesPersonal = new Personal();
        this.alleStellen = this.stellenController.getAlleStellen();
    }

    /**
     * Erstellt einer neue Stelle mit dem aktuell eingeloggten Personaler
     *
     * @return
     */
    public String createStelleWithPersonaler() {

        stelle.setPersonal(this.aktivesPersonal);
        stelle.setDatum(new Date());

        stellenController.createStelle(stelle);

        stelle = new Stelle();
        return "PersonalerStart";
    }

    /**
     *
     * @return
     */
    public StellenController getStellenController() {
        return stellenController;
    }

    /**
     *
     * @param stellenController
     */
    public void setStellenController(StellenController stellenController) {
        this.stellenController = stellenController;
    }

    /**
     *
     * @return
     */
    public Personal getPersonaler() {
        return aktivesPersonal;
    }

    /**
     *
     * @param personaler
     */
    public void setPersonaler(Personal personaler) {
        this.aktivesPersonal = personaler;
    }

    /**
     *
     * @return
     */
    public Stelle getStelle() {
        return stelle;
    }

    /**
     *
     * @param stelle
     */
    public void setStelle(Stelle stelle) {
        this.stelle = stelle;
    }

    /**
     *
     * @return
     */
    public List<Stelle> getAlleStellen() {
        return alleStellen;
    }

    /**
     *
     * @param alleStellen
     */
    public void setAlleStellen(List<Stelle> alleStellen) {
        this.alleStellen = alleStellen;
    }
}
