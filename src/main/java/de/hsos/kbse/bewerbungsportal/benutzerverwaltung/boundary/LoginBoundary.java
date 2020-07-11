/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "LoginModel")
@RequestScoped
public class LoginBoundary implements Serializable {

    @Inject
    private PersonalController pController;

    private Login login = new Login();

    public void loginUser() {
        FacesContext context = FacesContext.getCurrentInstance();

        Personal personaler = pController.login(login.getEmail(), login.getPasswort());
        
        if (personaler != null) {
            try {
                context.getExternalContext().redirect("/Kbse_Projekt_Beta/AlleStellen.xhtml");
            } catch (IOException e) {
            }
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login fehlgeschlagen!", "Bitte überprüfe Benutzernamen und Passwort."));
    }

    //Hier werden die Loginfunktionen gemacht
    //Loggout und Registrieren vllt auch?
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

}
