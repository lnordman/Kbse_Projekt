/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.BewerberController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.controller.BewerbungsController;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.StellenController;
import de.hsos.kbse.entity.service.SessionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "bewerberModel")
@RequestScoped
public class BewerberBoundary implements Serializable{

    @Inject
    private BewerberController bewerberController;

    @Inject
    private StellenController stellenController;
    
    @Inject
    private BewerbungsController bewerbungController;

    private Bewerber bewerber;
    private Stelle gewaehlteStelle;
    private Bewerbung bewerbung;
    private List<Stelle> alleStellen;
    private List<Bewerbung> eigeneBewerbungen;
    
    
    @PostConstruct
    public void init() {
        bewerber = new Bewerber();
        gewaehlteStelle= new Stelle();
        bewerbung= new Bewerbung();
        eigeneBewerbungen = new ArrayList<>();
        
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init()");
        this.bewerber = SessionService.getBewerber();
        System.out.println("Bewerber: " + bewerber.getName());
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init()");
        alleStellen = bewerberController.getAlleStellen();
        
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init()");
        eigeneBewerbungen = bewerberController.getEigeneBewerbungen(bewerber.getId());
        System.out.println("Bewerber: " + this.bewerber.toString());
    }
    
    public void init2() {
        bewerber = new Bewerber();
        gewaehlteStelle= new Stelle();
        bewerbung= new Bewerbung();
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init2()");
        this.bewerber = SessionService.getBewerber();
        this.gewaehlteStelle = SessionService.getGewaehlteStelle();
        System.out.println("Bewerber: " + this.bewerber.toString() + this.bewerber.getName());
        System.out.println("Stelle: " + this.gewaehlteStelle.toString() + this.gewaehlteStelle.getBezeichnung());
        
    }

    public String setSessionStelle(){
        Stelle s = this.gewaehlteStelle;
        SessionService.getSession().setAttribute("stelle", s);
        return "Bewerbung";
    }
    public String setSessionBewerbung() {
        Bewerbung bewerb = this.bewerbung;
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.setSessionBewerbung() BEWERBUNG" + bewerbung.getStelle().getBezeichnung());
        SessionService.getSession().setAttribute("bewerbung", bewerb);
        return "EigeneBewerbungen";
    }
    
    public String neueBewerbung() {
        this.bewerber = SessionService.getBewerber();   //TODO warum hier nochmal holen?! benutzen wir doch auch so; sonst aber NULLPointer
        this.gewaehlteStelle = SessionService.getGewaehlteStelle();
        bewerbung.setBewerber(this.bewerber);
        bewerbung.setStelle(this.gewaehlteStelle);
        //TODO Habe den Personaler eingetragen, der die Stelle erstellt hat. Gibts was besseres?
        bewerbung.setPersonal(this.gewaehlteStelle.getPersonal());
        System.out.println("Neue Bewerbung in DB schreiben"+bewerbung.toString());
        System.out.println("Neue Bewerbung in DB schreiben BEWERBER"+this.bewerber.toString()+ "+ " + this.bewerber.getVorname() + "+ " + this.bewerber.getName());
        System.out.println("Neue Bewerbung in DB schreiben STELLE"+this.gewaehlteStelle.toString() + "+ " + this.gewaehlteStelle.getBezeichnung() );
        System.out.println("Neue Bewerbung in DB schreiben PERSONALER"+this.gewaehlteStelle.getPersonal().toString() + "+ " + this.gewaehlteStelle.getPersonal().getName() );
        
        bewerbungController.neueBewerbung(bewerbung);
        return "/Benutzer/Bewerber/AlleStellen";
    }
    
    public Bewerber getBewerber() {
        return bewerber;
    }

    public void setBewerber(Bewerber bewerber) {
        this.bewerber = bewerber;
    }

    public Stelle getGewaehlteStelle() {
        return gewaehlteStelle;
    }

    public void setGewaehlteStelle(Stelle gewaehlteStelle) {
        this.gewaehlteStelle = gewaehlteStelle;
    }

    public List<Stelle> getAlleStellen() {
        return alleStellen;
    }

    public void setAlleStellen(List<Stelle> alleStellen) {
        this.alleStellen = alleStellen;
    }

    public List<Bewerbung> getEigeneBewerbungen() {
        return eigeneBewerbungen;
    }

    public void setEigeneBewerbungen(List<Bewerbung> eigeneBewerbungen) {
        this.eigeneBewerbungen = eigeneBewerbungen;
    }

    public Bewerbung getBewerbung() {
        return bewerbung;
    }

    public void setBewerbung(Bewerbung bewerbung) {
        this.bewerbung = bewerbung;
    }
    
    

    
    
    
    
    
    
    
    

}
