/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller;

import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Philipp Markmann
 * @author Leander Nordmann
 */
@Stateless
public class StellenController {

    @Inject
    private StellenRepository stellenRepo;

    /**
     *
     * @param stelle
     */
    public void createStelle(Stelle stelle) {
        this.stellenRepo.create(stelle);
    }

    /**
     *
     * @param personal_id
     * @return
     */
    public List<Stelle> getStelleFromPersonal(Long personal_id) {
        return this.stellenRepo.getStelleByPersonal(personal_id);
    }

    /**
     *
     * @return
     */
    public List<Stelle> getAlleStellen() {
        return this.stellenRepo.getAlleStellen();
    }

    /**
     *
     * @param id
     */
    public void deleteStelle(long id) {
        this.stellenRepo.deleteStelle(id);
    }

}
