/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.PersonalRepository;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository;

import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PMark
 */
@Stateless
public class PersonalController {

    //Inject-Punkt
    @Inject
    private PersonalRepository persoRepo;
    
    @Inject
    private StellenRepository stellenRepo;

    public Personal login(String email, String password) {
        return this.persoRepo.findByLogin(email, password);
    }

    public void register(Personal personal) {
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController.register()" + personal.toString());
        this.persoRepo.create(personal);
    }

    //unique Email wunsch
    public Personal updatePersonaler(Personal personal) {
        return this.persoRepo.edit(personal);
    }

    public List<Bewerbung> getAlleBewerbungenByPersonal(long id){
        return this.persoRepo.getAlleBewerbungenByPersonal(id);
    }
    /**
     * Liefert die Stelle mit der ausgewählten ID zurück
     *
     * @param id
     * @return
     */
    public Stelle getStelle(String id) {
        return this.stellenRepo.find(id);
    }


}
