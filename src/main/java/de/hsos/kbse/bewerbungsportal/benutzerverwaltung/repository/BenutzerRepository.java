package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Testpackage.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Benutzer;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nordm
 */
@Dependent
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

    public Benutzer getBenutzerByLogin(String email, String password) {
        try {
            TypedQuery<Benutzer> query = this.em.createQuery("select p from Benutzer p where p.login.email = :email and p.login.password = :pwd", Benutzer.class);
            query.setParameter("email", email);
            query.setParameter("pwd", password);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
    
    public List<Benutzer> getAllBenutzer() {
        TypedQuery<Benutzer> query = this.em.createQuery("select p from Benutzer p", Benutzer.class);
        return query.getResultList();
    }

}
