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
 * @author Philipp Markmann
 * @author Leander Nordmann
 */
@Entity
@Table(name = "stelle")
public class Stelle extends AbstractEntity {

    @Column(name = "bezeichnung", length = 5000)
    @NotNull
    @Valid
    String bezeichnung;

    @Column(name = "beschreibung", length = 5000)
    @NotNull
    @Valid
    String beschreibung;

    @Column(name = "erstellungsdatum")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date datum;

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

    /**
     *
     * @param bezeichnung
     * @param datum
     * @param beschreibung
     * @param ort
     */
    public Stelle(String bezeichnung, Date datum, String beschreibung, String ort) {
        this.bezeichnung = bezeichnung;
        this.datum = datum;
        this.beschreibung = beschreibung;
        this.ort = ort;
    }

    /**
     *
     * @param bezeichnung
     * @param beschreibung
     * @param ort
     */
    public Stelle(String bezeichnung, String beschreibung, String ort) {
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
        this.ort = ort;
    }

    /**
     *
     * @return bezeichnugn der Stelle
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     *
     * @param bezeichnung
     */
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     *
     * @return erstellungsdatum
     */
    public Date getDatum() {
        return datum;
    }

    /**
     *
     * @param datum
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    /**
     *
     * @return stellen beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     *
     * @param beschreibung
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     *
     * @return ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     *
     * @param ort
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     *
     * @return Personaler der die Stelle erstellt hat
     */
    public Personal getPersonal() {
        return personal;
    }

    /**
     *
     * @param personal
     */
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    /**
     *
     * @return Liste aller Bewerbungen für die jeweilige Stelle
     */
    public List<Bewerbung> getBewerbungen() {
        return bewerbungen;
    }

    /**
     *
     * @param bewerbungen
     */
    public void setBewerbungen(List<Bewerbung> bewerbungen) {
        this.bewerbungen = bewerbungen;
    }

}
