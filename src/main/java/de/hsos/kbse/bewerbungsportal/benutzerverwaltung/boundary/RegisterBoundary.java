/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
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
    //@Inject
    //transient private BewerberController bewerberCont;

    private Personal personal;

    @PostConstruct
    public void init() {
        personal = new Personal();
    }

    public RegisterBoundary() {
    }

    //registerBewerber() {}
    
    public String registerPersonal() {
        personalerCont.register(personal);
        return "Login";
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}
