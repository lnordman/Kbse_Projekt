/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
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

    private Login login = new Login();

    /**
     *
     */
    public void loginUser() {
        FacesContext context = FacesContext.getCurrentInstance();

        Personal personaler = pController.login(login.getEmail(), login.getPassword());
        
        if (personaler != null) {
            SessionService.getSession().setAttribute("personaler", personaler);
            try {
                context.getExternalContext().redirect("/Kbse_Projekt_Beta/PersonalerStart.xhtml");
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
            context.getExternalContext().redirect("/Kbse_Projekt_Beta");
        }catch (IOException e) {
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
