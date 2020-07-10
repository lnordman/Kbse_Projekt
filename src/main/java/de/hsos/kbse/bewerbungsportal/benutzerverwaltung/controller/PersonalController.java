/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BenutzerRepository;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StelleRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.PersonalRepository;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.repository.BewerbungRepository;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author PMark
 */
@RequestScoped
public class PersonalController {

    //Inject-Punkt
    @Inject
    private PersonalRepository persoRepo;

    private BenutzerRepository benutzerRepo;

    private StelleRepository stellenRepo;

    private BewerbungRepository bewerbungRepo;

    //unique Email wunsch
    public Personal updatePersonaler(Personal personal) {
        return this.persoRepo.edit(personal);
    }

    /**
     * Liefert alle Stellen zurück
     * @return 
     */
    public List<Stelle> getAllStellen() {
        return this.stellenRepo.findAll();
    }

    /**
     * Liefert die Stelle mit der ausgewählten ID zurück
     * @param id
     * @return 
     */
    public Stelle getStelle(String id) {
        return this.stellenRepo.find(id);
    }

    /**
     * Für den Personaler die Funktion der Stelle erstellen
     * @param stelle 
     */
    public void newStelle(Stelle stelle) {
        this.stellenRepo.create(stelle);
    }

    /**
     * Editieren je weiligen Stelle
     * @param stelle 
     */
    public void editStelle(Stelle stelle) {
        this.stellenRepo.edit(stelle);
    }

    /**
     * Löscht die existierende Stelle
     * @param stelle 
     */
    public void deleteStelle(Stelle stelle) {
        this.stellenRepo.remove(stelle);
    }

    /**
     * Ändern des Zustand der Bewerbung
     * @param bewerbung
     * @return 
     */
    public Bewerbung editBewerbung(Bewerbung bewerbung) {
        return this.bewerbungRepo.edit(bewerbung);
    }

    /**
     * Bewerbungsfunktionen für das Personal
     */

    /**
     * Liefer alle offnen Bewerbungen zurück. Hier kann der Personaler per Auswahl die jeweilige Stelle anschauen
     * @return List<Bewerbung>
     */
    public List<Bewerbung> getAllBewerbungen() {
        return this.bewerbungRepo.findAll();
    }

    /**
     * Liefert eine spezielle Bewerbung zurück
     * @param bewerbung
     * @return Bewerbung
     */
    public Bewerbung getBewerbung(Bewerbung bewerbung) {
        return this.bewerbungRepo.find(bewerbung);
    }

    /**
     * Löscht die Bewerbung
     * @param bewerbung 
     */
    public void deleteBewerbung(Bewerbung bewerbung) {
        this.bewerbungRepo.remove(bewerbung);
    }
    
    /**
     * Löscht den Personaler
     * @param personal
     * @return 
     */
    public Personal deletePersonaler(Personal personal) {
        return null;
    }

    //deleteUser(Benutzer)
}
