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
 * @author Philipp Markmann
 * @author Robin Schmidt
 * @version 3
 */
@Stateless
public class BewerberController {

    @Inject
    private BewerberRepository bewRepo;

    /**
     *
     * @param email
     * @param password
     * @return
     */
    public Bewerber login(String email, String password) {
        return this.bewRepo.findByLogin(email, password);
    }

    /**
     *
     * @param bewerber
     */
    public void register(Bewerber bewerber) {
        this.bewRepo.create(bewerber);
    }

    /**
     *
     * @param bewerber
     * @return
     */
    public Bewerber updateBewerber(Bewerber bewerber) {
        return this.bewRepo.edit(bewerber);
    }

    /**
     *
     * @return
     */
    public List<Stelle> getAlleStellen() {
        return bewRepo.getAlleStellen();
    }

    /**
     *
     * @param id
     * @return
     */
    public List<Bewerbung> getEigeneBewerbungen(long id) {
        return this.bewRepo.getEigeneBewerbungen(id);
    }

    /**
     * Liefer true / false auf die Bedingung zurück, ob sich bereit schon auf
     * diese Stelle beworben wurde
     *
     * @param bewerber_id
     * @param stelle_id
     * @return boolean
     */
    public boolean bereitsBeworben(long bewerber_id, long stelle_id) {
        return this.bewRepo.bereitsBeworben(bewerber_id, stelle_id);
    }

}
