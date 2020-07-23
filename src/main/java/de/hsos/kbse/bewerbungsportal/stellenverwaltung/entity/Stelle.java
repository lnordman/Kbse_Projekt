/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author pmarkman, lnordman
 */
@Entity
@Table(name = "stelle")
public class Stelle extends AbstractEntity {

    @Column(name = "bezeichnung", length = 5000)//Default ist 255 zu klein -> Truncation-Fehler 
    @NotNull
    @Valid
    String bezeichnung;

    @Column(name = "beschreibung", length = 5000)
    @NotNull
    @Valid
    String beschreibung;

    //Anforderung
    /*    @Column(name = "anforderung", length = 300)
    @NotNull
    @Valid
    String anforderung;*/
    
    @Column(name = "erstellungsdatum")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date datum;
    
    /*    @Column(name = "startdatum")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date startdatum;*/

    @Column(name = "ort", length = 5000)
    @NotNull
    @Valid
    String ort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
    @JsonBackReference
    private Personal personal;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "stelle",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Bewerbung> bewerbungen;

    public Stelle() {
    }

    public Stelle(String bezeichnung, Date datum, String beschreibung, String ort) {
        this.bezeichnung = bezeichnung;
        this.datum = datum;
        this.beschreibung = beschreibung;
        this.ort = ort;
    }

    public Stelle(String bezeichnung, String beschreibung, String ort) {
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
        this.ort = ort;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<Bewerbung> getBewerbungen() {
        return bewerbungen;
    }

    public void setBewerbungen(List<Bewerbung> bewerbungen) {
        this.bewerbungen = bewerbungen;
    }

}
