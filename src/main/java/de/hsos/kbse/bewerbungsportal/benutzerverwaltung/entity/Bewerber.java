/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.benutzerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Table;
/**
 *
 * @author pmarkman
 */
//@Entity
////@Vetoed Erklärung nötig
//@Table(name = "Bewerber")
////NamedQueries ergänzen!
//@Transactional(Transactional.TxType.MANDATORY) // Überprüfen!


//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name="bewerber")

public class Bewerber extends Benutzer {

    //Bemerkung Nachschlagen: Persistierung von Datein in Java
    @Column(name = "anlagen_pfad")
//    @NotNull
    @Valid
    String unterlagen_pfad;
    
    @Column(name = "portait_pfad")
    @NotNull
    @Valid
    String portait_pfad;

    //Ein Bewerber kann mehrere Bewerbungen haben
    @OneToMany(fetch = FetchType.LAZY,
        mappedBy="bewerbung",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonManagedReference
    private List<Bewerbung> bewerbung;

    public Bewerber() {
    }


    public Bewerber( String name, String vorname, String email, String telefon, String ort, String straße, Integer plz, String portait) {
        super(name,vorname,email, telefon, straße, ort, plz);
        this.portait_pfad = portait;
    }


    
//    public List<Bewerbung> getBewerbung() {
//        return bewerbung;
//    }
//
//    public void setBewerbung(List<Bewerbung> bewerbung) {
//        this.bewerbung = bewerbung;
//    }
//    

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.unterlagen_pfad);
        hash = 17 * hash + Objects.hashCode(this.portait_pfad);
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
        final Bewerber other = (Bewerber) obj;
        if (!Objects.equals(this.unterlagen_pfad, other.unterlagen_pfad)) {
            return false;
        }
        if (!Objects.equals(this.portait_pfad, other.portait_pfad)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bewerber{" + "unterlagen_pfad=" + unterlagen_pfad + ", portait_pfad=" + portait_pfad + '}';
    }

}
