/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.controller.BewerberController;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Bewerber;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.controller.BewerbungsController;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import de.hsos.kbse.entity.service.SessionService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author Philipp Markmann
 * @author Robin Schmidt
 * @version 3
 */
@Named(value = "bewerberModel")
@RequestScoped
public class BewerberBoundary implements Serializable {

    @Inject
    private BewerberController bewerberController;

    @Inject
    private BewerbungsController bewerbungController;

    private UploadedFile dokumente; //Zusammenhängende Bewerbung

    private Bewerber bewerber;
    private Stelle gewaehlteStelle;
    private Bewerbung bewerbung;
    private List<Stelle> alleStellen;
    private List<Bewerbung> eigeneBewerbungen;

    /**
     * Nachdem ein Objekt erzeugt wurde und alle Injektionen durchgeführt
     * wurden, werden die Variablen s.o. Initalisierungen
     */
    @PostConstruct
    public void init() {
        bewerber = new Bewerber();
        gewaehlteStelle = new Stelle();
        bewerbung = new Bewerbung();
        eigeneBewerbungen = new ArrayList<>();

        this.bewerber = SessionService.getBewerber();

        alleStellen = bewerberController.getAlleStellen();
        eigeneBewerbungen = bewerberController.getEigeneBewerbungen(bewerber.getId());

    }

    /**
     * Zustäzliche Init Funktion mit für eine andere WebPage
     */
    public void init2() {
        bewerber = new Bewerber();
        gewaehlteStelle = new Stelle();
        bewerbung = new Bewerbung();
        this.bewerber = SessionService.getBewerber();
        this.gewaehlteStelle = SessionService.getGewaehlteStelle();

    }

    /**
     *
     * @return Weiterleitung zur Bewerbung
     */
    public String setSessionStelle() {
        Stelle s = this.gewaehlteStelle;
        SessionService.getSession().setAttribute("stelle", s);
        return "Bewerbung";
    }

    /**
     *
     * @return Weiterleitung zur Eigene Bewerbungen
     */
    public String setSessionBewerbung() {
        Bewerbung bewerb = this.bewerbung;
        SessionService.getSession().setAttribute("bewerbung", bewerb);
        return "EigeneBewerbungen";
    }

    /**
     *
     * @return Weiterleitung nach Erfolgreichen anlegen zu Allen Stellen
     * @throws IOException
     */
    public String neueBewerbung() throws IOException {

        this.bewerber = SessionService.getBewerber();
        this.gewaehlteStelle = SessionService.getGewaehlteStelle();

        String bewerbungsUnterlagen = "";

        if (dokumente != null) {

            String filename = FilenameUtils.getBaseName(dokumente.getFileName());
            String extension = FilenameUtils.getExtension(dokumente.getFileName());

            File bewerberDir = new File("F:\\Philipp\\Documents\\GitHub\\Kbse_Projekt\\src\\main\\webapp\\uploads\\" + this.bewerber.getId().toString());
            //Erstelle einen Pfad-Verzeichnis für die Bewerbung für den jeweiligen Bewerber falls der Ordner noch nicht vorhanden
            if (!bewerberDir.exists()) {
                if (bewerberDir.mkdir()) {

                    bewerbungsUnterlagen = unterlagenSichern(filename, extension, this.bewerber);

                }
            } else { //Pflege die neue Bewerbugn in das Pfad-Verzeichnis des Bewerbers falls dieser sich auf eine andere Stelle bewirbt

                bewerbungsUnterlagen = unterlagenSichern(filename, extension, this.bewerber);
            }

            bewerbung.setUnterlagen_pfad(bewerbungsUnterlagen);
            bewerbung.setZeitstempel(new Date());
            bewerbung.setBewerber(this.bewerber);
            bewerbung.setStelle(this.gewaehlteStelle);
            bewerbung.setPersonal(this.gewaehlteStelle.getPersonal());

            bewerbungController.neueBewerbung(bewerbung);
            return "/Benutzer/Bewerber/AlleStellen";

        }
        return null;
    }

    /**
     *
     * @param filename
     * @param extension
     * @param bewerber
     * @return
     * @throws IOException
     */
    public String unterlagenSichern(String filename, String extension, Bewerber bewerber) throws IOException {

        java.nio.file.Path savePath = Paths.get("F:\\Philipp\\Documents\\GitHub\\Kbse_Projekt\\src\\main\\webapp\\uploads\\" + this.bewerber.getId().toString());
        java.nio.file.Path targetfile = Files.createTempFile(savePath, filename + "-", "." + extension);

        try (InputStream input = dokumente.getInputStream()) {
            Files.copy(input, targetfile, StandardCopyOption.REPLACE_EXISTING);
            return targetfile.getFileName().toString();
        }
    }

    /**
     *
     * @return Weiterleitung zur AlleStellen falls sich bereits auf diese Stelle
     * beworben wurde (FaceMessage). Ansonsten wird der Bewerber zu der
     * Berwerbung Weitergleitet um sich dort mit seinen Unterlagen zu bewerben
     */
    public String checkBereitsBeworben() {
        boolean bereitsBeworben;
        FacesContext context = FacesContext.getCurrentInstance();

        bereitsBeworben = this.bewerberController.bereitsBeworben(this.bewerber.getId(), this.gewaehlteStelle.getId());

        if (bereitsBeworben == true) {

            context.addMessage(null, new FacesMessage("Bereits auf diese Stelle beworben!", "Zum anschauen im Profil unter 'Eigene Bewerbungen' schauen."));
            return "AlleStellen";
        } else {

            return "Bewerbung";
        }
    }

    /**
     *
     * @return den aktuellen Bewerber
     */
    public Bewerber getBewerber() {
        return bewerber;
    }

    /**
     *
     * @param bewerber
     */
    public void setBewerber(Bewerber bewerber) {
        this.bewerber = bewerber;
    }

    /**
     *
     * @return die gewählte Stelle
     */
    public Stelle getGewaehlteStelle() {
        return gewaehlteStelle;
    }

    /**
     *
     * @param gewaehlteStelle
     */
    public void setGewaehlteStelle(Stelle gewaehlteStelle) {
        this.gewaehlteStelle = gewaehlteStelle;
    }

    /**
     *
     * @return
     */
    public List<Stelle> getAlleStellen() {
        return alleStellen;
    }

    /**
     *
     * @param alleStellen
     */
    public void setAlleStellen(List<Stelle> alleStellen) {
        this.alleStellen = alleStellen;
    }

    /**
     *
     * @return
     */
    public List<Bewerbung> getEigeneBewerbungen() {
        return eigeneBewerbungen;
    }

    /**
     *
     * @param eigeneBewerbungen
     */
    public void setEigeneBewerbungen(List<Bewerbung> eigeneBewerbungen) {
        this.eigeneBewerbungen = eigeneBewerbungen;
    }

    /**
     *
     * @return
     */
    public Bewerbung getBewerbung() {
        return bewerbung;
    }

    /**
     *
     * @param bewerbung
     */
    public void setBewerbung(Bewerbung bewerbung) {
        this.bewerbung = bewerbung;
    }

    /**
     *
     * @return
     */
    public UploadedFile getDokumente() {
        return dokumente;
    }

    /**
     *
     * @param dokumente
     */
    public void setDokumente(UploadedFile dokumente) {
        this.dokumente = dokumente;
    }

}
