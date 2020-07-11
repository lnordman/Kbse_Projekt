/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pmarkman
 */
@Entity
@Table(name = "Bewerber")
public class Bewerber extends AbstractEntity {

    //Bemerkung Nachschlagen: Persistierung von Datein in Java
    @Column(name = "name")
    private String name;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "email")
    private String email;

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
    @OneToMany(mappedBy = "bewerber")
    private Set<Bewerbung> bewerbung;

    public Bewerber() {
    }

    public Bewerber(String name, String vorname, String email, String telefon, String straße, String ort, Integer plz, String unterlagen_pfad, String portait_pfad) {
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.telefon = telefon;
        this.straße = straße;
        this.ort = ort;
        this.plz = plz;
        this.unterlagen_pfad = unterlagen_pfad;
        this.portait_pfad = portait_pfad;
    }

    public Bewerber(String name, String vorname, String email, String telefon, String ort, String straße, Integer plz, String portait_pfad) {
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.telefon = telefon;
        this.straße = straße;
        this.ort = ort;
        this.plz = plz;
        this.portait_pfad = portait_pfad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStraße() {
        return straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Integer getPlz() {
        return plz;
    }

    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    public String getUnterlagen_pfad() {
        return unterlagen_pfad;
    }

    public void setUnterlagen_pfad(String unterlagen_pfad) {
        this.unterlagen_pfad = unterlagen_pfad;
    }

    public String getPortait_pfad() {
        return portait_pfad;
    }

    public void setPortait_pfad(String portait_pfad) {
        this.portait_pfad = portait_pfad;
    }

    public Set<Bewerbung> getBewerbung() {
        return bewerbung;
    }

    public void setBewerbung(Set<Bewerbung> bewerbung) {
        this.bewerbung = bewerbung;
    }

}
