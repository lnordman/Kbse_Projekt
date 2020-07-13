/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StelleRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PMark
 */
@Stateless
public class StellenController {
    
    @Inject
    private StelleRepository stellenRepo;
    
    public void createStelle(Stelle stelle) {
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.StellenController.createStelle()");
        this.stellenRepo.create(stelle);
    }
       
    
}
