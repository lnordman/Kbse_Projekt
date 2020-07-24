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
 * @author Philipp Markmann
 * @author Robin Schmidt
 */
@Stateless
public class BewerbungsController {

    @Inject
    private BewerbungRepository bewerbungRepo;

    /**
     *
     * @param bewerbung
     */
    public void neueBewerbung(Bewerbung bewerbung) {
        this.bewerbungRepo.create(bewerbung);
    }

    /**
     *
     * @param bewerbung
     * @return
     */
    public Bewerbung updateBewerbung(Bewerbung bewerbung) {
        return this.bewerbungRepo.edit(bewerbung);
    }
}
