/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository;

import Testpackage.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nordm
 */
@Dependent
public class StellenRepository extends AbstractRepository<Stelle> {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    public StellenRepository() {
        super(Stelle.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Stelle> getStelleByPersonal(Long personaler_id) {
        
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository.getStelleByPersonal()"+"Personal_ID: "+personaler_id);
        
        TypedQuery<Stelle> query;
        query = this.em.createQuery("select p from Stelle p where p.personal= :id", Stelle.class);
        query.setParameter("id",personaler_id);
        return query.getResultList();
    }
    
    public List<Stelle> getAlleStellen(){
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository.getAlleStellen()");
        
        TypedQuery<Stelle> query;
        query = this.em.createQuery("select p from Stelle p", Stelle.class);
        return query.getResultList();
    }
}
