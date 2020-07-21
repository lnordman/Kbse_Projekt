/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.controller.BewerbungsController;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.StellenController;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.entity.service.SessionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "personalModel")
@RequestScoped
public class PersonalBoundary implements Serializable {

    @Inject
    private PersonalController persoController;

    @Inject
    private StellenController stellenController;

    @Inject
    private BewerbungsController bewerbungController;

    private Personal personaler;

    private List<Stelle> stellenOfPersonaler;
    private List<Bewerbung> bewerbungenOfPersonaler;

    private Bewerbung bearbeitendeBewerbung;
    private Stelle loeschendeStelle;
    private String status;

    @PostConstruct
    public void init() {
        personaler = new Personal();
        bearbeitendeBewerbung = new Bewerbung();
        bewerbungenOfPersonaler = new ArrayList<>();
        loeschendeStelle = new Stelle();
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.PersonalBoundary.init()");
        this.personaler = SessionService.getPersonaler();

        if (this.personaler != null) {
            System.out.println("Personaler ist gesetezt!");
            System.out.println("Personaler_ID: " + this.personaler.getId());
            //Holen der Stellen die der Personaler erstellt hat
            this.stellenOfPersonaler = stellenController.getAlleStellen();
            bewerbungenOfPersonaler = persoController.getAlleBewerbungenByPersonal(this.personaler.getId());
            //Holen der Bewerbungen die der Personaler verwaltet
            //this.bewerbungOfPersonaler = bewerbungsController.getBewerbungFromPersonal(this.personal.getId());
        }

        // stellenOfPersonaler = new ArrayList(persoController.getBewerbungenOfPersonaler(personaler));
    }

    public void init2() {
        personaler = new Personal();
        bearbeitendeBewerbung = new Bewerbung();
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.PersonalBoundary.init2()");
        this.personaler = SessionService.getPersonaler();
        this.bearbeitendeBewerbung = SessionService.getBearbeitendeBewerbung();
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.PersonalBoundary.init2() PERSONALER" + personaler.getName());
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.PersonalBoundary.init2() BEWERBUNG" + bearbeitendeBewerbung.getStelle().getBezeichnung());
    }

    public String setSessionBewerbung() {
        Bewerbung bewerbung = this.bearbeitendeBewerbung;
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.PersonalBoundary.setSessionBewerbung() BEWERBUNG" + bearbeitendeBewerbung.getStelle().getBezeichnung());
        SessionService.getSession().setAttribute("bewerbung", bewerbung);
        return "PersonalerEine";
    }
    

    public void updateStatus() {
        Bewerbung bew = SessionService.getBearbeitendeBewerbung();
        bew.setStatus(status);
        System.out.print("Update Bewerbung durch Personaler Session schreiben" + bew.toString());
        SessionService.getSession().setAttribute("bewerbung", bew);
    }

    public String updateBewerbung() {
        Bewerbung bew = SessionService.getBearbeitendeBewerbung();
        System.out.print("Update Bewerbung durch Personaler in DB schreiben" + bew.toString());
        bewerbungController.updateBewerbung(bew);
        return "PersonalerStart";
    }

    public String deleteStelle() {
        System.out.print("Delete Stelle durch Personaler");
        stellenController.deleteStelle(this.loeschendeStelle.getId());
        return "PersonalerStart";
    }

    public PersonalController getPersoController() {
        return persoController;
    }

    public void setPersoController(PersonalController persoController) {
        this.persoController = persoController;
    }

    public StellenController getStellenController() {
        return stellenController;
    }

    public void setStellenController(StellenController stellenController) {
        this.stellenController = stellenController;
    }

    public Personal getPersonaler() {
        return personaler;
    }

    public void setPersonaler(Personal personaler) {
        this.personaler = personaler;
    }

    public List<Stelle> getStellenOfPersonaler() {
        return stellenOfPersonaler;
    }

    public void setStellenOfPersonaler(List<Stelle> stellenOfPersonaler) {
        this.stellenOfPersonaler = stellenOfPersonaler;
    }

    public List<Bewerbung> getBewerbungenOfPersonaler() {
        return this.bewerbungenOfPersonaler;
    }

    public void setBewerbungenOfPersonaler(List<Bewerbung> bewerbungenOfPersonaler) {
        this.bewerbungenOfPersonaler = bewerbungenOfPersonaler;
    }

    public Bewerbung getBearbeitendeBewerbung() {
        return bearbeitendeBewerbung;
    }

    public void setBearbeitendeBewerbung(Bewerbung bearbeitendeBewerbung) {
        this.bearbeitendeBewerbung = bearbeitendeBewerbung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Stelle getLoeschendeStelle() {
        return loeschendeStelle;
    }

    public void setLoeschendeStelle(Stelle loeschendeStelle) {
        this.loeschendeStelle = loeschendeStelle;
    }

    
}
