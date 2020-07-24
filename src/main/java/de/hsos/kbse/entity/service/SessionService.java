/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.entity.service;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Philipp Markmann
 * @author Robin Schmidt
 */
public class SessionService {

    /**
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     *
     * @return Personaler
     */
    public static Personal getPersonaler() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (Personal) session.getAttribute("personaler");
    }

    /**
     *
     * @return Bewerber
     */
    public static Bewerber getBewerber() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (Bewerber) session.getAttribute("bewerber");
    }

    /**
     *
     * @return Stelle
     */
    public static Stelle getGewaehlteStelle() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (Stelle) session.getAttribute("stelle");
    }

    /**
     *
     * @return Bewerbung
     */
    public static Bewerbung getBearbeitendeBewerbung() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (Bewerbung) session.getAttribute("bewerbung");
    }
}
