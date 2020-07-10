/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;


import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.BenutzerController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Login;
import java.io.IOError;
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
@Named(value = "loginboundary")
@RequestScoped
public class LoginBoundary implements Serializable {
    
    @Inject
    private BenutzerController bController;
    
    private Login loginfromUser = new Login();
   
    public void loginUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Benutzer benutzer = bController.login(loginfromUser.getEmail(),loginfromUser.getPasswort());
        
        if(benutzer != null) {
            //Session Attribute setzten
            //context.getExternalContext().redirect("BLA");
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login fehlgeschlagen!", "Bitte überprüfe Benutzernamen und Passwort."));
    }
    
    //Hier werden die Loginfunktionen gemacht
    
    //Loggout und Registrieren vllt auch?
    
    
    
}
