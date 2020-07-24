/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository;

import Testpackage.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nordm
 */
//@Named

public class BewerberRepository extends AbstractRepository<Bewerber> {

    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public BewerberRepository() {
        super(Bewerber.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Bewerber findByLogin(String email, String password) {
        try {
            TypedQuery<Bewerber> query = this.em.createQuery("select b from Bewerber b where b.login.email = :email and b.login.password = :password", Bewerber.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
    
    public List<Stelle> getAlleStellen(){
         try {
            TypedQuery<Stelle> query = this.em.createQuery("select s from Stelle s", Stelle.class);
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
    
}
