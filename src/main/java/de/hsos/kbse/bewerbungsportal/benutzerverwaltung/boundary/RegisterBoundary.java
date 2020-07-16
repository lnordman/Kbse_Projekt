/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.BewerberController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.entity.service.SessionService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "RegisterModel")
@SessionScoped
public class RegisterBoundary implements Serializable {

    @Inject
    transient private PersonalController personalerCont;
    @Inject
    transient private BewerberController bewerberCont;

    private Personal personal;
    private Bewerber bewerber;

    @PostConstruct
    public void init() {
        personal = new Personal();
        bewerber = new Bewerber();
    }

    public RegisterBoundary() {
    }

    //registerBewerber() {}
    
    public String registerPersonal() {
        System.out.print("Register Personal in DB schreiben"+personal.toString() + personal.getLogin().toString());
        personalerCont.register(personal);
        return "/Benutzer/Personal/Login-Personal";
    }
    
    public String updatePersonal(){
        System.out.print("Update Personal in DB schreiben"+personal.toString() + personal.getLogin().toString());
        SessionService.getSession().setAttribute("personaler", personal);
        personalerCont.updatePersonaler(personal);
        return "/Benutzer/Personal/PersonalerStart";
    }
    
    public String registerBewerber(){
        System.out.print("Register Bewerber in DB schreiben"+bewerber.toString() + bewerber.getLogin().toString());
        bewerberCont.register(bewerber);
        return  "Login-Bewerber";
    }
    
    public String updateBewerber(){
        System.out.print("Update Bewerber in DB schreiben"+bewerber.toString() + bewerber.getLogin().toString());
        SessionService.getSession().setAttribute("bewerber", bewerber);
        bewerberCont.updateBewerber(bewerber);
        return  "AlleStellen";
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Bewerber getBewerber() {
        return bewerber;
    }

    public void setBewerber(Bewerber bewerber) {
        this.bewerber = bewerber;
    }
    
    

    
}
