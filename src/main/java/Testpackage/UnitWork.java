/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testpackage;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.repository.BenutzerRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nordm
 */
@RequestScoped
public class UnitWork  {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    @Inject
//    private IBenutzerRepository Benutzer;
//
//    private IBewerberRepository Bewerber;
//    private IPersonalRepository Personal;
//    private IBewerbungRepository Bewerbung;
//    private IStelleRepository Stelle;

    public UnitWork() {
//        Benutzer = new BenutzerRepository();
//        Bewerber = new BewerberRepository(em);
//        Personal = new PersonalRepository(em);
//        Bewerbung = new BewerbungRepository(em);
//        Stelle = new StelleRepository(em);
    }



}
