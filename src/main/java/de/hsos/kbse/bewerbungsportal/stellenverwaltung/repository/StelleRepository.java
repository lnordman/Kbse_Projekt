/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository;

import Testpackage.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nordm
 */
@Dependent
public class StelleRepository extends AbstractRepository<Stelle> {

     @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public StelleRepository() {
        super(Stelle.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
