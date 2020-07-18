/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.controller;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.repository.BewerbungRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PMark
 */
@Stateless
public class BewerbungsController {
    
    @Inject
    private BewerbungRepository bewerbungRepo;
    
    public void neueBewerbung(Bewerbung bewerbung){
        System.out.println("de.hsos.kbse.bewerbungsportal.bewerbungverwaltung.controller.neueBewerbung() " + bewerbung.toString());
        this.bewerbungRepo.create(bewerbung);
    }
    
    public Bewerbung updateBewerbung(Bewerbung bewerbung){
        System.out.println("de.hsos.kbse.bewerbungsportal.bewerbungverwaltung.controller.updateBewerbung() " + bewerbung.toString());
        return this.bewerbungRepo.edit(bewerbung);
    }
}
