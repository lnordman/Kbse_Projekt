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
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.controller.StellenController;
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
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author PMark
 */
@Named(value = "bewerberModel")
@RequestScoped
public class BewerberBoundary implements Serializable {

    @Inject
    private BewerberController bewerberController;

    @Inject
    private StellenController stellenController;

    @Inject
    private BewerbungsController bewerbungController;

    private UploadedFile dokumente; //Zusammenhängende Bewerbung

    private UploadedFile foto;

    private Bewerber bewerber;
    private Stelle gewaehlteStelle;
    private Bewerbung bewerbung;
    private List<Stelle> alleStellen;
    private List<Bewerbung> eigeneBewerbungen;

    @PostConstruct
    public void init() {
        bewerber = new Bewerber();
        gewaehlteStelle = new Stelle();
        bewerbung = new Bewerbung();
        eigeneBewerbungen = new ArrayList<>();

        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init()");
        this.bewerber = SessionService.getBewerber();
        System.out.println("Bewerber: " + bewerber.getName());
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init()");
        alleStellen = bewerberController.getAlleStellen();

        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init()");
        eigeneBewerbungen = bewerberController.getEigeneBewerbungen(bewerber.getId());
        System.out.println("Bewerber: " + this.bewerber.getName());
    }

    public void init2() {
        bewerber = new Bewerber();
        gewaehlteStelle = new Stelle();
        bewerbung = new Bewerbung();
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.init2()");
        this.bewerber = SessionService.getBewerber();
        this.gewaehlteStelle = SessionService.getGewaehlteStelle();
        System.out.println("Bewerber: " + this.bewerber.toString() + this.bewerber.getName());
        System.out.println("Stelle: " + this.gewaehlteStelle.toString() + this.gewaehlteStelle.getBezeichnung());

    }

    public String setSessionStelle() {
        Stelle s = this.gewaehlteStelle;
        SessionService.getSession().setAttribute("stelle", s);
        return "Bewerbung";
    }

    public String setSessionBewerbung() {
        Bewerbung bewerb = this.bewerbung;
        System.out.println("de.hsos.kbse.bewerbungsportal.benutzerverwaltung.boundary.BewerberBoundary.setSessionBewerbung() BEWERBUNG" + bewerbung.getStelle().getBezeichnung());
        SessionService.getSession().setAttribute("bewerbung", bewerb);
        return "EigeneBewerbungen";
    }

    public String neueBewerbung() throws IOException {

        this.bewerber = SessionService.getBewerber();   //TODO warum hier nochmal holen?! benutzen wir doch auch so; sonst aber NULLPointer
        this.gewaehlteStelle = SessionService.getGewaehlteStelle();

        String unterlagenPfad = "";

        //AddFile funktioniert nur wenn der p:commandButton ajax="false" enthält, crazy Shit!
        if (dokumente != null) {

            String filename = FilenameUtils.getBaseName(dokumente.getFileName());
            String extension = FilenameUtils.getExtension(dokumente.getFileName());

            System.out.println("Original Filename: " + filename + "\nPräfix: " + extension + "\n");

            File bewerberDir = new File("F:\\Philipp\\Documents\\GitHub\\Kbse_Projekt\\src\\main\\webapp\\uploads\\" + this.bewerber.getId().toString());
            if (!bewerberDir.exists()) { //Erstelle einen Pfad für die Bewerbung falls noch nicht vorhanden
                if (bewerberDir.mkdir()) {
                    System.out.println("Verzeichnis für Bewerber " + this.bewerber.getName() + "erstellt!");

                    unterlagenPfad = unterlagenSichern(filename, extension, this.bewerber);

                }
            } else { //Pflege die neue Bewerbugn in den Pfad des Bewerber falls eine andere Stelle
                System.out.println("Verzeichnis exisitiert für Bewerber" + this.bewerber.getId().toString());
                System.out.println("Datei wird im Verzeichnis gespeichert");

                unterlagenPfad = unterlagenSichern(filename, extension, this.bewerber);
            }

            bewerbung.setUnterlagen_pfad(filename+"."+extension);
            bewerbung.setZeitstempel(new Date());

            bewerbung.setBewerber(this.bewerber);
            bewerbung.setStelle(this.gewaehlteStelle);
            //TODO Habe den Personaler eingetragen, der die Stelle erstellt hat. Gibts was besseres?
            bewerbung.setPersonal(this.gewaehlteStelle.getPersonal());
            System.out.println("\n\nNeue Bewerbung in DB schreiben" + bewerbung.toString()+"\n\n");
            System.out.println("Neue Bewerbung in DB schreiben BEWERBER" + this.bewerber.toString() + "+ " + this.bewerber.getVorname() + "+ " + this.bewerber.getName());
            System.out.println("Neue Bewerbung in DB schreiben STELLE" + this.gewaehlteStelle.toString() + "+ " + this.gewaehlteStelle.getBezeichnung());
            System.out.println("Neue Bewerbung in DB schreiben PERSONALER" + this.gewaehlteStelle.getPersonal().toString() + "+ " + this.gewaehlteStelle.getPersonal().getName());

            bewerbungController.neueBewerbung(bewerbung);
            return "/Benutzer/Bewerber/AlleStellen";

        }

        System.out.println("\n\nFile == NULL ?!\n\n");
        return null;
    }

    public String unterlagenSichern(String filename, String extension, Bewerber bewerber) throws IOException {

        java.nio.file.Path savePath = Paths.get("F:\\Philipp\\Documents\\GitHub\\Kbse_Projekt\\src\\main\\webapp\\uploads\\" + this.bewerber.getId().toString());
        java.nio.file.Path targetfile = Files.createTempFile(savePath, filename + "-", "." + extension);

        try (InputStream input = dokumente.getInputStream()) {
            Files.copy(input, targetfile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Uploaded File succesfilly saved in " + targetfile);
            return targetfile.toString();
        }
    }

    public String checkBereitsBeworben() {
        boolean bereitsBeworben;
        System.out.println("CheckBreitsBeworben" + this.gewaehlteStelle.getId() + "+ " + this.bewerber.getId());

        bereitsBeworben = this.bewerberController.bereitsBeworben(this.bewerber.getId(), this.gewaehlteStelle.getId());
        System.out.println("CheckBreitsBeworben PRUEFER: " + bereitsBeworben);
        if (bereitsBeworben == true) {
            System.out.println("Du hast dich bereits auf diese Stelle beworben");
            return "AlleStellen";
        } else {
            System.out.println("Noch nicht beworben");
            return "Bewerbung";
        }
    }

    public Bewerber getBewerber() {
        return bewerber;
    }

    public void setBewerber(Bewerber bewerber) {
        this.bewerber = bewerber;
    }

    public Stelle getGewaehlteStelle() {
        return gewaehlteStelle;
    }

    public void setGewaehlteStelle(Stelle gewaehlteStelle) {
        this.gewaehlteStelle = gewaehlteStelle;
    }

    public List<Stelle> getAlleStellen() {
        return alleStellen;
    }

    public void setAlleStellen(List<Stelle> alleStellen) {
        this.alleStellen = alleStellen;
    }

    public List<Bewerbung> getEigeneBewerbungen() {
        return eigeneBewerbungen;
    }

    public void setEigeneBewerbungen(List<Bewerbung> eigeneBewerbungen) {
        this.eigeneBewerbungen = eigeneBewerbungen;
    }

    public Bewerbung getBewerbung() {
        return bewerbung;
    }

    public void setBewerbung(Bewerbung bewerbung) {
        this.bewerbung = bewerbung;
    }

    public UploadedFile getDokumente() {
        return dokumente;
    }

    public void setDokumente(UploadedFile dokumente) {
        this.dokumente = dokumente;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

}
