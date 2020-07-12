/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import javax.persistence.Table;
/**
 *
 * @author pmarkman
 */

@Entity
@Table(name="bewerber")
public class Bewerber extends Benutzer {

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
    @OneToMany(fetch = FetchType.LAZY,
        mappedBy="bewerber",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonManagedReference
    private List<Bewerbung> bewerbung;

    public Bewerber() {
    }

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
    
    public List<Bewerbung> getBewerbung() {
        return bewerbung;
    }

    public void setBewerbung(List<Bewerbung> bewerbung) {
        this.bewerbung = bewerbung;
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
