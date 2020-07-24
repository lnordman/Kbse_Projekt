/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository;

import de.hsos.kbse.interfaces.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Philipp Markmann
 * @author Leander Nordmann
 *
 */
@Named
public class PersonalRepository extends AbstractRepository<Personal> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public PersonalRepository() {
        super(Personal.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Sucht den Personaler in der DB mit den angebenen Parametern
     *
     * @param email
     * @param password
     * @return Personaler
     */
    public Personal findByLogin(String email, String password) {

        try {
            TypedQuery<Personal> query = this.em.createQuery("select p from Personal p where p.login.email = :email and p.login.password = :password", Personal.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    /**
     * Liefert alle Bewerbungen des übergebenen Personaler zurück
     *
     * @param id
     * @return List mit Bewerbungen 
     */
    public List<Bewerbung> getAlleBewerbungenByPersonal(long id) {

        try {
            TypedQuery<Bewerbung> query = this.em.createQuery("select b from Bewerbung b where b.personal.id = :id", Bewerbung.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
}
