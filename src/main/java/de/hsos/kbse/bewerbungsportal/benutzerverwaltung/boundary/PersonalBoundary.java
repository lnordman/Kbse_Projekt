/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.entity.service.SessionService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PMark
 */
@Named(value = "personalModel")
@RequestScoped
public class PersonalBoundary implements Serializable {

    @Inject
    private PersonalController persoController;

    /*    @Inject
    private StelleRepository stellenrepo;*/

    private Personal personaler = new Personal();

    private Bewerbung b;

    private List<Stelle> stellenOfPersonaler;
    private List<Bewerbung> bewerbungenOfPersonaler;

    public void init() {

        this.personaler = SessionService.getPersonaler();

        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.PersonalBoundary.init()");
        System.out.println("Personaler: " + this.personaler.toString());
        // stellenOfPersonaler = new ArrayList(persoController.getBewerbungenOfPersonaler(personaler));
    }

    public PersonalController getPersoController() {
        return persoController;
    }

    public void setPersoController(PersonalController persoController) {
        this.persoController = persoController;
    }

    /*    public StelleRepository getStellenrepo() {
    return stellenrepo;
    }
    
    public void setStellenrepo(StelleRepository stellenrepo) {
    this.stellenrepo = stellenrepo;
    }*/

    public Personal getPersonaler() {
        return personaler;
    }

    public void setPersonaler(Personal personaler) {
        this.personaler = personaler;
    }

    public Bewerbung getB() {
        return b;
    }

    public void setB(Bewerbung b) {
        this.b = b;
    }

    public List<Stelle> getStellenOfPersonaler() {
        return stellenOfPersonaler;
    }

    public void setStellenOfPersonaler(List<Stelle> stellenOfPersonaler) {
        this.stellenOfPersonaler = stellenOfPersonaler;
    }

    public List<Bewerbung> getBewerbungenOfPersonaler() {
        return bewerbungenOfPersonaler;
    }

    public void setBewerbungenOfPersonaler(List<Bewerbung> bewerbungenOfPersonaler) {
        this.bewerbungenOfPersonaler = bewerbungenOfPersonaler;
    }

}
