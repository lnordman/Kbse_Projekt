/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BenutzerRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author PMark
 */
@RequestScoped
public class BenutzerController {
    
       //Inject-Punkt
        @Inject
        private BenutzerRepository benutzerRepo;
        
        //login
        public Benutzer login(String email, String password) {
            return this.benutzerRepo.getBenutzerByLogin(email, password);
        }
    
        //register
        public void register(Benutzer benutzer) {
            this.benutzerRepo.create(benutzer);
        }
        
        //unique Email wunsch
  
        public Benutzer updateBenutzer(Benutzer benutzer) {
            return this.benutzerRepo.edit(benutzer);
        }
        
        //getAllUser
        public List<Benutzer> getAllBenutzer() {
            return (List<Benutzer>) this.benutzerRepo.getAllBenutzer();
        }
        
        //getUserById
        public Benutzer getBenutzerById(Integer id) {
            return benutzerRepo.find(id);
        }
        
        public Benutzer deleteBenutzer(Benutzer benutzer){
            return null;
        }
        
        //deleteUser(Benutzer)
        
        
}
