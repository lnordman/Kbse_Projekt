/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository;

import de.hsos.kbse.interfaces.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Philipp Markmann
 * @author Leander Nordmann
 */
@Named
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

    /**
     * Sucht den Bewerber in der DB mit den angebenen Parametern
     *
     * @param email
     * @param password
     * @return Bewerber
     */
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

    /**
     * Liefert die Liste mit allen Stellen zurück
     *
     * @return List mit Stellen
     */
    public List<Stelle> getAlleStellen() {
        try {
            TypedQuery<Stelle> query = this.em.createQuery("select s from Stelle s", Stelle.class);
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    /**
     * Liefer eine Liste mit allen Bewerbungen des jeweiligen Bewerber zurück
     *
     * @param id
     * @return List mit Stellen
     */
    public List<Bewerbung> getEigeneBewerbungen(long id) {

        try {
            TypedQuery<Bewerbung> query = this.em.createQuery("select bew from Bewerbung bew where bew.bewerber.id = :id", Bewerbung.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    /**
     * Liefer eine Boolean zurück mit true / false falls sich auf die übergebene
     * Stelle bereits beworben wurde
     *
     * @param bewerber_id
     * @param stelle_id
     * @return
     */
    public boolean bereitsBeworben(long bewerber_id, long stelle_id) {

        try {
            TypedQuery<Bewerbung> query = this.em.createQuery("select bew from Bewerbung bew where bew.bewerber.id = :b_id and bew.stelle.id = :s_id", Bewerbung.class);
            query.setParameter("b_id", bewerber_id);
            query.setParameter("s_id", stelle_id);
            if (query.getSingleResult() != null) {
                return true;
            }
            return false;
        } catch (NoResultException | NonUniqueResultException e) {
            return false;
        }
    }

}
