/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.BewerberController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.entity.service.SessionService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Philipp Markmann
 * @author Robin Schmidt
 * @version 3
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

    /**
     * Nachdem ein Objekt erzeugt wurde und alle Injektionen durchgeführt
     * wurden, werden die Variablen (s.o.) Initalisierungen
     */
    @PostConstruct
    public void init() {
        personal = new Personal();
        bewerber = new Bewerber();
    }

    public RegisterBoundary() {
    }

    /**
     *
     * @return
     */
    public String registerPersonal() {

        personalerCont.register(personal);
        return "/Benutzer/Personal/Login-Personal";
    }

    /**
     *
     * @return
     */
    public String updatePersonal() {
        SessionService.getSession().setAttribute("personaler", personal);
        personalerCont.updatePersonaler(personal);
        return "/Benutzer/Personal/PersonalerStart";
    }

    /**
     *
     * @return
     */
    public String registerBewerber() {
        bewerberCont.register(bewerber);
        return "Login-Bewerber";
    }

    /**
     *
     * @return
     */
    public String updateBewerber() {
        SessionService.getSession().setAttribute("bewerber", bewerber);
        bewerberCont.updateBewerber(bewerber);
        return "AlleStellen";
    }

    /**
     *
     * @return
     */
    public Personal getPersonal() {
        return personal;
    }

    /**
     *
     * @param personal
     */
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    /**
     *
     * @return
     */
    public Bewerber getBewerber() {
        return bewerber;
    }

    /**
     *
     * @param bewerber
     */
    public void setBewerber(Bewerber bewerber) {
        this.bewerber = bewerber;
    }

}
