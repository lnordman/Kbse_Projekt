/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.BewerberController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.StellenController;
import de.hsos.kbse.entity.service.SessionService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "bewerberModel")
@RequestScoped
public class BewerberBoundary {

    @Inject
    private BewerberController bewerberController;

    @Inject
    private StellenController stellenController;

    private Bewerber bewerber = new Bewerber();

    void init() {
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init()");
        this.bewerber = SessionService.getBewerber();
    }

}
