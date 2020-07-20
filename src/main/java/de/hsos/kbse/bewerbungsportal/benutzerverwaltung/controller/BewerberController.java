/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BewerberRepository;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.List;
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
    
    public Bewerber updateBewerber(Bewerber bewerber) {
        return this.bewRepo.edit(bewerber);
    }
    
    public List<Stelle> getAlleStellen(){
       return bewRepo.getAlleStellen();
    }
    
    public List<Bewerbung> getEigeneBewerbungen(long id){
        return this.bewRepo.getEigeneBewerbungen(id);
    }
    
    public boolean bereitsBeworben(long bewerber_id, long stelle_id){
        return this.bewRepo.bereitsBeworben(bewerber_id, stelle_id);
    }
    
}
