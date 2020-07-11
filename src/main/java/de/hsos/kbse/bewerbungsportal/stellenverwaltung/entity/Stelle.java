/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.Objects;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pmarkman
 */
@Entity
//@Vetoed 
//@Table(name = "Stelle")
////@NamedQueries ergänzen
//@Transactional(Transactional.TxType.MANDATORY) // Überprüfen!
public class Stelle extends AbstractEntity {

    @Column(name = "bezeichnung")
    @NotNull
    @Valid
    String bezeichnung;

//    @Column(name = "datum")
//    @Temporal(TemporalType.DATE)
//    @Valid
//    LocalDate datum;
    @Column(name = "beschreibung")
    @NotNull
    @Valid
    String beschreibung;

    @Column(name = "ort")
    @NotNull
    @Valid
    String ort;

    @ManyToOne
    @JoinColumn(name = "PERSONAL_ID")
    private Personal personal;

    @OneToMany
    private Set<Bewerbung> bewerbungen;

    public Stelle() {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.bezeichnung);
        hash = 73 * hash + Objects.hashCode(this.beschreibung);
        hash = 73 * hash + Objects.hashCode(this.ort);
        hash = 73 * hash + Objects.hashCode(this.personal);
        hash = 73 * hash + Objects.hashCode(this.bewerbungen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stelle other = (Stelle) obj;
        if (!Objects.equals(this.bezeichnung, other.bezeichnung)) {
            return false;
        }
        if (!Objects.equals(this.beschreibung, other.beschreibung)) {
            return false;
        }
        if (!Objects.equals(this.ort, other.ort)) {
            return false;
        }
        if (!Objects.equals(this.personal, other.personal)) {
            return false;
        }
        if (!Objects.equals(this.bewerbungen, other.bewerbungen)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Stelle{" + "bezeichnung=" + bezeichnung + ", beschreibung=" + beschreibung + ", ort=" + ort + ", personal=" + personal + ", bewerbungen=" + bewerbungen + '}';
    }

}
