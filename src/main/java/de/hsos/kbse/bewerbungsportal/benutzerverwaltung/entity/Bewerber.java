/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.FetchType;

import javax.persistence.Table;

/**
 *
 * @author pmarkman
 */
@Entity
@Table(name = "bewerber")
public class Bewerber extends Benutzer {

    //Bemerkung Nachschlagen: Persistierung von Datein in Java
    @Column(name = "name")
    private String name;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "straße")
    private String straße;

    @Column(name = "ort")
    private String ort;

    @Column(name = "plz")
    private Integer plz;

    @Column(name = "anlagen_pfad")
    String unterlagen_pfad;

    @Column(name = "portait_pfad")
    String portait_pfad;

    //Ein Bewerber kann mehrere Bewerbungen haben
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "bewerber",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Bewerbung> bewerbung;

    @Embedded
    Login login;

    /**
     *
     */
    public Bewerber() {
    }

    /**
     *
     * @param name
     * @param vorname
     * @param telefon
     * @param straße
     * @param ort
     * @param plz
     * @param unterlagen_pfad
     * @param portait_pfad
     * @param login
     */
    public Bewerber(String name, String vorname, String telefon, String straße, String ort, Integer plz, String unterlagen_pfad, String portait_pfad, Login login) {
        super(name, vorname, telefon, straße, ort, plz);
        this.unterlagen_pfad = unterlagen_pfad;
        this.portait_pfad = portait_pfad;
        this.login = login;
    }
       
    

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    @Override
    public String getVorname() {
        return vorname;
    }

    /**
     *
     * @param vorname
     */
    @Override
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTelefon() {
        return telefon;
    }

    /**
     *
     * @param telefon
     */
    @Override
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     *
     * @return
     */
    @Override
    public String getStraße() {
        return straße;
    }

    /**
     *
     * @param straße
     */
    @Override
    public void setStraße(String straße) {
        this.straße = straße;
    }

    /**
     *
     * @return
     */
    @Override
    public String getOrt() {
        return ort;
    }

    /**
     *
     * @param ort
     */
    @Override
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getPlz() {
        return plz;
    }

    /**
     *
     * @param plz
     */
    @Override
    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    /**
     *
     * @return
     */
    public Login getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     *
     * @return
     */
    public List<Bewerbung> getBewerbung() {
        return bewerbung;
    }

    /**
     *
     * @param bewerbung
     */
    public void setBewerbung(List<Bewerbung> bewerbung) {
        this.bewerbung = bewerbung;
    }

    /**
     *
     * @return
     */
    public String getUnterlagen_pfad() {
        return unterlagen_pfad;
    }

    /**
     *
     * @param unterlagen_pfad
     */
    public void setUnterlagen_pfad(String unterlagen_pfad) {
        this.unterlagen_pfad = unterlagen_pfad;
    }

    /**
     *
     * @return
     */
    public String getPortait_pfad() {
        return portait_pfad;
    }

    /**
     *
     * @param portait_pfad
     */
    public void setPortait_pfad(String portait_pfad) {
        this.portait_pfad = portait_pfad;
    }

}
