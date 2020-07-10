/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BenutzerRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BewerberRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.PersonalRepository;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.repository.BewerbungRepository;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value="personalModel")
@SessionScoped
public class PersonalBoundary implements Serializable {
    
    @Inject
    private PersonalRepository personalrepo;
    private BewerberRepository bewerberrepo;
    private BewerbungRepository bewerbungsrepo;
    
    private Personal personaler;
    private Bewerber bewerber;
    private Bewerbung bewerbungen;

    public Personal getPersonaler() {
        return personaler;
    }

    public void setPersonaler(Personal personaler) {
        this.personaler = personaler;
    }

    public Bewerber getBewerber() {
        return bewerber;
    }

    public void setBewerber(Bewerber bewerber) {
        this.bewerber = bewerber;
    }

    public Bewerbung getBewerbungen() {
        return bewerbungen;
    }

    public void setBewerbungen(Bewerbung bewerbungen) {
        this.bewerbungen = bewerbungen;
    }
    
    
    

    
    
    
    
    
    
}
