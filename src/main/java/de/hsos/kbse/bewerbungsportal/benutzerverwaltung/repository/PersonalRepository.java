/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository;

import Testpackage.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nordm
 */
@Named
public class PersonalRepository extends AbstractRepository<Personal>  {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public PersonalRepository() {
        super(Personal.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
