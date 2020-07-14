/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.*;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.entity.service.SessionService;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Philipp Markmann 
 * @author Leander Nordmann
 * @version 3
 * 
 */


@Named(value = "LoginModel")
@RequestScoped
public class LoginBoundary implements Serializable {

    @Inject
    private PersonalController pController;
    
    @Inject
    private BewerberController bController;

    private Login login = new Login();

    public void loginPersonaler() {
        FacesContext context = FacesContext.getCurrentInstance();

        Personal personaler = pController.login(login.getEmail(), login.getPassword());

        if (personaler != null) {
            SessionService.getSession().setAttribute("personaler", personaler);
            try {
                context.getExternalContext().redirect("/Bewerbungsportal/Benutzer/Personal/PersonalerStart.xhtml");
            } catch (IOException e) {
            }
        }
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login fehlgeschlagen!", "Bitte überprüfe Benutzernamen und Passwort."));
    }

    public void loginBewerber() {
        FacesContext context = FacesContext.getCurrentInstance();

        Bewerber bewerber = bController.login(login.getEmail(), login.getPassword());

        if (bewerber != null) {
            SessionService.getSession().setAttribute("bewerber", bewerber);
            try {
                context.getExternalContext().redirect("/Bewerbungsportal/Benutzer/Bewerber/AlleStellen.xhtml"); 
            } catch (IOException e) {
            }
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login fehlgeschlagen!", "Bitte überprüfe Benutzernamen und Passwort."));
    }

    /**
     *
     */
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("/Bewerbungsportal");
        } catch (IOException e) {
        }
    }
    
    /**
     *
     * @return
     */
    public Login getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public void setLogin(Login login) {
        this.login = login;
    }

}
