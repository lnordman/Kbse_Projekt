package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Testpackage.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nordm
 */
@Named
public class BenutzerRepository extends AbstractRepository<Benutzer> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public BenutzerRepository() {
        super(Benutzer.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
