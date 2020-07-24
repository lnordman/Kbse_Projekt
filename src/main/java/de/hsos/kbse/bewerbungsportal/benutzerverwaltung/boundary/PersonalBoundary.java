/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.PersonalController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.controller.BewerbungsController;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.StellenController;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.entity.service.SessionService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Philipp Markmann
 * @author Robin Schmidt
 * @version 3
 */
@Named(value = "personalModel")
@RequestScoped
public class PersonalBoundary implements Serializable {

    @Inject
    private PersonalController persoController;

    @Inject
    private StellenController stellenController;

    @Inject
    private BewerbungsController bewerbungController;

    private Personal personaler;

    private List<Stelle> stellenOfPersonaler;
    private List<Bewerbung> bewerbungenOfPersonaler;

    private Bewerbung bearbeitendeBewerbung;
    private Stelle loeschendeStelle;
    private String status;

    private File donwloadFile;
    private FileInputStream inStream;

    /**
     * Nachdem ein Objekt erzeugt wurde und alle Injektionen durchgeführt
     * wurden, werden die Variablen (s.o.) Initalisierungen
     */
    @PostConstruct
    public void init() {

        personaler = new Personal();
        bearbeitendeBewerbung = new Bewerbung();
        bewerbungenOfPersonaler = new ArrayList<>();
        loeschendeStelle = new Stelle();

        this.personaler = SessionService.getPersonaler();

        if (this.personaler != null) {

            //Holen der Stellen die der Personaler erstellt hat
            this.stellenOfPersonaler = stellenController.getAlleStellen();
            //Holen der Bewerbungen die der Personaler verwaltet
            bewerbungenOfPersonaler = persoController.getAlleBewerbungenByPersonal(this.personaler.getId());

        }
    }

    /**
     * Zustäzliche Init Funktion mit für eine andere WebPage
     */
    public void init2() {
        personaler = new Personal();
        bearbeitendeBewerbung = new Bewerbung();
        this.personaler = SessionService.getPersonaler();
        this.bearbeitendeBewerbung = SessionService.getBearbeitendeBewerbung();
    }

    /**
     * Setzt in der akutellen Session das Attribut Bewerbung
     *
     * @return Weiterleitung zu PersonalerEine
     */
    public String setSessionBewerbung() {
        Bewerbung bewerbung = this.bearbeitendeBewerbung;
        SessionService.getSession().setAttribute("bewerbung", bewerbung);

        return "PersonalerEine";
    }

    /**
     *
     * @throws IOException
     */
    public void showPDF() throws IOException {

        bearbeitendeBewerbung = new Bewerbung();
        this.bearbeitendeBewerbung = SessionService.getBearbeitendeBewerbung();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("/Kbse_Projekt/uploads/" + this.bearbeitendeBewerbung.getBewerber().getId().toString() + "/" + bearbeitendeBewerbung.getUnterlagen_pfad());

    }

    /**
     * Update des Status der jeweiligen Bewerbung
     */
    public void updateStatus() {
        Bewerbung bew = SessionService.getBearbeitendeBewerbung();
        bew.setStatus(status);

        SessionService.getSession().setAttribute("bewerbung", bew);
    }

    /**
     * Updatefunktion des Personalers für die Bewerbung
     *
     * @return Weiterleitung zu PersonalerStart
     */
    public String updateBewerbung() {
        Bewerbung bew = SessionService.getBearbeitendeBewerbung();
        bewerbungController.updateBewerbung(bew);
        return "PersonalerStart";
    }

    /**
     * Löscht die ausgewählte Stelle
     *
     * @return Weiterleitung PersonalerStart
     */
    public String deleteStelle() {
        stellenController.deleteStelle(this.loeschendeStelle.getId());
        return "PersonalerStart";
    }

    /**
     *
     * @return
     */
    public PersonalController getPersoController() {
        return persoController;
    }

    /**
     *
     * @param persoController
     */
    public void setPersoController(PersonalController persoController) {
        this.persoController = persoController;
    }

    /**
     *
     * @return
     */
    public StellenController getStellenController() {
        return stellenController;
    }

    /**
     *
     * @param stellenController
     */
    public void setStellenController(StellenController stellenController) {
        this.stellenController = stellenController;
    }

    /**
     *
     * @return
     */
    public Personal getPersonaler() {
        return personaler;
    }

    /**
     *
     * @param personaler
     */
    public void setPersonaler(Personal personaler) {
        this.personaler = personaler;
    }

    /**
     *
     * @return
     */
    public List<Stelle> getStellenOfPersonaler() {
        return stellenOfPersonaler;
    }

    /**
     *
     * @param stellenOfPersonaler
     */
    public void setStellenOfPersonaler(List<Stelle> stellenOfPersonaler) {
        this.stellenOfPersonaler = stellenOfPersonaler;
    }

    /**
     *
     * @return
     */
    public List<Bewerbung> getBewerbungenOfPersonaler() {
        return this.bewerbungenOfPersonaler;
    }

    /**
     *
     * @param bewerbungenOfPersonaler
     */
    public void setBewerbungenOfPersonaler(List<Bewerbung> bewerbungenOfPersonaler) {
        this.bewerbungenOfPersonaler = bewerbungenOfPersonaler;
    }

    /**
     *
     * @return
     */
    public Bewerbung getBearbeitendeBewerbung() {
        return bearbeitendeBewerbung;
    }

    /**
     *
     * @param bearbeitendeBewerbung
     */
    public void setBearbeitendeBewerbung(Bewerbung bearbeitendeBewerbung) {
        this.bearbeitendeBewerbung = bearbeitendeBewerbung;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public Stelle getLoeschendeStelle() {
        return loeschendeStelle;
    }

    /**
     *
     * @param loeschendeStelle
     */
    public void setLoeschendeStelle(Stelle loeschendeStelle) {
        this.loeschendeStelle = loeschendeStelle;
    }

    /**
     *
     * @return
     */
    public FileInputStream getInStream() {
        return inStream;
    }

    /**
     *
     * @param inStream
     */
    public void setInStream(FileInputStream inStream) {
        this.inStream = inStream;
    }

    /**
     *
     * @return
     */
    public File getDonwloadFile() {
        return donwloadFile;
    }

    /**
     *
     * @param donwloadFile
     */
    public void setDonwloadFile(File donwloadFile) {
        this.donwloadFile = donwloadFile;
    }

}
