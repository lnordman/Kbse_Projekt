/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.BenutzerController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "registerboundary")
@RequestScoped
public class RegisterBoundary implements Serializable {

    @Inject
    private BenutzerController bController;

    private Benutzer benutzer;

    public void register() {

        bController.register(benutzer);

    }

    //Hier werden die Loginfunktionen gemacht
    //Loggout und Registrieren vllt auch?
    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }
}
