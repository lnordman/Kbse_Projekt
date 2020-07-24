/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity;

import de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity.Personal;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.interfaces.AbstractEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 *
 * @author pmarkman, lnordman
 */
@Entity
@Table(name = "stelle")
public class Stelle extends AbstractEntity {

//    @NotNull(message = "Bezeichnung cannot be null")
//    @Size(min = 2, message = "Bezeichnung must between 2 and 30 characters")
    @JsonbProperty("Bezeichnung")
    @Column(name = "bezeichung")
    String bezeichnung;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date datum;

//    @NotNull(message = "Beschreibung cannot be null")
//    @Size(min = 2, message = "Beschreibung must between 2 and 30 characters")
    @JsonbProperty("Beschreibung")
    @Column(name = "beschreibung")
    String beschreibung;

//    @NotNull(message = "Ort cannot be null")
//    @Size(min = 2, message = "Ort must between 2 and 30 characters")
    @JsonbProperty("Ort")
    @Column(name = "ort")
    String ort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
    @JsonBackReference
//    @JsonIgnore
//    @NotNull(message = "Personal cannot be null")
    @JsonbProperty("Personal")
    private Personal personal;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "stelle",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
//    @JsonIgnore
    @JsonbProperty("Bewerbung")
    private List<Bewerbung> bewerbungen = new ArrayList<>();

    public Stelle() {
    }
    
//    public Stelle(Stelle entity) {
//        this = entity;
//    }
    
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
