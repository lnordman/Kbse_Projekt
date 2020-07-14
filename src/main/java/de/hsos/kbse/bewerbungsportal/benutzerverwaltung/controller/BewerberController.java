/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BewerberRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PMark
 */
@Stateless
public class BewerberController {

    @Inject
    private BewerberRepository bewRepo;

    public Bewerber login(String email, String password) {
        return this.bewRepo.findByLogin(email, password);
    }
    
    public void register(Bewerber bewerber) {
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.BewerberController.register()" + bewerber.toString());
        this.bewRepo.create(bewerber);
    }

}
