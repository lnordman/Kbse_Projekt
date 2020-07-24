/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.repository;

import de.hsos.kbse.interfaces.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Philipp Markmann
 * @author Leander Nordmann
 */
@Named
public class BewerbungRepository extends AbstractRepository<Bewerbung> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public BewerbungRepository() {
        super(Bewerbung.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
